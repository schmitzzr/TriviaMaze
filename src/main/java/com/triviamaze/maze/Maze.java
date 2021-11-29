package com.triviamaze.maze;

public class Maze {

	Room[][] myRooms;
	Player myPlayer;

	int myEndRow, myEndColumn;

	public Maze(int width, int height, int theStartRow,int theStartColumn,int theEndRow, int theEndColumn) {
		myRooms = new Room[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				this.myRooms[i][j]=new Room(i,j);//initial the maze.
			}
		}

		if (theEndRow >= height || theEndColumn >= width) {
			throw new IndexOutOfBoundsException("Finishing row or column out of bounds");
		} else {
			myEndRow = theEndRow;
			myEndColumn = theEndColumn;
		}
		myPlayer = new Player(theStartRow, theStartColumn);
	}
	public boolean isAtEnd() {
		//if the player is at the end of the maze,return true,else return false.
		return myPlayer.getRow()== myEndRow && myPlayer.getColumn()== myEndColumn;
	}

	public Player getPlayer() {
		return myPlayer;
	}

	public boolean isPositionValid(int x,int y) {
		//return true if the position is a valid position in the maze.
		return x>=0 && y>=0 && x < this.myRooms.length && y < this.myRooms[0].length;
	}

	public Room getRoom(int theColumn,int theRow) {
		if(this.isPositionValid(theRow, theColumn))
			return this.myRooms[theRow][theColumn];//return the room at index x,y
		return null;//return null if the position is invalid.
	}

	public Room[][] getRooms(){
		return this.myRooms;//return all the rooms.
	}

}
