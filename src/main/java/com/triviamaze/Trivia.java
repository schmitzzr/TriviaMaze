package com.triviamaze;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Trivia {

    private SQLiteDataSource ds;
    private String myQuestion;
    private String myAnswerA;
    private String myAnswerB;
    private String myAnswerC;
    private String myAnswerD;
    private String myAnswer;

    Trivia() {
        ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:multipleChoice.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void chooseQuestion() {
        System.out.println("Selecting question...");

        String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);


            myQuestion = rs.getString("QUESTION");
            myAnswerA = rs.getString("ANSWER_A");
            myAnswerB = rs.getString("ANSWER_B");
            myAnswerC = rs.getString("ANSWER_C");
            myAnswerD = rs.getString("ANSWER_D");
            myAnswer = rs.getString("ANSWER");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getQuestion() {
        return myQuestion;
    }

    public String getAnswerA() {
        return myAnswerA;
    }

    public String getAnswerB() {
        return myAnswerB;
    }

    public String getAnswerC() {
        return myAnswerC;
    }

    public String getAnswerD() {
        return myAnswerD;
    }

    public String getAnswer() {
        return myAnswer;
    }

}
