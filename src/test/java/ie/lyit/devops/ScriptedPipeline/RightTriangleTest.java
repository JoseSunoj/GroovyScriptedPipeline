/**
 * 
 */
package ie.lyit.devops.ScriptedPipeline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author sunoj
 *
 */
class RightTriangleTest {

	private static Triangle triangle;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		triangle = new Triangle();
	}

	/**
	 * Test method one for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isRightTriangle(double, double, double)}.
	 */

	@Test
	void testIsRightTriangle() {
		assertTrue(triangle.isRightTriangle(3, 4, 5));
	}

	/**
	 * Test method two for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isRightTriangle(double, double, double)}.
	 */
	@Test
	void testTwoIsRightTriangle() {
		assertFalse(triangle.isRightTriangle(5, 4, 5));
	}

	/**
	 * Test method three for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isRightTriangle(double, double, double)}.
	 */
	@Test
	void testThreeIsRightTriangle() {
		assertFalse(triangle.isRightTriangle(1, 4, 5));
	}

}
