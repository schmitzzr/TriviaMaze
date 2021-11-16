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
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS multipleChoice ( " +
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

        boolean cont = true;
        Scanner input = new Scanner(System.in);
        int qCount = 0;

        while (cont) {

            System.out.println("Selecting question...");

            query = "SELECT * FROM multipleChoice ORDER BY RANDOM() LIMIT 1";
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery(query);


                String question = rs.getString("QUESTION");
                String answer_a = rs.getString("ANSWER_A");
                String answer_b = rs.getString("ANSWER_B");
                String answer_c = rs.getString("ANSWER_C");
                String answer_d = rs.getString("ANSWER_D");
                String answer = rs.getString("ANSWER");

                System.out.println("Question = " + question +
                        "\nA. " + answer_a +
                        "\nB. " + answer_b +
                        "\nC. " + answer_c +
                        "\nD. " + answer_d);

                System.out.println("Type your letter of the answer below (A, B, C, or D): ");
                if (answer.equals(input.nextLine())) {
                    System.out.println("You are correct!");
                    qCount++;
                } else {
                    System.out.println("You are incorrect!  The answer was " + answer + ".\nGame Over!");
                    System.out.printf("Your score for this quiz was %d.\n", qCount);
                    cont = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
