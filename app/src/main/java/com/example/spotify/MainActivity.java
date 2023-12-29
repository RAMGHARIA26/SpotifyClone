package com.example.spotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player,player1;
    SeekBar seekBar;
    ImageView imageView;

    Handler handler ;
    String curr = "play";

    public void play(View view){
        if(curr.equals("play")) {
            player.start();
            player1.start();
            imageView.setImageResource(R.drawable.pause);
            curr = "pause";
            initSeekBar();
        }else{
            player.pause();
            player1.pause();
            imageView.setImageResource(R.drawable.play);
            curr = "play";
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        player = MediaPlayer.create(this,R.raw.sitdown);
        player1 = MediaPlayer.create(this,R.raw.sitdown);
        player1.setVolume(0.0f,0.0f);

         seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(player.getDuration());
        handler = new Handler();




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                player.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public  void initSeekBar(){
        handler = new Handler();


        handler.postDelayed(new  Runnable() {
            @Override
            public void run() {
                try {
                    seekBar.setProgress(player.getCurrentPosition());
                    handler.postDelayed(this,200);
                }catch (Exception e){
                    seekBar.setProgress(0);
                }
            }
        },0);
    };

}