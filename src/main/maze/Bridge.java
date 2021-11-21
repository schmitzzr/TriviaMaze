package maze1;

public class Bridge {
	private boolean vertical,horizontal,openStatus;
	public Bridge(boolean vertical,boolean horizontal) {
		this.horizontal=horizontal;
		this.vertical=vertical;
	}
	public boolean getOpenStatus() {
		return this.openStatus;//return the open status of the bridge.
	}
	public boolean isVertical() {
		return this.isVertical();//return true if the bridge is vertical.
	}
	public void setOpenStatus(boolean status) {
		this.openStatus=status;//set the status of the bridge.
	}
	public boolean isHorizontal() {
		return this.horizontal;//return true if the bridge is horizontal.
	}
}
