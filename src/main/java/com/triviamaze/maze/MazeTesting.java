package com.triviamaze.maze;

import java.util.Arrays;

public class MazeTesting {

    public static void main(String[] args) {

        Maze maze = new Maze(4, 4, 0, 0, 3, 3);

        System.out.println("Current room: \n" + maze.getMyCurrentRoom());

        //Move right twice
        maze.moveLocation(Direction.EAST);

        System.out.println("Attempting to move player east on unsolved bridge: \n" + maze.getMyCurrentRoom());

        //Breaking south bridge
        maze.breakOrSolveBridge(Direction.SOUTH, false);

        System.out.println("Breaking bridge south -> " + maze.getMyCurrentRoom());

        // attempting to cross south bridge
        maze.moveLocation(Direction.SOUTH);
        System.out.println("Attempting to move south -> " + maze.getMyCurrentRoom());


        //Fixing and moving across east bridge
        maze.breakOrSolveBridge(Direction.EAST, true);
        System.out.println("Opening bridge east -> " + maze.getMyCurrentRoom());
        maze.moveLocation(Direction.EAST);
        System.out.println("Crossing bridge east -> " + maze.getMyCurrentRoom());

        //Fixing and moving across north bridge
        maze.breakOrSolveBridge(Direction.SOUTH, true);
        maze.moveLocation(Direction.SOUTH);
        System.out.println("Crossing bridge south -> " + maze.getMyCurrentRoom());

        //Fixing and moving across west bridge
        maze.breakOrSolveBridge(Direction.WEST, true);
        maze.moveLocation(Direction.WEST);
        System.out.println("Crossing bridge west -> " + maze.getMyCurrentRoom());

        //Breaking all bridges in room
        System.out.println("Possible to continue?: " + maze.checkAbilityToContinue() + "\n");

        maze.breakOrSolveBridge(Direction.EAST, false);
        maze.breakOrSolveBridge(Direction.SOUTH, false);
        //maze.breakOrSolveBridge(Direction.WEST, false);

        System.out.println("After breaking all bridges:\n" + maze.getMyCurrentRoom());

        System.out.println("Possible to continue?: " + maze.checkAbilityToContinue());

    }
}
