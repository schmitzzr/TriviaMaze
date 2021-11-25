package com.triviamaze;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLCreate {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:multipleChoice.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
                "QUESTION TEXT NOT NULL, " +
                "ID NUMBER NOT NULL," +
                "ANSWER_A TEXT NOT NULL," +
                "ANSWER_B TEXT NOT NULL," +
                "ANSWER_C TEXT NOT NULL," +
                "ANSWER_D TEXT NOT NULL," +
                "ANSWER TEXT NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement() ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );
    }
}
