package com.example.quizflagapp;

import java.util.ArrayList;

public class Question {

    public int id;
    public String question;
    public int image;
    public String[] options;
    public int correctAnswer;

    public static final String QUIZ_QUESTION = "What country does this flag belong to?";

    public static final String USER_NAME = "userName";
    public static final String TOTAL_QUESTIONS = "totalQuestions";
    public static final String CORRECT_ANSWERS = "correctAnswers";


    public Question(int id, String question, int image, String[] options, int correctAnswer) {

        this.id = id;
        this.question = question;
        this.image = image;
        this.options = options;
        this.correctAnswer = correctAnswer;

    }


    public static ArrayList<Question> getQuestions() {

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(1, QUIZ_QUESTION, R.drawable.norway,
                new String[]{"Norway", "Sweden", "Iceland", "Denmark"}, 1));

        questions.add(new Question(2, QUIZ_QUESTION, R.drawable.costa_rica,
                new String[]{"Honduras", "Panama", "Costa Rica", "Cuba"}, 3));

        questions.add(new Question(3, QUIZ_QUESTION, R.drawable.niger,
                new String[]{"India", "Hungary", "Nigeria", "Niger"}, 4));

        questions.add(new Question(4, QUIZ_QUESTION, R.drawable.mongolia,
                new String[]{"Mongolia", "Kazakhstan", "Turkmenistan", "North Korea"},
                1));

        questions.add(new Question(5, QUIZ_QUESTION, R.drawable.uruguay,
                new String[]{"Argentina", "Uruguay", "Paraguay", "Jamaica"}, 2));

        questions.add(new Question(6, QUIZ_QUESTION, R.drawable.azerbaijan,
                new String[]{"Azerbaijan", "Armenia", "Turkey", "Algeria"}, 1));

        questions.add(new Question(7, QUIZ_QUESTION, R.drawable.nepal,
                new String[]{"India", "Bangladesh", "Nepal", "Taiwan"}, 3));

        questions.add(new Question(8, QUIZ_QUESTION, R.drawable.romania,
                new String[]{"Bulgaria", "Russia", "Romania", "Moldova"}, 3));

        questions.add(new Question(9, QUIZ_QUESTION, R.drawable.taiwan,
                new String[]{"Taiwan", "China", "Thailand", "Laos"}, 1));

        questions.add(new Question(10, QUIZ_QUESTION, R.drawable.switzerland,
                new String[]{"Sweden", "Switzerland", "Austria", "Germany"}, 2));


        return questions;

    }

}
