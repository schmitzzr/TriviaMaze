package com.triviamaze.maze;

public class Player {
	private int myRow, myColumn;
	public Player(int theRow,int theColumn) {
		this.myRow = theRow;
		this.myColumn = theColumn;
	}
	public int getRow() {
		//getter of X.
		return this.myRow;
	}
	public int getColumn() {
		//getter of Y.
		return this.myColumn;
	}

	public void moveEast() {
		myColumn++;
	}

	public void moveWest() {
		myColumn--;
	}

	public void moveNorth() {
		myRow--;
	}

	public void moveSouth() {
		myRow++;
	}

	public void setRow(int theRow) {
		//setter of X.
		this.myRow = theRow;
	}
	public void setColumn(int theColumn) {
		//setter of Y.
		this.myColumn = theColumn;
	}

	public String toString() {
		return "Current player position: (" + myRow + ", " + myColumn + ")";
	}

}
