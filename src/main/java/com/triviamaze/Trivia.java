package com.triviamaze;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class initializes an array list that contains questions originally stored in a SQLite Database.
 */
public class Trivia {

    /** List of questions */
    private final ArrayList<Question> myQuestionList = new ArrayList<>();

    /** The question to be asked */
    private String myQuestion;

    /** The answer corresponding to multiple choice A, B, C, and D respectively */
    private String myAnswerA, myAnswerB, myAnswerC, myAnswerD;

    /** The actual answer to the question */
    private String myAnswer;

    /**
     * Pulls questions from the SQLite database and adds them to the question ArrayList.
     * The questions are then shuffled randomly.
     *
     * @param theUrl the URL of the database
     * @param theTable the specific table within the database that holds the questions
     */
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

    /**
     * Chooses the question to ask and initializes the fields.
     */
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

    /**
     * Getter for the question.
     * @return the question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Getter for the multiple choice answer A.
     * @return answer A
     */
    public String getAnswerA() {
        return myAnswerA;
    }

    /**
     * Getter for the multiple choice answer B.
     * @return answer B
     */
    public String getAnswerB() {
        return myAnswerB;
    }

    /**
     * Getter for the multiple choice answer C.
     * @return answer D
     */
    public String getAnswerC() {
        return myAnswerC;
    }

    /**
     * Getter for the multiple choice answer D.
     * @return answer D
     */
    public String getAnswerD() {
        return myAnswerD;
    }

    /**
     * Getter for the correct answer to the question.
     * @return the correct answer
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Getter for whether the guess was equal to the answer.
     * @param theGuess the guess
     * @return true if the guess was correct, false otherwise
     */
    public boolean getResult(String theGuess) {
        return theGuess.equals(myAnswer);
    }

}
