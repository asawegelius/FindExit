import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Test;

import dao.MazeReader;
import domain.Maze;


public class MazeReaderTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testGetMaces(){

		MazeReader tester = new MazeReader();
		@SuppressWarnings("unused")
		boolean[][] horizontal = {{false, false, true, false, false, false},
								{false, false, false, false, true, false},
								{true, true, false, true, false, true},
								{false, true, true, false, true, true},
								{true, false, false, true, true, true},
								{false, true, false, false, false, false}};
		boolean[][] bigHorizontal = {{false, false, false, false, false, true, false, false, false, false, false, false, false},
									{true, true, true, true, false, true, true, false, false, true, false, false, true},
									{true, false, false, true, false, false, false, true, true, true, true, true, true},
									{false, true, false, false, false, false, false, false, false, false, true, false, true},
									{true, false, false, true, false, false, true, false, false, true, true, false, false},
									{false, false, true, true, true, true, true, false, true, true, true, true, true},
									{true, true, true, true, true, true, true, false, true, true, true, true, true},
									{true, true, false, true, false, false, true, false, false, true, false, false, true},
									{true, false, true, false, false, false, false, false, false, false, true, false, false},
									{true, true, false, false, false, true, true, false, false, false, true, false, true},
									{false, false, false, true, false, false, false, false, false, false, false, false, false}};
		
		
		@SuppressWarnings("unused")
		boolean[][] vertical ={{false, true, true, true, true, true, false},
								{false, true, false, true, false, true, false},
								{false, false, false, true, true, false, false},
								{false, true, false, true, false, false, false},
								{false, true, true, true, false, true, false}};
		
		boolean[][] bigVertical = {{false, true, false, false, true, false, true, true, true, true, false, true, true, false},
									{false, false, true, false, false, true, true, false, true, true, false, true, false, false},
									{false, true, true, true, true, true, true, true, false, true, true, false, true, false},
									{false, true, true, false, true, true, true, true, true, true, false, false, true, false},
									{false, true, true, false, false, true, true, true, true, false, false, false, true, false},
									{false, true, false, false, false, true, true, true, true, false, false, true, false, false},
									{false, false, false, false, false, true, true, true, true, false, false, false, false, false},
									{false, true, false, true, true, true, true, true, true, true, true, true, true, false},
									{false, true, false, true, true, true, false, true, true, true, true, true, false, false},
									{ false, false, true, true, true, true, false, false, true, true, true, true, true, false}};
		
		       
		

		
	
		ArrayList<Maze> actual = tester.getMaces("files\\bigMaze.csv");
		assertEquals("height should be 11", 11, actual.get(0).getHeight());
		assertEquals("width should be 14", 14, actual.get(0).getWidth());
		assertEquals("not the same horizontal", bigHorizontal, actual.get(0).getHorizontal());
		assertEquals("not the same vertical", bigVertical, actual.get(0).getVertical());
	}
	
	
	@SuppressWarnings("deprecation")
	public void testGetWalls(){
		MazeReader tester = new MazeReader();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("files\\testReader.csv"));
		
		boolean[][] expected = 	{{false, true, false, false, false, false},
								{true, false, false, true, true, true},
								{false, true, true, false, true, true},
								{true, true, false, true, false, true},
								{false, false, false, false, true, false},
								{false, false, true, false, false, false}};
		assertEquals("1 in file must be false and 0 true", expected, tester.getWalls(reader, 6));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetRow(){
		MazeReader tester = new MazeReader();
		String row = "4, 1, 0, 0, 1";
		boolean[] actual = tester.getRow(row);
		assertEquals("actual.length must give 4", 4, actual.length);
		assertEquals("1 must give false", false, actual[0]);
		assertEquals("0 must give true", true, actual[1]);
		assertEquals("0 must give true", true, actual[1]);
		assertEquals("1 must give false", false, actual[0]);
	}

	
	@Test
	public void testIsInteger(){
		MazeReader tester = new MazeReader();
		assertEquals("\"1\" isInteger must be true", true, tester.isInteger("1"));
		assertEquals("one isInteger must be false", false, tester.isInteger("one"));
	}
}
