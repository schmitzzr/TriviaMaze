package com.triviamaze.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    int WIDTH = 4;
    int HEIGHT = 4;
    int START_ROW = 0;
    int START_COLUMN = 0;
    int END_ROW = 3;
    int END_COLUMN = 3;

    Maze myMaze = new Maze(WIDTH, HEIGHT, START_ROW, START_COLUMN, END_ROW, END_COLUMN);

    @Test
    void findCurrentRoomColumn() {
        Room current = myMaze.getMyCurrentRoom();
        assertEquals(0, current.getMyColumn());
    }

}