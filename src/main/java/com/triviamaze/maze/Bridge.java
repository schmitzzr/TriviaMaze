package com.triviamaze.maze;

import java.io.Serializable;

public class Bridge {

	/** true if the bridge is still possible to be crossed, false if the bridge has been broken */
	private boolean openStatus;

	/** true if the question has been answered, false otherwise */
	private boolean questionStatus;

	/**
	 * Constructor; initializes the bridge's open status and question status.
	 */
	public Bridge() {
		openStatus = true;
		questionStatus = false;
	}

	/**
	 * Getter for the question status.
	 * @return the question status
	 */
	public boolean getQuestionStatus() {
		return questionStatus;
	}

	/**
	 * Setter for the status of the bridge, which affects both the question and open statuses.
	 * True means the bridge has been solved and free to cross, false if the bridge is broken.
	 * @param theStatus the status of the bridge
	 */
	public void setStatus(final boolean theStatus) {
		questionStatus = theStatus;
		openStatus = theStatus;
	}

	/**
	 * Getter for the open status of the bridge.
	 * @return the open status of the bridge
	 */
	public boolean getOpenStatus() {
		return this.openStatus;//return the open status of the bridge.
	}

	/**
	 * Converts Bridge to a String, mostly for testing purposes.
	 * @return the open and question statuses of the bridge as a String
	 */
	public String toString() {
		return "Open?: " + openStatus + ", Question answered?: " + questionStatus + "\n";
	}

}
