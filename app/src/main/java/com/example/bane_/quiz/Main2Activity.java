package com.example.bane_.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView question, score;
    Button answer1, answer2, answer3, answer4, nextQuestion;
    int i = 4, j = 1, newQuestion;
    int result = 0;
    boolean answerd = false;
    ArrayList<String> questions, answer, correctAnswers;
    SharedPreferences finalScore;
    Random r;
    ArrayList<Integer> usedQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);
        finalScore = getSharedPreferences("storage", MODE_PRIVATE);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);

        questions = new ArrayList<>();
        answer = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        usedQuestions = new ArrayList<>();

        questions.add("Who released the 2009 album \"All I Ever Wanted? ");
        answer.add("Kelly Clarkson");
        answer.add(" Madonna");
        answer.add("Lady Gaga");
        answer.add("Beyonce");
        correctAnswers.add("Kelly Clarkson");
        questions.add("In what year did Disney release the film \"Pocahontas\"?");
        answer.add("1995");
        answer.add("1991");
        answer.add("1993");
        answer.add("1997");
        correctAnswers.add("1995");
        questions.add("What colour is the inside of the Kiwi Fruit?");
        answer.add("Brown");
        answer.add("Green");
        answer.add("Red");
        answer.add("Yellow");
        correctAnswers.add("Green");
        questions.add("What is the name of Jafar's parrot in \"Aladdin\"?");
        answer.add("Iago");
        answer.add("Poppy");
        answer.add("Henry");
        answer.add("Vizier");
        correctAnswers.add("Iago");
        questions.add("The coccyx lies in which part of the human body?");
        answer.add("Legs");
        answer.add("Back");
        answer.add("Chest");
        answer.add("Arm");
        correctAnswers.add("Back");

        r = new Random();
        newQuestion = r.nextInt(questions.size());
        usedQuestions.add(newQuestion);

        question.setText(questions.get(newQuestion));
        answer1.setText(answer.get(newQuestion * 4));
        answer2.setText(answer.get(newQuestion * 4 + 1));
        answer3.setText(answer.get(newQuestion * 4 + 2));
        answer4.setText(answer.get(newQuestion * 4 + 3));
    }

    public void firstAnswer(View view) {
        Button button = (Button) view;
        if (!answerd) {
            answerd = true;
            if (correctAnswers.contains(button.getText().toString())) {
                result++;
                score.setText(score.getText().toString().substring(0, score.getText().toString().indexOf(':') + 1) + result);
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
                if (correctAnswers.contains(answer2.getText().toString()))
                    answer2.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer3.getText().toString()))
                    answer3.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer4.getText().toString()))
                    answer4.setBackgroundColor(Color.GREEN);
            }
        }
    }

    public void secondAnswer(View view) {
        Button button = (Button) view;
        if (!answerd) {
            answerd = true;
            if (correctAnswers.contains(button.getText().toString())) {
                result++;
                score.setText(score.getText().toString().substring(0, score.getText().toString().indexOf(':') + 1) + result);
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
                if (correctAnswers.contains(answer1.getText().toString()))
                    answer1.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer3.getText().toString()))
                    answer3.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer4.getText().toString()))
                    answer4.setBackgroundColor(Color.GREEN);
            }
        }
    }

    public void thirdAnswer(View view) {
        Button button = (Button) view;
        if (!answerd) {
            answerd = true;
            if (correctAnswers.contains(button.getText().toString())) {
                result++;
                score.setText(score.getText().toString().substring(0, score.getText().toString().indexOf(':') + 1) + result);
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
                if (correctAnswers.contains(answer2.getText().toString()))
                    answer2.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer1.getText().toString()))
                    answer1.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer4.getText().toString()))
                    answer4.setBackgroundColor(Color.GREEN);
            }
        }
    }

    public void fourthAnswer(View view) {
        Button button = (Button) view;
        if (!answerd) {
            answerd = true;
            if (correctAnswers.contains(button.getText().toString())) {
                result++;
                score.setText(score.getText().toString().substring(0, score.getText().toString().indexOf(':') + 1) + result);
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
                if (correctAnswers.contains(answer2.getText().toString()))
                    answer2.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer3.getText().toString()))
                    answer3.setBackgroundColor(Color.GREEN);
                else if (correctAnswers.contains(answer1.getText().toString()))
                    answer1.setBackgroundColor(Color.GREEN);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void nextQuestion(View view) {
        if (answerd && questions.size() != j) {
            answer1.setBackgroundColor(Color.LTGRAY);
            answer2.setBackgroundColor(Color.LTGRAY);
            answer3.setBackgroundColor(Color.LTGRAY);
            answer4.setBackgroundColor(Color.LTGRAY);
            j++;
            while (true) {
                newQuestion = r.nextInt(questions.size());
                if (!usedQuestions.contains(newQuestion)) {
                    question.setText(questions.get(newQuestion));
                    answer1.setText(answer.get(newQuestion * 4));
                    answer2.setText(answer.get(newQuestion * 4 + 1));
                    answer3.setText(answer.get(newQuestion * 4 + 2));
                    answer4.setText(answer.get(newQuestion * 4 + 3));
                    answerd = false;
                    usedQuestions.add(newQuestion);
                    break;
                }

            }
        } else if (j == questions.size()) {

            SharedPreferences.Editor edit = finalScore.edit();

            edit.putString("score", score.getText().toString().substring(score.getText().toString().indexOf(':') + 1, score.getText().toString().length()));
//            edit.putString("numberOfSocres",""+(Integer.parseInt(finalScore.getString("numberOfSocres","")+1)));
            edit.apply();
            Intent goToFirstScreen = new Intent(this, MainActivity.class);
            startActivity(goToFirstScreen);
        }
    }
}
