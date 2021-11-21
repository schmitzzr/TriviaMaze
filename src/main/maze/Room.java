package maze1;

public class Room {
	int CoordX,CoordY;
	Bridge bridgeN,bridgeS,bridgeE,bridgeW;
	public Room(int CoordX,int CoordY) {
		this.CoordX=CoordX;
		this.CoordY=CoordY;
		this.bridgeN=new Bridge(true,false);
		this.bridgeS=new Bridge(true,false);
		this.bridgeE=new Bridge(false,true);
		this.bridgeW=new Bridge(false,true);
	}
	public int getCoordX() {
		return this.CoordX;
	}
	public int getCoordY() {
		return this.CoordY;
	}
	public Bridge getBridgeN() {
		return this.bridgeN;
	}
	public Bridge getBridgeW() {
		return this.bridgeW;
	}
	public Bridge getBridgeE() {
		return this.bridgeE;
	}
	public Bridge getBridgeS() {
		return this.bridgeS;
	}
}
