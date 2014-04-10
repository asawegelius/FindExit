package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Path implements Comparable<Path>{
	private ArrayList<Integer> x = new ArrayList<Integer>();
	private ArrayList<Integer> y = new ArrayList<Integer>();
	private boolean[][] visited;
	private int prevx = -1;
	private int prevy = -1;
	private int dist;
	
	
	/** Adds a step and increase dist with 1 */ 
	public void addStep(int x, int y){
		this.x.add(x);
		this.y.add(y);
		this.dist ++;
	}
	
	public boolean[][] getVisited() {
		return visited;
	}

	public void setVisited(boolean[][] visited) {
		this.visited = visited;
	}
	
	public void setVisited(int x, int y){
		visited[y][x] = true;
	}
	
	public boolean visitStatus(int x, int y){
		return visited[y][x];
	}

	/** Adds one to x and one to y */
	public void add(int x, int y){
		this.x.add(x);
		this.y.add(y);
	}
	
	
	/** Returns the int distance from the starting Point */
	public int getDist() {
		return dist;
	}
	
	/** Sets the distance from the starting Point to be dist */
	public void setDist(int dist) {
		this.dist = dist;
	}
	

	@Override
	public int compareTo(Path other) {
		if(dist < other.dist){
			return -1;
		} else if (dist == other.dist){
			return 0;
		} else{
			return 1;
		}
	}




	public ArrayList<Integer> getX() {
		return x;
	}




	public void setX(ArrayList<Integer> x) {
		this.x = x;
	}




	public ArrayList<Integer> getY() {
		return y;
	}




	public void setY(ArrayList<Integer> y) {
		this.y = y;
	}




	public int getPrevx() {
		return prevx;
	}




	public void setPrevx(int prevx) {
		this.prevx = prevx;
	}




	public int getPrevy() {
		return prevy;
	}




	public void setPrevy(int prevy) {
		this.prevy = prevy;
	}
	

	public Path clone(){
		Path clone = new Path();
		clone.setDist(dist);
		clone.setPrevx(prevx);
		clone.setPrevy(prevy);
		for(int i = 0; i < x.size(); i++){
			clone.add(x.get(i).intValue(), y.get(i).intValue());
		}
		if(visited.length > 0){
			boolean[][] visitClone = new boolean[visited.length][visited[0].length];
				for(int j = 0; j < visited.length; j++){
					visitClone[j] = Arrays.copyOf(visited[j], visited[j].length);
			}

			clone.setVisited(visitClone);
		}
		return clone;
	}
}
