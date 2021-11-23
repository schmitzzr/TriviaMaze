package maze1;

public class Maze {
	Room[][] rooms;
	Player player;
	int endX,endY;
	public Maze(int width,int height,int startX,int startY,int endX,int endY) {
		this.rooms=new Room[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				this.rooms[i][j]=new Room(j,i);//initial the maze.
			}
		}
		this.endX=endX;
		this.endY=endY;
		this.player=new Player(startX,startY);
	}
	public boolean isAtEnd() {
		//if the player is at the end of the maze,return true,else return false.
		return this.player.getX()==endX && this.player.getY()==endY;
	}
	public Player getPlayer() {
		return this.player;
	}
	public boolean isPositionValid(int x,int y) {
		return x>=0 && y>=0 && x<this.rooms[0].length && y<this.rooms.length;//return true if the position is a valid position in the maze.
	}
	public Room getRoom(int x,int y) {
		if(this.isPositionValid(x, y))
			return this.rooms[y][x];//return the room at index x,y
		return null;//return null if the position is invalid.
	}
	public Room[][] getRooms(){
		return this.rooms;//return all the rooms.
	}
}
