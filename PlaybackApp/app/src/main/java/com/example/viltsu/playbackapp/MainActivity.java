package com.example.viltsu.playbackapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener completionListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(MainActivity.this, "I am done", Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    private int maxVolume = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View playbtn = findViewById(R.id.play);

        mp = MediaPlayer.create(this, R.raw.mix1);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        View pausebtn = findViewById(R.id.pause);

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });

        View backbtn = findViewById(R.id.back);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                mp.seekTo(0);
                mp.start();
            }
        });

        View infobtn = findViewById(R.id.stop);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Now Playing some tunes...", Toast.LENGTH_LONG).show();
            }
        });

        View volumeup = findViewById(R.id.volumeup);

        volumeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currVolume = getVolumeControlStream();

                String volume = String.valueOf(currVolume);

                mp.setVolume(1.0f, 1.0f);
                Toast.makeText(MainActivity.this, "Am i working or not?" + volume, Toast.LENGTH_LONG).show();
            }
        });

        View volumedown = findViewById(R.id.volumedown);

        volumedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currVolume = getVolumeControlStream();

                String volume = String.valueOf(currVolume);

                mp.setVolume(0.1f, 0.1f);
                Toast.makeText(MainActivity.this, "Am i working or not?" + volume, Toast.LENGTH_LONG).show();

            }
        });

        mp.setOnCompletionListener(completionListener);
    }

    //free resources when user switches activity
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    //helper method for releasing MEdiaPlayer
    public void releaseMediaPlayer(){
        if(mp != null){
            mp.release();
            mp = null;
        }
    }
}
