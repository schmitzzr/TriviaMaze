package com.triviamaze.maze;

import java.io.Serializable;

/**
 * This class contains the
 */
public class Maze implements Serializable {

	/** 2-D array that stores all the rooms in the maze */
	private final Room[][] myRooms;

	/** The current room that the player is in */
	private Room myCurrentRoom;

	/** The coordinates of the final room */
	private final int myEndRow, myEndColumn;

	/**
	 * Constructor for the maze
	 * @param theWidth the desired number of columns in the maze
	 * @param theHeight the desired number of rows in the maze
	 * @param theStartRow the row to start at
	 * @param theStartColumn the column to start at
	 * @param theEndRow the row of the final room
	 * @param theEndColumn the column of the final room
	 */
	public Maze(final int theWidth, final int theHeight,
				final int theStartRow, final int theStartColumn,
				final int theEndRow, final int theEndColumn) {
		myRooms = new Room[theHeight][theWidth];
		for(int i = 0; i < theHeight; i++) {
			for(int j = 0; j < theWidth; j++) {
				this.myRooms[i][j]=new Room(i,j);//initial the maze.

				if(i == 0) { // Checks if the room is on the top or bottom edge
					myRooms[i][j].getMyBridgeN().setStatus(false);
				} else if (i == theHeight - 1) {
					myRooms[i][j].getMyBridgeS().setStatus(false);
				}

				if(j == 0) { // Checks if the room is on the left or right edge
					myRooms[i][j].getMyBridgeW().setStatus(false);
				} else if (j == theWidth - 1) {
					myRooms[i][j].getMyBridgeE().setStatus(false);
				}
			}
		}

		myCurrentRoom = myRooms[theStartRow][theStartColumn];

		if (theEndRow >= theHeight || theEndColumn >= theWidth) {
			throw new IndexOutOfBoundsException("Finishing row or column out of bounds");
		} else {
			myEndRow = theEndRow;
			myEndColumn = theEndColumn;
		}
	}

	/**
	 * Checks if the current room is the last room in the maze.
	 * @return true if the current room is at the end of the maze, else return false
	 */
	public boolean isAtEnd() {
		return myCurrentRoom.getMyRow() == myEndRow && myCurrentRoom.getMyColumn()== myEndColumn;
	}

	/**
	 * Checks if the inputted coordinates are a valid location in the maze.
	 * @param theRow the row of the room
	 * @param theColumn the column of the room
	 * @return true if the position is valid, else return false
	 */
	public boolean isPositionValid(final int theRow, final int theColumn) {
		//return true if the position is a valid position in the maze.
		return theRow>=0 && theColumn>=0 && theRow < this.myRooms.length && theColumn < this.myRooms[0].length;
	}

	/**
	 * Checks if the room coordinates inputted are valid, and then returns the room if so.
	 * Returns null if it isn't valid.
	 * @param theRow the row of the room
	 * @param theColumn the column of the room
	 * @return the room with the desired coordinates, and null if the coordinates are invalid
	 */
	public Room getRoom(final int theRow, final int theColumn) {
		if(this.isPositionValid(theRow, theColumn))
			return this.myRooms[theRow][theColumn]; //return the room at index x,y
		return null; //return null if the position is invalid.
	}

	/**
	 * Getter for the current room
	 * @return the current room
	 */
	public Room getMyCurrentRoom() {
		return myCurrentRoom;
	}

	/**
	 * Chooses the bridge that the user wishes to cross and checks its question status.
	 * If the question has been solved, they may proceed.  Otherwise, it will be unsuccessful.
	 * @param theDirection the direction that the user wishes to move
	 */
	public void moveLocation(final Direction theDirection) {
		switch(theDirection) {
			case EAST -> {
				if (myCurrentRoom.getMyBridgeE().getSolvedStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() + 1];
			}
			case WEST -> {
				if (myCurrentRoom.getMyBridgeW().getSolvedStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow()][myCurrentRoom.getMyColumn() - 1];
			}
			case NORTH -> {
				if (myCurrentRoom.getMyBridgeN().getSolvedStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow() - 1][myCurrentRoom.getMyColumn()];
			}
			case SOUTH -> {
				if (myCurrentRoom.getMyBridgeS().getSolvedStatus())
					myCurrentRoom = myRooms[myCurrentRoom.getMyRow() + 1][myCurrentRoom.getMyColumn()];
			}
		}
	}

	/**
	 * Takes a bridge adjacent to the current room and either breaks it or solves it.
	 * The former prevents the player from ever crossing the bridge, while the latter allows them to freely cross.
	 * Any changes will also affect the room across the bridge specified.
	 * @param theDirection the direction of the bridge to break or solve
	 * @param theStatus the status of the bridge to be set (true to solve, false to break)
	 */
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

		if (!checkAbilityToContinue()) {
			printYouLost();
		}
	}

	/**
	 * Setter for the current room, mostly for testing purposes.
	 * @param theRow the row to set the current room in
	 * @param theColumn the column to set the current room in
	 */
	public void setMyCurrentRoom(final int theRow, final int theColumn) {
		myCurrentRoom = myRooms[theRow][theColumn];
	}

	/**
	 * Checks if it is possible for the player to progress any further by checking if there are still bridges.
	 * @return true if the player can still progress, false otherwise
	 */
	public boolean checkAbilityToContinue() {
		return myCurrentRoom.getMyBridgeN().getOpenStatus()
				|| myCurrentRoom.getMyBridgeE().getOpenStatus()
				|| myCurrentRoom.getMyBridgeW().getOpenStatus()
				|| myCurrentRoom.getMyBridgeS().getOpenStatus();
	}

	public void printYouLost() {
		System.out.println("You lost :(");
	}

	/**
	 * Getter for the 2-D array of rooms
	 * @return the 2-D array of rooms
	 */
	public Room[][] getRooms(){
		return this.myRooms;//return all the rooms.
	}

}
