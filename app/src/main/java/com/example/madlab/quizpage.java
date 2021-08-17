package com.example.madlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class quizpage extends AppCompatActivity implements View.OnClickListener {
    public Button option1, option2, option3, next;
    public TextView question, scoreBoard, progress;
    private ProgressBar spinner;
    private ArrayList<quizModel> quizModelArrayList;
    int currentPos = 0;
    int CurrentScore = 0;
    String UserName, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        next = (Button) findViewById(R.id.next);
        question = (TextView) findViewById(R.id.question);
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);
        progress = (TextView) findViewById(R.id.progress);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        next.setOnClickListener(this);
        UserName = getIntent().getStringExtra("userName");
        Log.d("USERNAME======", UserName);
        category = getIntent().getStringExtra("category");



        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option1.getText().toString().trim())) {
                    CurrentScore++;
                    option1.setBackgroundColor(getResources().getColor(R.color.correct));
                } else {
                    option1.setBackgroundColor(getResources().getColor(R.color.incorrect));
                }

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option2.getText().toString().trim())) {
                    CurrentScore++;
                    option2.setBackgroundColor(getResources().getColor(R.color.correct));
                } else {
                    option2.setBackgroundColor(getResources().getColor(R.color.incorrect));
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option3.getText().toString().trim())) {
                    CurrentScore++;
                    option3.setBackgroundColor(getResources().getColor(R.color.correct));
                } else {
                    option3.setBackgroundColor(getResources().getColor(R.color.incorrect));
                }

            }
        });
        quizModelArrayList = new ArrayList<>();
        getQuizQuestion(quizModelArrayList);
        setDataToViews(currentPos);

    }

    private void setDataToViews(int currentPos) {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        question.setText(quizModelArrayList.get(currentPos).getQuestion());
        option1.setText(quizModelArrayList.get(currentPos).getOption1());
        option2.setText(quizModelArrayList.get(currentPos).getOption2());
        option3.setText(quizModelArrayList.get(currentPos).getOption3());
        scoreBoard.setText(String.format("Score: %d", CurrentScore));
        progress.setText(String.format("%d/%d", currentPos + 1, quizModelArrayList.size()));
    }

    private void getQuizQuestion(final ArrayList<quizModel> quizModelArrayList) {
        int number = 0;
        if(category.compareToIgnoreCase("Sports")==0){
                number = 21;
        }else if(category.compareToIgnoreCase("Entertainment")==0){
            number = 11;
        }else{
                number = 9;
        }
        String url = String.format("https://opentdb.com/api.php?amount=5&category=%d",number);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Response

                        try {
                            Log.d("Success Text", response.getString("results"));
                            JSONArray questions = response.getJSONArray("results");
                            if (questions != null) {
                                for (int i = 0; i < questions.length(); i++) {
                                    JSONObject data = new JSONObject(questions.getString(i));
                                    String q = data.getString("question").toString();
                                    String replaceString=q.replaceAll("&quot;","\"");
                                    replaceString = q.replaceAll("&#039;","\'");
                                    JSONArray options = new JSONArray(data.getString("incorrect_answers"));
                                    Random rand = new Random();
                                    int randInt = rand.nextInt(3);
                                    options.put(randInt, data.getString("correct_answer"));
                                    quizModelArrayList.add(new quizModel(replaceString, options.get(0).toString(), options.get(1).toString(), options.get(2).toString(), data.getString("correct_answer")));
                                    Log.d("Success", new JSONObject(questions.getString(i)).getString("question"));
                                }
                            }
                            spinner.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Context context = getApplicationContext();
                            CharSequence text = "Something went wrong";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            spinner.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        spinner.setVisibility(View.GONE);
                        Context context = getApplicationContext();
                        CharSequence text = "Something went wrong";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                });
        queue.add(jsonObjectRequest);
        quizModelArrayList.add(new quizModel("what's your name?", " ben", " cat", " bat", " ben"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                currentPos++;
                if (currentPos >= quizModelArrayList.size()) {
                    String score = String.format("%d/%d",CurrentScore,currentPos-1);
                    Intent intent1 = new Intent(quizpage.this, highscore.class);
                    intent1.putExtra("keyname", UserName);
                    intent1.putExtra("score", score);
                    startActivity(intent1);
                    break;
                }
                setDataToViews(currentPos);
                break;

        }
    }
}