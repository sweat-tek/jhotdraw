package CH.ifa.draw.test.standard;

import junit.framework.TestCase;
// JUnitDoclet begin import
import CH.ifa.draw.standard.SelectionTool;
import CH.ifa.draw.test.JHDTestCase;
// JUnitDoclet end import

/*
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


// JUnitDoclet begin javadoc_class
/**
* TestCase SelectionToolTest is generated by
* JUnitDoclet to hold the tests for SelectionTool.
* @see CH.ifa.draw.standard.SelectionTool
*/
// JUnitDoclet end javadoc_class
public class SelectionToolTest
// JUnitDoclet begin extends_implements
extends JHDTestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  // instance variables, helper methods, ... put them in this marker
  CH.ifa.draw.standard.SelectionTool selectiontool = null;
  // JUnitDoclet end class
  
  /**
  * Constructor SelectionToolTest is
  * basically calling the inherited constructor to
  * initiate the TestCase for use by the Framework.
  */
  public SelectionToolTest(String name) {
    // JUnitDoclet begin method SelectionToolTest
    super(name);
    // JUnitDoclet end method SelectionToolTest
  }
  
  /**
  * Factory method for instances of the class to be tested.
  */
  public CH.ifa.draw.standard.SelectionTool createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new CH.ifa.draw.standard.SelectionTool(getDrawingEditor());
    // JUnitDoclet end method testcase.createInstance
  }
  
  /**
  * Method setUp is overwriting the framework method to
  * prepare an instance of this TestCase for a single test.
  * It's called from the JUnit framework only.
  */
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    selectiontool = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  /**
  * Method tearDown is overwriting the framework method to
  * clean up after each single test of this TestCase.
  * It's called from the JUnit framework only.
  */
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    selectiontool = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  // JUnitDoclet begin javadoc_method mouseDown()
  /**
  * Method testMouseDown is testing mouseDown
  * @see CH.ifa.draw.standard.SelectionTool#mouseDown(java.awt.event.MouseEvent, int, int)
  */
  // JUnitDoclet end javadoc_method mouseDown()
  public void testMouseDown() throws Exception {
    // JUnitDoclet begin method mouseDown
    // JUnitDoclet end method mouseDown
  }
  
  // JUnitDoclet begin javadoc_method mouseMove()
  /**
  * Method testMouseMove is testing mouseMove
  * @see CH.ifa.draw.standard.SelectionTool#mouseMove(java.awt.event.MouseEvent, int, int)
  */
  // JUnitDoclet end javadoc_method mouseMove()
  public void testMouseMove() throws Exception {
    // JUnitDoclet begin method mouseMove
    // JUnitDoclet end method mouseMove
  }
  
  // JUnitDoclet begin javadoc_method mouseDrag()
  /**
  * Method testMouseDrag is testing mouseDrag
  * @see CH.ifa.draw.standard.SelectionTool#mouseDrag(java.awt.event.MouseEvent, int, int)
  */
  // JUnitDoclet end javadoc_method mouseDrag()
  public void testMouseDrag() throws Exception {
    // JUnitDoclet begin method mouseDrag
    // JUnitDoclet end method mouseDrag
  }
  
  // JUnitDoclet begin javadoc_method mouseUp()
  /**
  * Method testMouseUp is testing mouseUp
  * @see CH.ifa.draw.standard.SelectionTool#mouseUp(java.awt.event.MouseEvent, int, int)
  */
  // JUnitDoclet end javadoc_method mouseUp()
  public void testMouseUp() throws Exception {
    // JUnitDoclet begin method mouseUp
    // JUnitDoclet end method mouseUp
  }
  
  
  
  // JUnitDoclet begin javadoc_method testVault
  /**
  * JUnitDoclet moves marker to this method, if there is not match
  * for them in the regenerated code and if the marker is not empty.
  * This way, no test gets lost when regenerating after renaming.
  * <b>Method testVault is supposed to be empty.</b>
  */
  // JUnitDoclet end javadoc_method testVault
  public void testVault() throws Exception {
    // JUnitDoclet begin method testcase.testVault
    // JUnitDoclet end method testcase.testVault
  }
  
  /**
  * Method to execute the TestCase from command line
  * using JUnit's textui.TestRunner .
  */
  public static void main(String[] args) {
    // JUnitDoclet begin method testcase.main
    junit.textui.TestRunner.run(SelectionToolTest.class);
    // JUnitDoclet end method testcase.main
  }
}
