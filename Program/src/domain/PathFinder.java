package domain;

import java.util.PriorityQueue;

/**	<h1> PathFinder </h1>	
 * 	The PathFinder finds the shortest paths in mazes with an entrance at the bottom.  
 * 	@author Åsa Wegelius */
public class PathFinder {
	private PriorityQueue<Path> paths; 	// the shortest path in progress is always in front in the queue
	private boolean found = false;		// true if a path is found
	private int startx;
	private int starty;
	Path foundExit;
	
	/** Returns all paths in progress ordered by length, shortest first. */
	public PriorityQueue<Path> getPaths() {
		return paths;
	}
	
	
	/** Finds the shortest path in a maze. 
	 * 	@param maze the given maze to search
	 * 	@return returns the shortest path found. */
	public Path shortestPath(Maze maze){
		initialize(maze);
		while(!found){
			addForks(maze, paths.poll());
		}
		return foundExit;
	}
	
	/** Puts the starting step into the empty queue of paths
	 * 	to inspect. It is assumed to be in the bottom of the maze.
	 * 	@param maze the given maze to search*/
	public void initialize(Maze maze){
		if(paths != null)
			paths.clear();
		else
			paths = new PriorityQueue<Path>();
		int x = 0;
		while(!maze.getHorizontal()[maze.getHeight()-1][x])
			x++;
		Path first = new Path();
		boolean[][] visited = new boolean[maze.getHorizontal().length][maze.getHorizontal()[0].length];
		for(int i = 0; i < visited.length; i++){
			visited[i] = new boolean[maze.getHorizontal()[0].length];
		}
		first.setVisited(visited);
		first.addStep(x, maze.getHeight()-2);
		first.setPrevx(x);
		first.setPrevy(maze.getHeight() - 1);
		startx = x;
		starty = maze.getHeight()-2;
		paths.add(first);
	}
	
	/** Adds all extended paths the given path can lead to and sets found to true if the exit is found. 
	 * 	@param maze The maze under investigation.
	 * 	@param path	The sub graph to extend if possible.*/
	public void addForks(Maze maze, Path path){
		int lastx = path.getX().get(path.getX().size()-1);
		int lasty = path.getY().get(path.getY().size()-1);
		int dx = lastx - path.getPrevx();
		int dy = lasty - path.getPrevy();
		// comes from down so check left, up and right
		if(dy == -1){
			// go left
			if(maze.getVertical()[lasty][lastx]){
				if(lastx == 0){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx - 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx - 1, lasty);
					newPath.setVisited(lastx - 1, lasty);
					paths.add(newPath);
				}
			}
			// go up
			if(maze.getHorizontal()[lasty][lastx]){
				if(lasty == 0){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx, lasty - 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty - 1);
					paths.add(newPath);
				}
			}
			//go right
			if(maze.getVertical()[lasty][lastx + 1]){
				if(lastx + 1 == maze.getWidth()){
					found = true;
					foundExit = path;
					return;
				}
					if(!path.visitStatus(lastx + 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx + 1, lasty);
					paths.add(newPath);
				}
			}
		}
		//comes from right, so check up, down and left
		else if(dx == -1){
			// go up
			if(maze.getHorizontal()[lasty][lastx]){
				if(lasty  == 0){
					found = true;
					foundExit = path;
					return;
				}	
				if(!path.visitStatus(lastx, lasty - 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty - 1);
					paths.add(newPath);
				}
			}
			//go down
			if(maze.getHorizontal()[lasty + 1][lastx]){
				if(lasty + 2 == maze.getHeight() ){
					if(lasty  != starty && lastx != startx){
						found = true;
						foundExit = path;
					}
					return;
				}
				if(!path.visitStatus(lastx, lasty + 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty + 1);
					paths.add(newPath);
				}
			}	
			// go left
			if(maze.getVertical()[lasty][lastx]){
				if(lastx == 0){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx - 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx - 1, lasty);
					newPath.setVisited(lastx - 1, lasty);
					paths.add(newPath);
				}
			}	
		}
		//comes from up so check left, right and up
		else if(dy == 1){
			// go left
			if(maze.getVertical()[lasty][lastx]){
				if(lastx == 0){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx - 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx - 1, lasty);
					newPath.setVisited(lastx - 1, lasty);
					paths.add(newPath);
				}
			}
			//go right
			if(maze.getVertical()[lasty][lastx + 1]){
				if(lastx + 1 == maze.getWidth()){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx + 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx + 1, lasty);
					paths.add(newPath);
				}
			}
			//go down
			if(maze.getHorizontal()[lasty + 1][lastx]){
				if(lasty + 2 == maze.getHeight() ){
					if(lasty  != starty && lastx != startx){
						found = true;
						foundExit = path;
					}
					return;
				}
				if(!path.visitStatus(lastx, lasty + 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty + 1);
					paths.add(newPath);
				}
			}	
			
		}
		//comes from left so check top, right and down
		else if(dx == 1){
			// go up
			if(maze.getHorizontal()[lasty][lastx]){
				if(lasty  == 0){
					found = true;
					foundExit = path;
					return;
				}	
				if(!path.visitStatus(lastx, lasty - 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty - 1);
					paths.add(newPath);
				}
			}	
			//go right
			if(maze.getVertical()[lasty][lastx + 1]){
				if(lastx + 1 == maze.getWidth()){
					found = true;
					foundExit = path;
					return;
				}
				if(!path.visitStatus(lastx + 1, lasty)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx + 1, lasty);
					paths.add(newPath);
				}
			}
			//go down
			if(maze.getHorizontal()[lasty + 1][lastx]){
				if(lasty + 2 == maze.getHeight()){
					if(lasty  != starty && lastx != startx){
						found = true;
						foundExit = path;
					}
					return;
				}
				if(!path.visitStatus(lastx, lasty + 1)){
					Path newPath = path.clone();
					newPath.setPrevx(lastx);
					newPath.setPrevy(lasty);
					newPath.addStep(lastx, lasty + 1);
					paths.add(newPath);
				}
			}	
		}

	}
}
