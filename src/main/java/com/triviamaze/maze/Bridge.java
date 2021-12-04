package com.triviamaze.maze;

public class Bridge {

	private final boolean myVertical;
	private final boolean myHorizontal;
	private boolean openStatus = true; // true if the bridge has not been broken
	private boolean questionStatus; // true if the question has been answered

	public Bridge(boolean vertical,boolean horizontal) {
		myHorizontal = horizontal;
		myVertical = vertical;
	}

	public boolean getQuestionStatus() {
		return questionStatus;
	}

	public void setStatus(boolean theStatus) {
		questionStatus = theStatus;
		openStatus = theStatus;
	}

	public boolean getOpenStatus() {
		return this.openStatus;//return the open status of the bridge.
	}

	public String toString() {
		String orientation;
		if (myVertical) {
			orientation = "Vertical";
		} else {
			orientation = "Horizontal";
		}

		return "Orientation: " + orientation + ", Open?: " + openStatus + ", Question answered?: " + questionStatus + "\n";
	}

}
