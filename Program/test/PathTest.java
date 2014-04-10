import static org.junit.Assert.*;

import org.junit.Test;

import domain.Path;


public class PathTest {

	@Test
	/** Tests if a path when compared to a path with smaller distance returns 1,
	 * 	to a path with equal distance return 0 and to a path with bigger distance return -1*/
	public void testCompareTo() {
		Path tester = new Path();
		tester.setDist(5);
		
		Path smaller = new Path();
		smaller.setDist(2);
		
		Path equal = new Path();
		equal.setDist(5);
		
		Path bigger = new Path();
		bigger.setDist(10);
		
		assertEquals("tester.compareTo(smaller) should return 1", 1, tester.compareTo(smaller));
		assertEquals("tester.compareTo(equal) should return 0", 0, tester.compareTo(equal));
		assertEquals("tester.compareTo(bigger) should return -1", -1, tester.compareTo(bigger));
	}
	
	@Test
	/** Tests if the method addStep(Point step, int dist) adds a Point to the field steps
	 *  and adds dist to the field dist */
	public void testAddStep(){
		Path tester = new Path();
		tester.addStep(1, 2);
		tester.addStep(3, 6);
		
		
		assertEquals("tester.getX().get(0) should return 1", 1, tester.getX().get(0).intValue());
		assertEquals("tester.getY().get(0) should return 2",  2, tester.getY().get(0).intValue());
		assertEquals("tester.getX().get(1) should return 3", 3, tester.getX().get(1).intValue());
		assertEquals("tester.getY().get(1) should return 6", 6, tester.getY().get(1).intValue());
		assertEquals("tester.getDist should return 2", 2, tester.getDist());
		
	}

}
