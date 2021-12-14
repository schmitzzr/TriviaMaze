package com.triviamaze.maze;

import java.io.Serializable;

public class Room implements Serializable{

	/** The row and column of the room's location */
	private final int myRow, myColumn;

	/** The four bridges connected to the room labeled as their cardinal directions */
	private final Bridge myBridgeN, myBridgeS, myBridgeE, myBridgeW;

	/**
	 * Constructor for the bridge, initializing the fields.
	 * @param theRow the row where the room is set
	 * @param theColumn the column where the room is set
	 */
	public Room(final int theRow, final int theColumn) {
		myRow = theRow;
		myColumn = theColumn;
		//initializes four bridge of the room.
		myBridgeN = new Bridge();
		myBridgeS = new Bridge();
		myBridgeE = new Bridge();
		myBridgeW = new Bridge();
	}

	/**
	 * Getter for the room's row.
	 * @return the row of the room
	 */
	public int getMyRow() {
		return myRow;
	}

	/**
	 * Getter for the room's column.
	 * @return the column of the room
	 */
	public int getMyColumn() {
		return myColumn;
	}

	/**
	 * Sets the status of the bridge in the listed cardinal direction.
	 * @param theDirection the cardinal direction of the bridge
	 * @param theStatus the status of the bridge (true for question solved, false for bridge broken)
	 */
	public void setBridgeStatus(final Direction theDirection, final boolean theStatus) {
		switch(theDirection) {
			case NORTH ->
					myBridgeN.setStatus(theStatus);
			case EAST ->
					myBridgeE.setStatus(theStatus);
			case WEST ->
					myBridgeW.setStatus(theStatus);
			case SOUTH ->
					myBridgeS.setStatus(theStatus);
		}
	}

	/**
	 * Sets the status of the bridge in the listed cardinal direction.
	 * @param theDirection the cardinal direction of the bridge
	 * @param theStatus the status of the bridge (true for question solved, false for bridge broken)
	 */
	public void setBridgeQStatus(final Direction theDirection, final boolean theStatus) {
		switch(theDirection) {
			case NORTH ->
					myBridgeN.setQuestionStatus(theStatus);
			case EAST ->
					myBridgeE.setQuestionStatus(theStatus);
			case WEST ->
					myBridgeW.setQuestionStatus(theStatus);
			case SOUTH ->
					myBridgeS.setQuestionStatus(theStatus);
		}
	}

	/**
	 * Getter for the north bridge.
	 * @return the north bridge
	 */
	public Bridge getMyBridgeN() {
		return this.myBridgeN;
	}

	/**
	 * Getter for the north bridge.
	 * @return the north bridge
	 */
	public Bridge getMyBridgeW() {
		return this.myBridgeW;
	}

	/**
	 * Getter for the north bridge.
	 * @return the north bridge
	 */
	public Bridge getMyBridgeE() {
		return this.myBridgeE;
	}

	/**
	 * Getter for the north bridge.
	 * @return the north bridge
	 */
	public Bridge getMyBridgeS() {
		return this.myBridgeS;
	}

	/**
	 * Creates a string listing the position of the room and all its bridges, for testing purposes.
	 * @return the position of the room and its bridges as a String
	 */
	public String toString() {
		String position = "Room position: (" + myRow + ", " + myColumn + ")\n";
		String bridgeN = "North Bridge: " + myBridgeN;
		String bridgeE = "East Bridge: " + myBridgeE;
		String bridgeW = "West Bridge: " + myBridgeW;
		String bridgeS = "South Bridge: " + myBridgeS;

		return position + bridgeN + bridgeE + bridgeW + bridgeS;
	}

}