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
class TriangleTest {

	private static Triangle triangle;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		triangle = new Triangle();
	}

	/**
	 * Test method for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isTriangle(double, double, double)}.
	 */
	@Test
	void testIsTriangle() {
		assertTrue(triangle.isTriangle(3, 4, 5));
	}

	/**
	 * Test method two for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isTriangle(double, double, double)}.
	 */
	@Test
	void testTwoIsTriangle() {
		assertFalse(triangle.isTriangle(13, 14, 1));
	}

	/**
	 * Test method three for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isTriangle(double, double, double)}.
	 */
	@Test
	void testThreeIsTriangle() {
		assertFalse(triangle.isTriangle(20, 11, 5));
	}

	/**
	 * Test method four for
	 * {@link ie.lyit.devops.ScriptedPipeline.Triangle#isTriangle(double, double, double)}.
	 */
	@Test
	void testFourIsTriangle() {
		assertFalse(triangle.isTriangle(10, 10, 30));
	}

}
