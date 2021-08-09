package com.example.madlab;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button high;
    public ImageButton play;
    public EditText username;
    public String uname;
    public Spinner myspinner;
   int current=0;


    Intent intent,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        high= (Button) findViewById(R.id.high);
        play=(ImageButton) findViewById(R.id.play);
        username=(EditText) findViewById(R.id.name);
        myspinner=(Spinner) findViewById(R.id.spin);
        high.setOnClickListener(this);
        play.setOnClickListener(this);


        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Topic));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);
        myspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(current==position){
                    return;
                }
                else{
                    Intent intu=new Intent(MainActivity.this,quizpage.class);
                    startActivity(intu);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

            i=new Intent(getApplicationContext(),quizpage.class);
            intent=new Intent(getApplicationContext(),highscore.class);


    }

    @Override

        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.play:

                    MainActivity.this.startActivity(i);
                    break;
                case R.id.high:
                    String uname=username.getText().toString();

                    intent.putExtra("keyname",uname);
                    startActivity(intent);

                    break;

        }

    }
}