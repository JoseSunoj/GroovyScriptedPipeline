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
class AreaTest {
	private static Triangle triangle;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		triangle = new Triangle();
	}

	/**
	 * Test method for {@link ie.lyit.devops.ScriptedPipeline.Triangle#findArea(double, double, double)}.
	 */
	@Test
	void testFindArea() {
		assertEquals(9.17, triangle.findArea(5, 4, 5));
	}
	
	@Test
	void testTwoFindArea() {
		assertEquals(0.0, triangle.findArea(1, 4, 5));
	}
	
	@Test
	void testThreeFindArea() {
		assertEquals(6000000.0, triangle.findArea(3000, 4000, 5000));
	}

}
