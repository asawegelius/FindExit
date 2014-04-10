package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import domain.Maze;

/**	<h1>MazeReader</h1>
 * 	@author Åsa Wegelius
 * 	<p>Extracts mazes from a csv file. The csv file represents a maze by: </p>
 * 	Number of horizontal lines<br>
 * 	Line number, Number for walls (0: none(open), 1:wall separated by ,)<br>
 * 	.. .. ..<br>
 * 	 Number of vertical lines<br>
 * 	Line number, number for walls (0: none(open), 1:wall separated by ,)<br>
 * */
public class MazeReader {	
	
	
	/**	Collects all the mazes in the file.
	 * 	@param file the file that stores the mazes
	 * 	@return an ArrayList with maze objects
	 * 	@see Maze */
	public ArrayList<Maze> getMaces(String file){
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String line;
			// there is a maze stored in the file
			while ((line = reader.readLine()) != null) {
				Maze maze = new Maze();
				// first row gives the height of the maze
				if(isInteger(line.trim())){
					maze.setHeight(new Integer(line.trim()));
					maze.setHorizontal(mirrorWall(getWalls(reader, maze.getHeight())));
				}
				// after all horizontal lines are read next line gives
				// the width of the maze
				line = reader.readLine();
				if(isInteger(line.trim())){
					maze.setWidth(new Integer(line.trim()));
					maze.setVertical(mirrorWall(transposeWall(getWalls(reader, maze.getWidth()))));
				}
				// all info for the maze are collected
				mazes.add(maze);
			}
		}
		catch(FileNotFoundException ioEx){
			System.err.println("Could not find file " + file);
		} catch (IOException e) {
			System.err.println("Unable to read file" + file);
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mazes;
	}
	
	/** Extracts information about the wall rows from a file. 
	 * 	@param reader the BufferedReader connected to the file
	 * 	@param height how many rows that represent the walls
	 * 	@return 2d array with false for 1 in the file and true for 0*/
	public boolean[][] getWalls(BufferedReader reader, int height){
		boolean[][] walls = null;
		try{
			boolean[] first = getRow(reader.readLine());
			walls = new boolean[height][first.length];
			walls[0] = first;
			for(int i = 1; i < height; i++){
				walls[i] = getRow(reader.readLine());
			}
		}
		catch(IOException ioEx){
			System.err.println("Problem reading the file");
		}
		return walls;
	}
	
	/** Takes a String with 1 and 0 separated with , and returns
	 * 	a boolean array with false for 1 and true for 0 
	 * 	@param row the string to translate
	 * 	@return a boolean array storing true for 0 and false for 1*/
	public boolean[] getRow(String row){
		StringTokenizer reader = new StringTokenizer(row, ",");
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		String next= reader.nextToken().trim();
		while(reader.hasMoreTokens()){
			next = reader.nextToken().trim();
			if(next.equals("1")){
				list.add(false);
			} else{
				list.add(true);
			}
		}
		boolean[] walls = new boolean[list.size()];
		for(int i = 0; i < walls.length; i++){
			walls[i] = list.get(i);
		}
		return walls;
	}
	
	/** Rotates the rows in a 2d array 90 degrees counter clock wise. 
	 * 	@param wall the boolean array to rotate
	 * 	@return an array with the same result but rotated. */
	public boolean[][] transposeWall(boolean[][] wall){
		boolean[][] transposed = new boolean[wall[0].length][wall.length];
		for(int i = 0; i < wall[0].length; i++){
			for(int j = 0; j < wall.length; j++){
				transposed[i][j] = wall[j][i];
			}
		}
		return transposed;
	}
	
	/** Mirror the rows in a 2d array.
	 * 	@param wall The boolean array to mirror.
	 * 	@return A boolean array with the same result but mirrored.*/
	public boolean[][] mirrorWall(boolean[][] wall){
		boolean[][] mirrored = new boolean[wall.length][wall[0].length];
		for(int i = 0; i < wall.length; i++){
			mirrored[i] = wall[wall.length - 1 - i];
		}
		return mirrored;
	}
	
	/** Checks if a String can be turned into an integer
	 * 	@param i The string to check.
	 * 	@return true if the string contains an integer and otherwise false.*/
	public boolean isInteger(String i) {		
		try{
			new Integer(i);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

}
