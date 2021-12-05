package com.triviamaze.maze;

public class Bridge {

	private boolean openStatus; // true if the bridge has not been broken
	private boolean questionStatus; // true if the question has been answered

	public Bridge() {
		openStatus = true;
		questionStatus = false;
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
		return "Open?: " + openStatus + ", Question answered?: " + questionStatus + "\n";
	}

}
