package com.example.bane_.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView question, score;
    Button answer1, answer2, answer3, answer4, nextQuestion;
    int i = 4, j = 1, newQuestion;
    int result = 0;
    boolean answerd = false;
    ArrayList<String> correctAnswers;
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

        usedQuestions = new ArrayList<>();
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);

        new GetListOfCountryNames().execute();

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
     /*   if (answerd && questions.size() != j) {
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
            edit.putBoolean("uslov",true);
            edit.apply();
            Intent goToFirstScreen = new Intent(this, MainActivity.class);
            startActivity(goToFirstScreen);
        }*/
    }

    private class GetListOfCountryNames extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {


            String responseData = "";

            try {

                // Creating http request
                URL obj = new URL("http://zoran.ogosense.net/api/get-questions");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("GET");

                // Send post request
                con.setDoOutput(true);
                con.setDoInput(true);


                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                while ((line = br.readLine()) != null)
                    responseData += line;
                con.disconnect();

                int responseCode = con.getResponseCode();

                System.out.println("Response Code : " + responseCode);
                System.out.println("Response Data: " + responseData);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseData;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject response = new JSONObject(result);
                int numberOfQuestion=0;
                Random r = new Random();
                while (true) {

                    numberOfQuestion = r.nextInt(response.getJSONArray("data").length());

                    if(!usedQuestions.contains(numberOfQuestion))break;


                }
                question.setText(response.getJSONArray("data").getJSONObject(numberOfQuestion).getString("question"));
                answer1.setText(response.getJSONArray("data").getJSONObject(numberOfQuestion).getString("answer1"));
                answer2.setText(response.getJSONArray("data").getJSONObject(numberOfQuestion).getString("answer2"));
                answer3.setText(response.getJSONArray("data").getJSONObject(numberOfQuestion).getString("answer3"));
                answer4.setText(response.getJSONArray("data").getJSONObject(numberOfQuestion).getString("answer4"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
