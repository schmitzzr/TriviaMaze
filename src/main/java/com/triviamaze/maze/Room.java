package com.triviamaze.maze;

public class Room {

	private final int myRow, myColumn;

	private final Bridge myBridgeN, myBridgeS, myBridgeE, myBridgeW;

	public Room(int theRow,int theColumn) {
		this.myRow =theRow;
		this.myColumn =theColumn;
		//initial four bridge of the room.
		this.myBridgeN =new Bridge(true,false);
		this.myBridgeS =new Bridge(true,false);
		this.myBridgeE =new Bridge(false,true);
		this.myBridgeW =new Bridge(false,true);
	}

	public int getMyRow() {
		return this.myRow;
	}

	public int getMyColumn() {
		return this.myColumn;
	}

	public void setBridgeStatus(Direction theDirection, boolean theStatus) {
		switch(theDirection) {
			case NORTH -> {
				myBridgeN.setStatus(theStatus);
			}
			case EAST -> {
				myBridgeE.setStatus(theStatus);
			}
			case WEST -> {
				myBridgeW.setStatus(theStatus);
			}
			case SOUTH -> {
				myBridgeS.setStatus(theStatus);
			}
		}
	}


	public Bridge getMyBridgeN() {
		return this.myBridgeN;
	}

	public Bridge getMyBridgeW() {
		return this.myBridgeW;
	}

	public Bridge getMyBridgeE() {
		return this.myBridgeE;
	}

	public Bridge getMyBridgeS() {
		return this.myBridgeS;
	}

	public String toString() {
		String position = "Room position: (" + myRow + ", " + myColumn + ")\n";
		String bridgeN = "North Bridge: " + myBridgeN;
		String bridgeE = "East Bridge: " + myBridgeE;
		String bridgeW = "West Bridge: " + myBridgeW;
		String bridgeS = "South Bridge: " + myBridgeS;

		return position + bridgeN + bridgeE + bridgeW + bridgeS;
	}

}
