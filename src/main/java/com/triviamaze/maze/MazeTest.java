package com.triviamaze.maze;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    final int WIDTH = 4;
    final int HEIGHT = 4;
    final int START_ROW = 0;
    final int START_COLUMN = 0;
    final int END_ROW = 3;
    final int END_COLUMN = 3;

    Maze myMaze = new Maze(WIDTH, HEIGHT, START_ROW, START_COLUMN, END_ROW, END_COLUMN);

    @Test
    public void nothingHappens() {
        assertEquals(0,0);
    }

    @Test
    public void TestCurrentRoomColumn() {
        assertEquals(myMaze.getMyCurrentRoom().getMyColumn(), START_COLUMN);
    }

    @Test
    public void TestCurrentRoomRow() {
        assertEquals(myMaze.getMyCurrentRoom().getMyRow(), START_ROW);
    }

    @Test
    public void TestSettingCurrentRoom() {
        myMaze.setMyCurrentRoom(END_ROW,END_COLUMN);
        assertEquals(myMaze.getMyCurrentRoom().getMyColumn(), END_COLUMN);
    }

    @Test
    public void TestOpeningAndCrossingSouthBridge() {
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.SOUTH,true);
        myMaze.moveLocation(Direction.SOUTH);
        assertEquals(myMaze.getMyCurrentRoom().getMyRow(), START_ROW + 1);
    }

    @Test
    public void TestCrossingBrokenSouthBridge() {
        int originalRow = myMaze.getMyCurrentRoom().getMyRow();
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.SOUTH,false);
        myMaze.moveLocation(Direction.SOUTH);
        assertEquals(myMaze.getMyCurrentRoom().getMyRow(), originalRow);
    }

    @Test
    public void TestWestBridgeStartsBroken() {
        Room topLeftRoom = myMaze.getRoom(0,0);
        assertFalse(topLeftRoom.getMyBridgeW().getOpenStatus());
    }

    @Test
    public void TestNorthBridgeStartsBroken() {
        Room topLeftRoom = myMaze.getRoom(0,0);
        assertFalse(topLeftRoom.getMyBridgeN().getOpenStatus());
    }

    @Test
    public void TestWinningTheGame() {
        myMaze.setMyCurrentRoom(END_ROW, END_COLUMN);
        assertTrue(myMaze.isAtEnd());
    }

    @Test
    public void TestIfBreakingEastBridgeAffectsBothRooms() {
        myMaze.setMyCurrentRoom(START_ROW, START_COLUMN);
        myMaze.breakOrSolveBridge(Direction.EAST, false);
        myMaze.setMyCurrentRoom(START_ROW, START_COLUMN + 1);
        assertFalse(myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus());
    }
}
