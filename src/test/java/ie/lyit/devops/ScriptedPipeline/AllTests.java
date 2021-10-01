/**
 * 
 */
package ie.lyit.devops.ScriptedPipeline;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ TriangleTest.class })
@SelectPackages("ie.lyit.devops.ScriptedPipeline")
/**
 * Test Suite
 * 
 * @author sunoj
 *
 */
class AllTests {

}
