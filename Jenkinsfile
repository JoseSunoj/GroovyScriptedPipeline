pipeline {
    agent {
        node {
            label 'maven'    
        }
    }
    environment {
        CI = 'true'
        GITHUB_REPO = 'JoseSunoj/ScriptedPipeline'
        ARTIFACTORY = 'sunojjose.jfrog.io'
        REGISTRY = 'sunojjose.jfrog.io/scripted-pipeline'
        ARTIFACTROY_ACCESS_KEY = credentials('artifactory-access-key')

    }
    parameters { 
        string(name: 'APP', defaultValue: 'triangle_app', description: 'The name of the sample application') 
        string(name: 'MAIL_ID', defaultValue: '', description: 'Email ID(s) of the developer(s)')
    }
    stages {
        stage("Build") {
           
            steps {
                sh 'mvn compile'
            }
        }
        stage("Test") {
           
            failFast true
            parallel {
                stage('Unit Test') {
                    steps {
                         sh 'mvn test'
                    }
                }
                stage('Code Analysis') {
                    stages {
                        stage('Code Scanning') {
                            steps {
                                withSonarQubeEnv(installationName: 'sonarqube') { 
                                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
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
        stage("Build and Push Image to Artifactory") {
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
                        sh 'echo $ARTIFACTROY_ACCESS_KEY | docker login --username=_ --password-stdin $ARTIFACTORY'
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
            post('Log Out') {
                always {
                    sh 'docker logout'
                }
            }
        }
    }
     post {
        failure {
            emailext body: 'Pipeline Job Failures. Please Find The Attached Report.', 
                compressLog: true, 
                subject: 'Pipeline Job Failure Info.', 
                to: params.MAIL_ID
        }
    }
}
