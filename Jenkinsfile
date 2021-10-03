pipeline {
    // any agent with label maven will be picked
    agent {
        node {
            label 'maven'    
        }
    }
    // keeps only the last 5 build history
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }    
    // declaring variables
    environment {
        CI = 'true'
        GITHUB_REPO = 'josesunoj/scriptedpipeline'
        ARTIFACTORY = 'sunojjose.jfrog.io'
        REGISTRY = 'sunojjose.jfrog.io/scripted-pipeline'
        ARTIFACTROY_ACCESS_KEY = credentials('artifactory-access-key')

    }
    // supports for parameterised build
    parameters { 
        string(name: 'APP', defaultValue: 'triangle_app', description: 'The name of the sample application') 
        string(name: 'MAIL_ID', defaultValue: $DEFAULT_RECIPIENTS, description: 'Email ID(s) of the developer(s)')
    }
    stages {
        stage("Build") {
           
            steps {
                sh 'mvn compile'
            }
        }
        // The test stage will be executed in parallel, 
        // if a failure in any stage occurs, the remaining stages outside the parallel block will be skipped
        stage("Test") {
           
            failFast true
            // the stages inside the parallel block will be executed in parallel
            parallel {
                stage('Testing') {
                    // this block will be executed sequentially
                    stages{
                        stage('Unit Test') {
                            steps {
                                sh 'mvn clean test'
                            }
                        } 
                        stage('Code Coverage') {
                            steps {
                                sh 'mvn clean verify'
                            }
                        }  
                    }
                    
                }
                stage('Code Analysis') {
                    // this block is also executed sequentially
                    stages {
                        stage('Code Scanning') {
                            steps {
                                withSonarQubeEnv(installationName: 'sonarqube') { 
                                    sh 'mvn clean install org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                                }
                            }
                        }
                        stage('Quality Gate') {
                            steps {
                                timeout(time: 3, unit: 'MINUTES') {
                                    waitForQualityGate abortPipeline: true
                                }
                            }
                        }
                    } 
                }
            }
        }
        
        // this stage is executed only if the branch is the main
        
        stage("Build and Push Image to Artifactory") {
            
            // any agent with a label docker will be picked
            
            agent {
                node {
                    label 'docker' 
                } 
            }
            when {
                branch 'main'
            }
            stages {
                stage('Buid Image') {
                    steps {
                        sh 'docker build -t $GITHUB_REPO/$APP .'
                    }
                }
                stage('Login To Artifactory') {
                
                    steps {
                        sh 'echo $ARTIFACTROY_ACCESS_KEY_PSW | docker login $ARTIFACTORY -u $ARTIFACTROY_ACCESS_KEY_USR --password-stdin'
                    }
                }
                stage('Push Image to Artifactory') {
                
                    steps {
                        sh '''
                            docker tag $GITHUB_REPO/$APP $REGISTRY/$APP:v-$BUILD_NUMBER
                            docker push $REGISTRY/$APP:v-$BUILD_NUMBER
                        '''
                    }
                }
            }
            // ensures the user is loged out after the execution
            post('Log Out') {
                always {
                    sh 'docker logout'
                }
            }
        }
    }
    // the developer will be notified in the event of a failure with logs attached
     post {
        failure {
            emailext attachLog: true, body: '''Hi,
$PROJECT_NAME Job $BUILD_NUMBER Failed. 
Please Find The Attached Report.
Thank you.''', 
                compressLog: true, 
                replyTo: $DEFAULT_REPLYTO, 
                subject: '$JOB_NAME Job # $BUILD_NUMBER $BUILD_STATUS Info- ', 
                to: params.MAIL_ID
        }
    }
}
