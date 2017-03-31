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

    if(finalScore.getBoolean("uslov",false)) {
        sort(storage.getString("name", ""), finalScore.getString("score", ""));

        score1.setText(storage.getString("end", ""));
        }
        finalScore.getBoolean("uslov",false);
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
//        edit = storage.edit();
//        allScores = returnArray(allScores, score1);
//        if (allScores.size() != 0) {
//            for (int i = 0; i < allScores.size() && i < 10; i++) {
//                if (Integer.parseInt(record) > Integer.parseInt(allScores.get(i).substring(allScores.get(i).indexOf(':'), allScores.get(i).length()))) {
//                    String newElemenofArray = "" + (i + 1) + "." + names + ":" + record;
//                    names = allScores.get(i).substring(2, allScores.get(i).indexOf(':'));
//                    record = allScores.get(i).substring(allScores.get(i).indexOf(':'), allScores.get(i).length());
//                    allScores.set(i, newElemenofArray);
//                }
//            }
//        } else if (!record.equals("")) {
//            allScores.add("1." + names + ":" + record);
//        }
//        if (allScores.size() != 0) {
//            for (int i = 0; i < allScores.size() ; i++)
//                score1.setText(score1.getText().toString() + '\n' + allScores.get(i));
//        }
//        edit.putString("end", score1.getText().toString());
//        edit.apply();
        allScores = returnArray(allScores, score1);
        if (allScores.size() == 0) allScores.add("123." + names + ":" + record);
        else {
            for (int i = 0; i < allScores.size() && i < 10; i++) {
                if (Integer.parseInt(record) > Integer.parseInt(allScores.get(i).substring(allScores.get(i).indexOf(':'), allScores.get(i).length()))) {
                    String newElemenofArray = "" + (i + 1) + "." + names + ":" + record;
                    names = allScores.get(i).substring(2, allScores.get(i).indexOf(':'));
                    record = allScores.get(i).substring(allScores.get(i).indexOf(':'), allScores.get(i).length());
                    allScores.set(i, newElemenofArray);
                }
            }
        }
        ispisi();
        edit = storage.edit();
        edit.putString("end", score1.getText().toString());
        edit.apply();
    }

    public ArrayList<String> returnArray(ArrayList<String> list, TextView score) {
        if (!score.getText().toString().equals("")) {
            String[] lines = score.getText().toString().split(System.getProperty("line.separator"));
            for (int i = 0; i < lines.length; i++) list.add(lines[i]);
        }
        return list;
    }
    public void ispisi()
    {
        score1.setText(allScores.get(0));
        if(allScores.size()>1) for(int i=1;i<allScores.size();i++) score1.setText(score1.getText().toString()+"\n"+allScores.get(i));
    }

}
