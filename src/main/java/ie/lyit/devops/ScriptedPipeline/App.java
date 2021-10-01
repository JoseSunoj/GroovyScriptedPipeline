package ie.lyit.devops.ScriptedPipeline;

/**
 * Triangle App
 * 
 * @author sunoj
 * 
 * @version 0.1
 *
 */
public class App {
	/**
	 * Main method
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Triangle triangle = new Triangle();

		System.out.println(triangle.isTriangle(3, 4, 5));
	}
}
