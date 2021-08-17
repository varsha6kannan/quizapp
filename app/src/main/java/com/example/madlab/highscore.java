package com.example.madlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class highscore extends AppCompatActivity implements View.OnClickListener {
    public Button bt;
    public TextView pname, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        bt = (Button) findViewById(R.id.home);
        bt.setOnClickListener(this);
        pname = (TextView) findViewById(R.id.pname);
        score = (TextView) findViewById(R.id.score);

        String username = getIntent().getStringExtra("keyname");
        String userScore = getIntent().getStringExtra("score");
        score.setText(userScore);
        pname.setText(username);
    }

    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(highscore.this, MainActivity.class);
        startActivity(intent1);


    }
}