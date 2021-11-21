package maze1;

public class Player {
	private int CoordX,CoordY;
	public Player(int x,int y) {
		this.CoordX=x;
		this.CoordY=y;
	}
	public int getX() {
		return this.CoordX;
	}
	public int getY() {
		return this.CoordY;
	}
	public void setX(int x) {
		this.CoordX=x;
	}
	public void setY(int y) {
		this.CoordY=y;
	}
}
