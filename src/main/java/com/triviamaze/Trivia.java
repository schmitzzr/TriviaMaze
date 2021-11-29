package com.triviamaze;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Trivia {

    private final ArrayList<Question> myQuestionList = new ArrayList<>();
    private String myQuestion;
    private String myAnswerA;
    private String myAnswerB;
    private String myAnswerC;
    private String myAnswerD;
    private String myAnswer;

    Trivia(String theUrl, String theTable) {
        SQLiteDataSource ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl(theUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        String query = "SELECT * FROM " + theTable;
        Question fullQuestion;

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                myQuestion = rs.getString("QUESTION");
                myAnswerA = rs.getString("ANSWER_A");
                myAnswerB = rs.getString("ANSWER_B");
                myAnswerC = rs.getString("ANSWER_C");
                myAnswerD = rs.getString("ANSWER_D");
                myAnswer = rs.getString("ANSWER");

                fullQuestion = new Question(myQuestion, myAnswerA, myAnswerB, myAnswerC, myAnswerD, myAnswer);
                myQuestionList.add(fullQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Collections.shuffle(myQuestionList);
    }

    public void chooseQuestion() {
        System.out.println("Selecting question...");
        Question fullQuestion = myQuestionList.get(0);
        myQuestion = fullQuestion.getMyQuestion();
        myAnswerA = fullQuestion.getMyAnswerA();
        myAnswerB = fullQuestion.getMyAnswerB();
        myAnswerC = fullQuestion.getMyAnswerC();
        myAnswerD = fullQuestion.getMyAnswerD();

        myAnswer = fullQuestion.getMyAnswer();

        myQuestionList.remove(0);
        myQuestionList.add(fullQuestion);
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

    public boolean getResult(String theGuess) {
        return theGuess.equals(myAnswer);
    }


}
