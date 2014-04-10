package domain;

/** <h1>Maze</h1>
 * 	@author Åsa Wegelius
 * 	Stores all information about a maze. Horizontal and vertical walls are represented by boolean arrays with true for 
 * 	openings and false for walls. */
public class Maze {
	private boolean[][] horizontal;
	private boolean[][] vertical;
	private int width;
	private int height;
	
	/** Gets the boolean 2d array that represents the horizontal walls.
	 * 	@return A boolean 2d array with true for openings and false for walls. */
	public boolean[][] getHorizontal() {
		return horizontal;
	}
	
	/** Sets the horizontal walls for the maze.
	 * 	@param horizontal A boolean 2d array with true for openings and false for walls.*/
	public void setHorizontal(boolean[][] horizontal) {
		this.horizontal = horizontal;
	}
	
	/** Gets the boolean 2d array that represents the vertical walls.
	 * 	@return A boolean 2d array with true for openings and false for walls.*/
	public boolean[][] getVertical() {
		return vertical;
	}
	
	/** Sets the vertical walls for the maze.
	 * 	@param vertical A boolean 2d array with true for openings and false for walls.*/
	public void setVertical(boolean[][] vertical) {
		this.vertical = vertical;
	}
	
	/** Gets the width of the maze.
	 * 	@return The with.*/
	public int getWidth() {
		return width;
	}
	
	/** Sets the width of the maze.
	 * 	@param width The width. */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/** Gets the height of the maze.
	 * 	@return The height.*/
	public int getHeight() {
		return height;
	}
	
	/** Sets the height of the maze.
	 * 	@param height The height.*/
	public void setHeight(int height) {
		this.height = height;
	}
	

}
