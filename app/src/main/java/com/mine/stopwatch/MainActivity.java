package com.mine.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView times;
    Button Start, Pause, Reset;
    long milisecTime, Startime, Buff, Update = 0L;
    int second,seconds, minute, hour;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        times = (TextView)findViewById(R.id.time);
        Start = (Button)findViewById(R.id.Start);
        Pause = (Button)findViewById(R.id.Pause);
        Reset = (Button)findViewById(R.id.Reset);
        handler = new Handler();

        Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Startime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                Reset.setEnabled(false);
            }
        });

        Pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Buff += milisecTime;
                handler.removeCallbacks(runnable);
                Reset.setEnabled(true);

            }
        });

        Reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                milisecTime = 0L;
                Startime = 0L;
                Buff = 0L;
                Update = 0L;
                second = 0;
                minute = 0;
                hour = 0;

                times.setText("00:00:00");
            }
        });
    }

        public Runnable runnable = new Runnable() {

            public void run() {

                milisecTime = SystemClock.uptimeMillis() - Startime;
                Update = Buff + milisecTime;
                second = (int) (Update / 1000);
                minute = second / 60;
                seconds = second % 60;
                hour = seconds % 60;
                hour = seconds / 60;


                times.setText("0" + hour + ":"
                        + String.format("%02d", minute) + ":"
                        + String.format("%02d",seconds));

                handler.postDelayed(this, 0);
            }

        };

}
