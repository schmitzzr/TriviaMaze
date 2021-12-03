package com.triviamaze.maze;

public class Room {
	int CoordX,CoordY;
	Bridge bridgeN,bridgeS,bridgeE,bridgeW;
	public Room(int CoordX,int CoordY) {
		this.CoordX=CoordX;
		this.CoordY=CoordY;
		//initial four bridge of the room.
		this.bridgeN=new Bridge();
		this.bridgeS=new Bridge();
		this.bridgeE=new Bridge();
		this.bridgeW=new Bridge();
	}
	public int getCoordX() {
		return this.CoordX;
	}
	public int getCoordY() {
		return this.CoordY;
	}
	public Bridge getBridgeN() {
		//getter of BridgeN
		return this.bridgeN;
	}
	public Bridge getBridgeW() {
		//getter of BridgeW
		return this.bridgeW;
	}
	public Bridge getBridgeE() {
		//getter of BridgeE
		return this.bridgeE;
	}
	public Bridge getBridgeS() {
		//getter of BridgeS
		return this.bridgeS;
	}
}