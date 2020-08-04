package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.braintrainerapp.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    int score = 0;
    int countOfQuestions = 0;

    public void playAgain(View view) {
        score = 0;
        countOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(countOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) +  "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                preventUserClicks();
            }
        }.start();
    }

    public void preventUserClicks() {
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void chooseAnswer(View view) {
        // get the button pressed

        // if location of correct ans is same as pressed tag
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        }else {
            resultTextView.setText("Wrong :(");
        }
        countOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(countOfQuestions));
        newQuestion();
    }

    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(timerTextView);
    }

    public void newQuestion() {
        Random rand = new Random();

        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);

        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswer = rand.nextInt(4);

        // reset the array each time
        answers.clear();

        for(int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(num1 + num2);
            } else{
                // to avoid the case where random num generated happens to be the right answer too
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == num1+num2)
                    wrongAnswer = rand.nextInt(41);
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}