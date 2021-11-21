package maze1;

public class Maze {
	Room[][] rooms;
	Player player;
	int endX,endY;
	public Maze(int width,int height,int startX,int startY,int endX,int endY) {
		this.rooms=new Room[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				this.rooms[i][j]=new Room(j,i);
			}
		}
		this.endX=endX;
		this.endY=endY;
		this.player=new Player(startX,startY);
	}
	public boolean isAtEnd() {
		return this.player.getX()==endX && this.player.getY()==endY;
	}
	public Player getPlayer() {
		return this.player;
	}
	public boolean isPositionValid(int x,int y) {
		return x>=0 && y>=0 && x<this.rooms[0].length && y<this.rooms.length;
	}
	public Room getRoom(int x,int y) {
		return this.rooms[y][x];
	}
	public Room[][] getRooms(){
		return this.rooms;
	}
}
