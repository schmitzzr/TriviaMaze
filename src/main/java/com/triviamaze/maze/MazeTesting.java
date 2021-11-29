package com.triviamaze.maze;

import java.util.Arrays;

public class MazeTesting {

    public static void main(String[] args) {

        Maze maze = new Maze(4, 4, 0, 0, 3, 3);
        Room[][] mazeArray = maze.getRooms();
        Player player = maze.getPlayer();

//        for (int i = 0; i < 4; i++) {
//            System.out.println(Arrays.toString(mazeArray[i]));
//        }

        System.out.println("\n------------------------------------\n");

        Room room = maze.getRoom(0,0);
        System.out.println(room);

        room.getMyBridgeE().setStatus(true);

        System.out.println("After opening east bridge:\n" + room);

        room.getMyBridgeW().setStatus(false);

        System.out.println("After breaking west bridge:\n" + room);

        System.out.println(player);

        //Move right twice
        player.moveEast();
        player.moveEast();
        System.out.println("Moving player east 2 times -> " + player);

        //Move down thrice
        player.moveSouth();
        player.moveSouth();
        player.moveSouth();
        System.out.println("Moving player south 3 times -> " + player);

        System.out.println("Has the player reached the end?: " + maze.isAtEnd());

        player.moveEast();
        System.out.println("Moving player east 1 time(s) -> " + player);

        System.out.println("Has the player reached the end?: " + maze.isAtEnd());


    }
}
