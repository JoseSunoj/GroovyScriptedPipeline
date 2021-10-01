/**
 * 
 */
package ie.lyit.devops.ScriptedPipeline;

/**
 * A Java program for basic triangle functions, such as determine the given
 * edges forms a triangle, find area etc.
 * 
 * @author sunoj
 * 
 */
public class Triangle {
	/**
	 * A method to test whether the three given edges forms a triangle
	 * 
	 * @param a length of edge-1
	 * @param b length of edge-2
	 * @param c length of edge-3
	 * @return true or false
	 */
	public boolean isTriangle(double a, double b, double c) {
		return (a + b > c) && (b + c > a) && (a + c > b);
	}
}
