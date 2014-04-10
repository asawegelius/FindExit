package gui;

import java.awt.*;
import java.util.ArrayList;

import domain.Maze;
import domain.Path;
/** <h1>MazeWriter</h1>
 * 	@author Åsa Wegelius
 * 	Draws a maze and the shortest path from the start to the exit.
 */
public class MazeWriter extends Canvas{

	private static final long serialVersionUID = 6368170969150451093L;
	private int cellwidth = 40;
	private int cellheight = 40;
	private int offset = 40;
	private Maze maze;
	private Path path;
	
	/** @param maze the maze to draw.*/
	public MazeWriter(Maze maze){
        setSize(300, 500);
        setBackground(Color.white);
        this.maze = maze;
	}
	
	/** Sets the path to draw and repaints the Canvas object. 
	 * 	@param path The path to draw.*/
	public void setPath(Path path){
		this.path = path;
		super.repaint();
	}

	/** Draws a maze with the help of g.drawLine and the path if a path is set
	 * 	with the help of g.drawRect.
	 * 	@param g The Graphic Object
	 * 	@Overrides Canvas.paint */
	public void paint(Graphics g){ 
		g.setColor(Color.black);
		for(int i = 0; i < maze.getHorizontal()[0].length; i++){
			for(int j = 0; j < maze.getHorizontal().length; j++){
				int x = i * cellwidth + offset;
				int y = j* cellheight + offset;
				if(!maze.getHorizontal()[j][i]){
					g.drawLine(x,y,               //start X,Y
                            x + cellwidth,y);  //stop X+width,Y
				}
	
			}
		}
		for(int i = 0; i < maze.getVertical()[0].length; i++)
			for(int j = 0; j < maze.getVertical().length; j++){
				int x = i * cellwidth + offset;
				int y = j* cellheight + offset;
				if(!maze.getVertical()[j][i]){
					g.drawLine(x,y + cellheight,               //start X,Y + height
							x,y );  //stop X,Y
				}
			}
		if(path != null){
			ArrayList<Integer> x = path.getX();
			ArrayList<Integer> y = path.getY();
			for(int i = 0; i < x.size(); i++){
				g.setColor(new Color(0,158,142,30));
				g.fillRect(x.get(i) * cellwidth + offset, y.get(i)*cellheight + offset, cellwidth, cellheight);
			}
		}
	}
}
