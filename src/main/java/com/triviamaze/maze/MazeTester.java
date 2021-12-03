package com.triviamaze.maze;

import org.junit.jupiter.api.Test;

public class MazeTester{
    @Test
    public void testMaze(){
        Maze maze = new Maze(10,10,0,0,9,9);
        maze.getRoom(0,0).bridgeE.setOpenStatus(false);
        assert(maze.getRoom(0,0).bridgeE.getOpenStatus() == false);
        assert(maze.isAtEnd() == false);
        maze.getPlayer().setX(9);
        maze.getPlayer().setY(9);
        assert(maze.isAtEnd());

    }

}

