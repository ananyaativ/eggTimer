package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time;
    SeekBar seekBar;
    Button button;
    boolean counter=false;
    CountDownTimer countDownTimer;

   public void secsToMins(int sec){
       int mins=sec/60;
       int remainder=sec%60;
       String min=Integer.toString(mins);
       String remain=Integer.toString(remainder);
       if(mins<10){
           min="0"+Integer.toString(mins);

       }
       if(remainder<10){
           remain="0"+Integer.toString(remainder);
       }
       time.setText(min +":"+remain);

    }
    public  void reset(){
        button.setText("GO!");
        seekBar.setEnabled(true);
        time.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        counter = false;

    }

  // MediaPlayer horn=MediaPlayer.create(this,R.raw.horn);


  public  void click(View view) {
      if (counter) {
         reset();

      } else {
          time = (TextView) findViewById(R.id.textView);
          counter = true;
          button.setText("STOP!");
          seekBar.setEnabled(false);

          countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
              public void onTick(long millisecondsleft) {
                  int timeleft = (int) millisecondsleft / 1000;
                  secsToMins(timeleft);

              }

              public void onFinish() {
                  MediaPlayer horn = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                  horn.start();
                  reset();


              }

          }.start();


      }
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar) findViewById(R.id.seekBar3);
        time=(TextView) findViewById(R.id.textView);
        button=findViewById(R.id.button2);


        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secsToMins(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
    }
}