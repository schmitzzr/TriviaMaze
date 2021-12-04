package com.triviamaze.maze;

public class Bridge {

	private boolean openStatus = true; // true if the bridge has not been broken
	private boolean questionStatus; // true if the question has been answered


	public void setStatus(boolean theStatus) {
		questionStatus = theStatus;
		openStatus = theStatus;
	}

	public boolean getOpenStatus() {
		return this.openStatus;//return the open status of the bridge.
	}

	public void setOpenStatus(boolean status) {
		this.openStatus=status;//set the status of the bridge.
	}

}
