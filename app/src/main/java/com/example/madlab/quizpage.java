package com.example.madlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class quizpage extends AppCompatActivity implements View.OnClickListener {
    public Button option1,option2,option3,next;
    public TextView question;
    private ArrayList<quizModel> quizModelArrayList;
    public String uname;
    int currentPos=0;
    int CurrentScore=0;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);
        option1=(Button) findViewById(R.id.option1);
        option2=(Button) findViewById(R.id.option2);
        option3=(Button) findViewById(R.id.option3);
        next=(Button) findViewById(R.id.next);
        question=(TextView) findViewById(R.id.question);
        next.setOnClickListener(this);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option1.getText().toString().trim())){
                    CurrentScore++;
                }

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option2.getText().toString().trim())){
                    CurrentScore++;
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().toLowerCase().equals(option3.getText().toString().trim())){
                    CurrentScore++;
                }

            }
        });
        quizModelArrayList=new ArrayList<>();
        getQuizQuestion(quizModelArrayList);
        setDataToViews(currentPos);

    }

        private void setDataToViews(int currentPos){
        question.setText(quizModelArrayList.get(currentPos).getQuestion());
        option1.setText(quizModelArrayList.get(currentPos).getOption1());
        option2.setText(quizModelArrayList.get(currentPos).getOption2());
        option3.setText(quizModelArrayList.get(currentPos).getOption3());
    }

    private void getQuizQuestion(ArrayList<quizModel> quizModelArrayList) {
        quizModelArrayList.add(new quizModel("what's your name?"," ben"," cat"," bat"," ben"));
        quizModelArrayList.add(new quizModel("what's your age?"," 1"," 2"," 3"," 2"));
        quizModelArrayList.add(new quizModel("what's your hobby?"," sleeping"," dancing"," tv","sleeping"));

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.next:
                currentPos++;
                if(currentPos>=quizModelArrayList.size()){
                    Intent intent1 = new Intent(quizpage.this,highscore.class);
                    intent1.putExtra("right_answer",CurrentScore);
                    String username=getIntent().getStringExtra("keyname");
                    startActivity(intent1);
                    break;
                }
                setDataToViews(currentPos);
                break;

        }
    }
}