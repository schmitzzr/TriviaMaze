package com.triviamaze.maze;

public class Player {
	private int CoordX,CoordY;
	public Player(int x,int y) {
		this.CoordX=x;
		this.CoordY=y;
	}
	public int getX() {
		//getter of X.
		return this.CoordX;
	}
	public int getY() {
		//getter of Y.
		return this.CoordY;
	}
	public void setX(int x) {
		//setter of X.
		this.CoordX=x;
	}
	public void setY(int y) {
		//setter of Y.
		this.CoordY=y;
	}
}
