package com.triviamaze.maze;

import java.io.Serializable;

public class Bridge implements Serializable {

	/** true if the bridge is still possible to be crossed, false if the bridge has been broken */
	private boolean myOpenStatus;

	/** true if the question has been answered, false otherwise */
	private boolean mySolvedStatus;

	/** true if question is in progress, false otherwise */
	private boolean myQuestionStatus;

	/**
	 * Constructor; initializes the bridge's open status and question status.
	 */
	public Bridge() {
		myOpenStatus = true;
		mySolvedStatus = false;
	}

	/**
	 * Getter for the question status.
	 * @return the question status
	 */
	public boolean getSolvedStatus() {
		return mySolvedStatus;
	}

	/**
	 * Getter for the open status of the bridge.
	 * @return the open status of the bridge
	 */
	public boolean getOpenStatus() {
		return myOpenStatus;
	}

	/**
	 * Setter for the status of the bridge, which affects both the solved and open statuses.
	 * True means the bridge has been solved and free to cross, false if the bridge is broken.
	 * @param theStatus the status of the bridge
	 */
	public void setStatus(final boolean theStatus) {
		mySolvedStatus = theStatus;
		myOpenStatus = theStatus;
	}

	public boolean getQuestionStatus() {
		return myQuestionStatus;
	}

	public void setQuestionStatus(boolean theStatus) {
		myQuestionStatus = theStatus;
	}

	/**
	 * Converts Bridge to a String, mostly for testing purposes.
	 * @return the open and question statuses of the bridge as a String
	 */
	public String toString() {
		return "Open?: " + myOpenStatus + ", Question answered?: " + mySolvedStatus + "\n";
	}

}
