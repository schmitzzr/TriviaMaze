package com.triviamaze.maze;

public class Maze {

	Room[][] myRooms;
	Player myPlayer;
	Room myCurrentRoom;

	int myEndRow, myEndColumn;

	public Maze(int width, int height, int theStartRow,int theStartColumn,int theEndRow, int theEndColumn) {
		myRooms = new Room[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				this.myRooms[i][j]=new Room(i,j);//initial the maze.

				if(i == 0) { // Checks if the room is on the top or bottom edge
					myRooms[i][j].getMyBridgeN().setStatus(false);
				} else if (i == height - 1) {
					myRooms[i][j].getMyBridgeS().setStatus(false);
				}

				if(j == 0) {
					myRooms[i][j].getMyBridgeW().setStatus(false);
				} else if (j == width - 1) {
					myRooms[i][j].getMyBridgeE().setStatus(false);
				}
			}
		}

		myCurrentRoom = myRooms[theStartRow][theStartColumn];

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

//	public Player getPlayer() {
//		return myPlayer;
//	}

	public boolean isPositionValid(int x,int y) {
		//return true if the position is a valid position in the maze.
		return x>=0 && y>=0 && x < this.myRooms.length && y < this.myRooms[0].length;
	}

	public Room getRoom(final int theColumn, final int theRow) {
		if(this.isPositionValid(theRow, theColumn))
			return this.myRooms[theRow][theColumn];//return the room at index x,y
		return null;//return null if the position is invalid.
	}

	public Room getMyCurrentRoom() {
		return myCurrentRoom;
	}

	public void moveLocation(final Direction theDirection) {
		switch(theDirection) {
			case EAST -> {
				if (myCurrentRoom.getMyBridgeE().getQuestionStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() + 1];
			}
			case WEST -> {
				if (myCurrentRoom.getMyBridgeW().getQuestionStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() - 1];
			}
			case NORTH -> {
				if (myCurrentRoom.getMyBridgeN().getQuestionStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow() - 1][myCurrentRoom.getMyColumn()];
			}
			case SOUTH -> {
				if (myCurrentRoom.getMyBridgeS().getQuestionStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow() + 1][myCurrentRoom.getMyColumn()];
			}
			default -> {}
		}
	}

	public void breakOrSolveBridge(final Direction theDirection, final boolean theStatus) {

		Room adjacentRoom;

		switch(theDirection) {
			case EAST -> {
				myCurrentRoom.getMyBridgeE().setStatus(theStatus);
				adjacentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() + 1];
				adjacentRoom.getMyBridgeW().setStatus(theStatus);
			}
			case WEST -> {
				myCurrentRoom.getMyBridgeW().setStatus(theStatus);
				adjacentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() - 1];
				adjacentRoom.getMyBridgeE().setStatus(theStatus);
			}
			case NORTH -> {
				myCurrentRoom.getMyBridgeN().setStatus(theStatus);
				adjacentRoom = myRooms[myCurrentRoom.getMyRow() - 1][myCurrentRoom.getMyColumn()];
				adjacentRoom.getMyBridgeS().setStatus(theStatus);
			}
			case SOUTH -> {
				myCurrentRoom.getMyBridgeS().setStatus(theStatus);
				adjacentRoom = myRooms[myCurrentRoom.getMyRow() + 1][myCurrentRoom.getMyColumn()];
				adjacentRoom.getMyBridgeN().setStatus(theStatus);
			}
			default -> System.out.println("Error: Direction not valid.");
		}
	}

	public void setMyCurrentRoom(final Room theCurrentRoom) {
		myCurrentRoom = theCurrentRoom;
	}

	public boolean checkAbilityToContinue() {
		return myCurrentRoom.getMyBridgeN().getOpenStatus()
				|| myCurrentRoom.getMyBridgeE().getOpenStatus()
				|| myCurrentRoom.getMyBridgeW().getOpenStatus()
				|| myCurrentRoom.getMyBridgeS().getOpenStatus();
	}

	public Room[][] getRooms(){
		return this.myRooms;//return all the rooms.
	}

}
