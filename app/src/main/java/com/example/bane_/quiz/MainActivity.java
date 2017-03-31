package com.example.bane_.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name;
    SharedPreferences storage;
    TextView bestScore;
    TextView score1;
    ArrayList<String> allScores = new ArrayList<>();
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText);
        storage = getSharedPreferences("storage", MODE_PRIVATE);
        bestScore = (TextView) findViewById(R.id.score);
        SharedPreferences finalScore = getSharedPreferences("storage", MODE_PRIVATE);

        score1 = (TextView) findViewById(R.id.score1);


        sort(storage.getString("name", ""), finalScore.getString("score", ""));
        score1.setText(storage.getString("end", ""));

    }

    public void onClic(View view) {
        if (!name.getText().toString().equals("")) {
            edit = storage.edit();

            edit.putString("name", name.getText().toString());

            edit.apply();
            Intent questionsActivity = new Intent(this, Main2Activity.class);
            startActivity(questionsActivity);
        } else Toast.makeText(this, "You need to enter your name!", Toast.LENGTH_SHORT).show();
    }

    public void sort(String names, String record) {
        if(!record.equals("") && score1.getText().toString().equals(""))
                allScores = returnArray(allScores, score1);
                edit = storage.edit();
                for (int i = 0; i < allScores.size() + 1 && i < 10; i++) {
                    String[]result = allScores.get(i).split(":");
                    if (result.length != 2) {
                        allScores.add("" + (i + 1) + "." + names + ":" + record);
                        break;
                    }
                    if (Integer.parseInt(result[1])>Integer.parseInt(record)) continue;
                    else {
                        String newName = result[0].substring(2, result[0].length());
                        String newRecord = result[1];
                        allScores.add("" + (i + 1) + "." + names + ":" + record);
                        names = newName;
                        record = newRecord;
                    }
                }
                for (int i = 0; i < allScores.size(); i++) {
                    score1.setText(score1.getText().toString() + '\n' + allScores.get(i));
                }
                edit.putString("end", score1.getText().toString());
                edit.apply();
    }

    public ArrayList<String> returnArray(ArrayList<String> list, TextView score) {
        if(!score.getText().toString().equals("")) {
            String[] lines = score.getText().toString().split(System.getProperty("line.separator"));
            for (int i = 0; i < lines.length; i++) list.add(lines[i]);
            System.out.println(list);
        }
        return list;
    }

}
