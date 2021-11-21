package maze1;

public class Bridge {
	private boolean vertical,horizontal,openStatus;
	public Bridge(boolean vertical,boolean horizontal) {
		this.horizontal=horizontal;
		this.vertical=vertical;
	}
	public boolean getOpenStatus() {
		return this.openStatus;
	}
	public boolean isVertical() {
		return this.isVertical();
	}
	public void setOpenStatus(boolean status) {
		this.openStatus=status;
	}
	public boolean isHorizontal() {
		return this.horizontal;
	}
}
