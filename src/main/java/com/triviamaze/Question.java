package com.triviamaze;

public class Question {

    private final String myQuestion;
    private final String myAnswer;
    private final String myAnswerA;
    private final String myAnswerB;
    private final String myAnswerC;
    private final String myAnswerD;

    Question(String theQuestion,
             String theAnswerA,
             String theAnswerB,
             String theAnswerC,
             String theAnswerD,
             String theAnswer) {
        myQuestion = theQuestion;
        myAnswerA = theAnswerA;
        myAnswerB = theAnswerB;
        myAnswerC = theAnswerC;
        myAnswerD = theAnswerD;
        myAnswer = theAnswer;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyAnswerA() {
        return myAnswerA;
    }

    public String getMyAnswerB() {
        return myAnswerB;
    }

    public String getMyAnswerC() {
        return myAnswerC;
    }

    public String getMyAnswerD() {
        return myAnswerD;
    }
}
