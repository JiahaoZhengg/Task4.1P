package com.example.studytimerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView shuchu;
    ImageButton start;
    ImageButton pause;
    ImageButton stop;
    EditText task;
    Chronometer ch;
    long time;

    String Time;
    long recordingTime = 0;

    SharedPreferences share;
    String jizhu;
    String chuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shuchu = findViewById(R.id.shuchu);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        task = findViewById(R.id.task);
        ch = findViewById(R.id.ch);
        shuchu.setText("You spent"  +     ch.getText().toString()  +" on "+task.getText().toString()+" last time");
        share = getSharedPreferences("com.example.studytimerapp",MODE_PRIVATE);
        share();


    /*start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ch.setBase(SystemClock.elapsedRealtime() - recordingTime );

            ch.start();
            time = ch.getBase();

        }
    });*/

    pause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ch.stop();
            recordingTime = SystemClock.elapsedRealtime() - ch.getBase();
        }
    });
    stop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            jizhu ="You spent "  +ch.getText().toString()  +" on "+task.getText().toString()+" last time";
            SharedPreferences.Editor editor = share.edit();
            editor.putString(chuan,jizhu);
            editor.apply();
            shuchu.setText(jizhu);
            recordingTime = 0;
            ch.setBase(SystemClock.elapsedRealtime());
            recordingTime=0;
            ch.stop();


        }
    });


    if (savedInstanceState != null)
        {
            ch.setBase(savedInstanceState.getLong(Time));
            ch.start();
            time= ch.getBase();


        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(Time ,time);

    }


    public  void  share(){
      String show = share.getString(chuan,"");
      shuchu.setText(show);


    }
    public void start(View view){


        ch.setBase(SystemClock.elapsedRealtime() - recordingTime );

        ch.start();
        time = ch.getBase();



    }




}