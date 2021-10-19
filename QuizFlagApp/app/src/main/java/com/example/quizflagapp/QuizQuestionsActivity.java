package com.example.quizflagapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.service.controls.templates.ControlTemplate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizQuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Question> questions;
    private ProgressBar progressBar;
    private TextView textViewProgress, textViewQuestion, textViewOptionOne, textViewOptionTwo, textViewOptionThree, textViewOptionFour;
    private ImageView imageView;

    private TextView[] textViews;

    private Button buttonSubmit;

    private int currentPosition = 1;
    private int selectedOptionPosition = 0;
    private int correctAnswers = 0;

    private String userName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        textViewProgress = (TextView) findViewById(R.id.tv_progress);
        textViewQuestion = (TextView) findViewById(R.id.tv_question);

        imageView = (ImageView) findViewById(R.id.iv_image_country);

        textViewOptionOne = (TextView) findViewById(R.id.tv_option_one);
        textViewOptionTwo = (TextView) findViewById(R.id.tv_option_two);
        textViewOptionThree = (TextView) findViewById(R.id.tv_option_three);
        textViewOptionFour = (TextView) findViewById(R.id.tv_option_four);

        buttonSubmit = (Button) findViewById(R.id.btn_submit);


        userName = getIntent().getStringExtra(Question.USER_NAME);


        questions = Question.getQuestions();

        setQuestions();


        textViews = new TextView[]{textViewOptionOne, textViewOptionTwo, textViewOptionThree, textViewOptionFour};

        for (int i = 0; i < textViews.length; i++) {

            textViews[i].setOnClickListener(this::onClick);

        }


        buttonSubmit.setOnClickListener(view -> {

            if (selectedOptionPosition == 0) {

                currentPosition++;

                if (currentPosition <= questions.size()) {

                    setQuestions();

                } else {

                    Intent intent = new Intent(QuizQuestionsActivity.this, ResultActivity.class);

                    intent.putExtra(Question.USER_NAME, userName);
                    intent.putExtra(Question.TOTAL_QUESTIONS, questions.size());
                    intent.putExtra(Question.CORRECT_ANSWERS, correctAnswers);

                    startActivity(intent);

                    finish();

                }

            } else {

                Question q = questions.get(currentPosition - 1);

                if (q.correctAnswer != selectedOptionPosition) {

                    setAnswerView(selectedOptionPosition, R.drawable.wrong_drawable_resource_option_border);

                }else{
                    correctAnswers++;
                }

                if (buttonSubmit.isPressed()){
                    textViewOptionOne.setClickable(false);
                    textViewOptionTwo.setClickable(false);
                    textViewOptionThree.setClickable(false);
                    textViewOptionFour.setClickable(false);
                }


                setAnswerView(q.correctAnswer, R.drawable.correct_drawable_resource_option_border);

                if (currentPosition == questions.size()) {

                    buttonSubmit.setText("FINISH");

                } else {

                    buttonSubmit.setText("GO TO THE NEXT QUESTION");

                }

                selectedOptionPosition = 0;

            }

        });

    }

    private void setQuestions() {

        Question question = questions.get(currentPosition - 1);

        setDefaultOptionsView();

        if (currentPosition == questions.size()) {

            buttonSubmit.setText("FINISH");

        } else {

            buttonSubmit.setText("GO TO THE NEXT QUESTION");


        }

        progressBar.setProgress(currentPosition);

        textViewProgress.setText(currentPosition + "/" + progressBar.getMax());

        textViewQuestion.setText(question.question);

        imageView.setImageResource(question.image);

        textViewOptionOne.setText(question.options[0]);
        textViewOptionTwo.setText(question.options[1]);
        textViewOptionThree.setText(question.options[2]);
        textViewOptionFour.setText(question.options[3]);

        textViewOptionOne.setClickable(true);
        textViewOptionTwo.setClickable(true);
        textViewOptionThree.setClickable(true);
        textViewOptionFour.setClickable(true);

    }

    private void setDefaultOptionsView() {

        ArrayList<TextView> options = new ArrayList<>();

        options.add(0, textViewOptionOne);

        options.add(1, textViewOptionTwo);

        options.add(2, textViewOptionThree);

        options.add(3, textViewOptionFour);

        for (TextView option : options) {

            option.setTextColor(Color.GRAY);
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(this, R.drawable.drawable_resource_option_border));

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_option_one:
                setSelectedOptionView(textViews[0], 1);
                break;
            case R.id.tv_option_two:
                setSelectedOptionView(textViews[1], 2);
                break;
            case R.id.tv_option_three:
                setSelectedOptionView(textViews[2], 3);
                break;
            case R.id.tv_option_four:
                setSelectedOptionView(textViews[3], 4);
                break;

        }

    }

    public void setSelectedOptionView(TextView textView, int selectedOptionNumber) {

        setDefaultOptionsView();

        selectedOptionPosition = selectedOptionNumber;

        textView.setTextColor(Color.GRAY);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_drawable_resource_option_border));

    }


    private void setAnswerView(int answer, int drawableView) {

        switch (answer) {

            case 1:
                textViewOptionOne.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 2:
                textViewOptionTwo.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 3:
                textViewOptionThree.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 4:
                textViewOptionFour.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;

        }

    }
}