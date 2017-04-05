package com.example.bane_.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
    Random r;
    TextView question, score;
    Button answer1, answer2, answer3, answer4, nextQuestion;
    boolean answerd = false, lastquestion = false;
    int result = 0, correctAnswer = 0;
    // SharedPreferences finalScore;
    ArrayList<Button> correctAnswersOfButtons;
    ArrayList<String> questions, answers, correctAnswers;
    ArrayList<Integer> correctAnswersNumbers, usedQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);
        //finalScore = getSharedPreferences("storage", MODE_PRIVATE);

        usedQuestions = new ArrayList<>();
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        correctAnswersNumbers = new ArrayList<>();
        correctAnswersOfButtons = new ArrayList<>();

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);


        correctAnswersOfButtons.add(answer1);
        correctAnswersOfButtons.add(answer2);
        correctAnswersOfButtons.add(answer3);
        correctAnswersOfButtons.add(answer4);

        new setScoresOrGetJSON().execute();


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
        if (answerd && !(usedQuestions.size() == questions.size())) {
            answer1.setBackgroundColor(Color.LTGRAY);
            answer2.setBackgroundColor(Color.LTGRAY);
            answer3.setBackgroundColor(Color.LTGRAY);
            answer4.setBackgroundColor(Color.LTGRAY);

            r = new Random();

            int randomQuestion = 0;

            while (true) {
                randomQuestion = r.nextInt(questions.size());
                if (!usedQuestions.contains(randomQuestion)) break;
            }

            question.setText(questions.get(randomQuestion));
            answer1.setText(answers.get(randomQuestion * 4));
            answer2.setText(answers.get(randomQuestion * 4 + 1));
            answer3.setText(answers.get(randomQuestion * 4 + 2));
            answer4.setText(answers.get(randomQuestion * 4 + 3));

            usedQuestions.add(randomQuestion);
            correctAnswers.add(correctAnswersOfButtons.get(correctAnswersNumbers.get(randomQuestion) - 1).getText().toString());

            answerd = false;

        }
        else if(usedQuestions.size() == questions.size()){
            SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
            new setScoresOrGetJSON().execute(storage.getString("name", ""), "" + result);
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        }
    }

    private class setScoresOrGetJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {


            String responseData = "";
            HttpURLConnection con;
            try {


                if (lastquestion) {
                    // Creating http request
                    URL obj = new URL("http://zoran.ogosense.net/api/set-score");
                    con = (HttpURLConnection) obj.openConnection();

                    //add request header
                    con.setRequestMethod("POST");

                    // Send post request
                    con.setDoOutput(true);
                    con.setDoInput(true);

                    String urlParameters = "name=" + params[0] + "&score=" + params[1];

                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();
                } else {
                    // Creating http request
                    URL obj = new URL("http://zoran.ogosense.net/api/get-questions");
                    con = (HttpURLConnection) obj.openConnection();
                }


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
                if (!lastquestion) {
                    JSONObject response = new JSONObject(result);

                    for (int i = 0; i < response.getJSONArray("data").length(); i++) {
                        questions.add(response.getJSONArray("data").getJSONObject(i).getString("question"));
                        answers.add(response.getJSONArray("data").getJSONObject(i).getString("answer1"));
                        answers.add(response.getJSONArray("data").getJSONObject(i).getString("answer2"));
                        answers.add(response.getJSONArray("data").getJSONObject(i).getString("answer3"));
                        answers.add(response.getJSONArray("data").getJSONObject(i).getString("answer4"));
                        correctAnswer = Integer.parseInt(response.getJSONArray("data").getJSONObject(i).getString("correct_answer"));
                        correctAnswersNumbers.add(correctAnswer);
                    }
                    r = new Random();

                    int randomQuestion = r.nextInt(questions.size());

                    question.setText(questions.get(randomQuestion));
                    answer1.setText(answers.get(randomQuestion * 4));
                    answer2.setText(answers.get(randomQuestion * 4 + 1));
                    answer3.setText(answers.get(randomQuestion * 4 + 2));
                    answer4.setText(answers.get(randomQuestion * 4 + 3));

                    usedQuestions.add(randomQuestion);
                    correctAnswers.add(correctAnswersOfButtons.get(correctAnswersNumbers.get(randomQuestion) - 1).getText().toString());
                    lastquestion=true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
