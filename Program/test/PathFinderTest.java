import static org.junit.Assert.*;

import org.junit.Test;






import java.util.ArrayList;

import dao.MazeReader;
import domain.Maze;
import domain.Path;
import domain.PathFinder;


public class PathFinderTest {
	
	@Test
	public void testInitialize(){
		PathFinder tester = new PathFinder();
		ArrayList<Maze> mazes = new MazeReader().getMaces("files\\labyrinths.csv");
		
		tester.initialize(mazes.get(0));

		
		assertEquals("first x should be 1", 1, tester.getPaths().peek().getX().get(0).intValue());
		assertEquals("first y should be 4", 4, tester.getPaths().peek().getY().get(0).intValue());
		assertEquals("prev x should be 1", 1, tester.getPaths().peek().getPrevx());
		assertEquals("prev y should be 5", 5, tester.getPaths().peek().getPrevy());
		assertEquals("first distance after initialize the test maze should be 1", 1, tester.getPaths().peek().getDist());
		assertEquals("there should only be one step after initialize the test maze", 1, tester.getPaths().peek().getX().size());
		assertEquals("there should only be one path after initialize the test maze", 1, tester.getPaths().size());

		tester.initialize(mazes.get(0));
		assertEquals("there should only be one path after calling initialize again", 1, tester.getPaths().size());
		
	}
	
	@Test
	public void testAddForks(){	
	
		
		PathFinder tester = new PathFinder();
		Maze maze = new MazeReader().getMaces("files\\labyrinths.csv").get(0);
		tester.initialize(maze);
		
		Path path = tester.getPaths().poll();
		tester.addForks(maze, path);
		assertEquals("there should be 2 paths after addForks", 2, tester.getPaths().size());
		assertEquals("the previous x should be 1", 1, path.getPrevx());
		assertEquals("the previous y should be 5", 5, path.getPrevy());
		assertEquals("the last x should be 1", 1, path.getX().get(path.getX().size()-1).intValue());
		assertEquals("the last y should be 4",  4, path.getY().get(path.getY().size()-1).intValue());
		
		path = tester.getPaths().poll();
		tester.addForks(maze, path);
		

		assertEquals("the previous x should be 1",  1, path.getPrevx());
		assertEquals("the previous y should be 4",  4, path.getPrevy());
		assertEquals("the last x should be 0", 0 , path.getX().get(path.getX().size()-1).intValue());
		assertEquals("the last y should be 4", 4, path.getY().get(path.getY().size()-1).intValue());
	}
	
	public void testShortestPath(){
		
	}
}