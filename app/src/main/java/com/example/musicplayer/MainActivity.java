package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    FloatingActionButton playButton, shuffleButton, loopButton;
    Button otherPageButton;
    SeekBar seekBar;
    TextView timeNowTV, timeOverTV, lyricsTV, translationTV;
    boolean wasPlaying;
    List<Integer> music;
    int nowPlaying, isLooping, isRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        shuffleButton = findViewById(R.id.shuffleButton);
        loopButton = findViewById(R.id.loopButton);
        otherPageButton = findViewById(R.id.otherPageButton);
        seekBar = findViewById(R.id.seekBar);
        timeNowTV = findViewById(R.id.textView);
        timeOverTV = findViewById(R.id.timeOver);
        lyricsTV = findViewById(R.id.lyrics);
        translationTV = findViewById(R.id.translation);

        isLooping = 0;
        isRandom = 0;
        wasPlaying = false;
        music = getRawIds();
        nowPlaying = 2;

        checkButtons();

        lyricsTV.setText("Fly me to the moon\n" +
                "And let me play among the stars\n" +
                "Let me see what spring is like\n" +
                "On a-Jupiter and Mars\n" +
                "In other words, hold my hand\n" +
                "In other words, darling, kiss me\n" +
                "Fill my heart with song\n" +
                "And let me sing for ever more\n" +
                "You are all I long for\n" +
                "All I worship and adore\n" +
                "In other words, please be true\n" +
                "In other words, I love you\n" +
                "Fill my heart with song\n" +
                "And let me sing for ever more\n" +
                "You are all I long for\n" +
                "All I worship and adore\n" +
                "In other words, please be true\n" +
                "In other words, I love you");
        translationTV.setText("Лети со мной на луну\n" +
                "И позволь мне поиграть среди звезд\n" +
                "Позвольте мне увидеть, что такое весна\n" +
                "На Юпитере и Марсе\n" +
                "Другими словами, держи меня за руку\n" +
                "Другими словами, дорогая, поцелуй меня\n" +
                "Наполни мое сердце песней\n" +
                "И позволь мне петь вечно\n" +
                "Ты все, чего я жажду\n" +
                "Все, что я поклоняюсь и обожаю\n" +
                "Другими словами, пожалуйста, будьте правдой\n" +
                "Другими словами, я люблю тебя\n" +
                "Наполни мое сердце песней\n" +
                "И позволь мне петь вечно\n" +
                "Ты все, чего я жажду\n" +
                "Все, что я поклоняюсь и обожаю\n" +
                "Другими словами, пожалуйста, будьте правдой\n" +
                "Другими словами, я люблю тебя");

        lyricsTV.setMovementMethod(new ScrollingMovementMethod());
        lyricsTV.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                translationTV.scrollTo(0, lyricsTV.getScrollY());
            }
        });

        Handler handler = new Handler();
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition / 1000);
                    timeNowTV.setText(getSongDuration(currentPosition));
                }
                handler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                    timeNowTV.setText(getSongDuration(progress * 1000));
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("mytag", "Song is finished");
                mediaPlayer.pause();
                playButton.setBackgroundResource(R.drawable.play_button);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    Log.d("mytag", "Pause song");
                    mediaPlayer.pause();
                    wasPlaying = false;
                    playButton.setBackgroundResource(R.drawable.play_button);
                }
                else {
                    Log.d("mytag", "Play song");
                    wasPlaying = true;
                    mediaPlayer.start();
                    playButton.setBackgroundResource(R.drawable.pause_button);
                }
            }
        });

        loopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isLooping()) {
                    Log.d("mytag", "Song isn't looped");
                    isLooping = 0;
                    mediaPlayer.setLooping(false);
                }
                else {
                    Log.d("mytag", "Song is looped");
                    isLooping = 1;
                    mediaPlayer.setLooping(true);
                }
            }
        });

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = (int)(Math.random() * music.size());
                nowPlaying = r;
                Log.d("mytag", "Random song " + Integer.toString(nowPlaying));
                checkButtons();
            }
        });

        otherPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TextAndTranslationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void skipSong(View v) {
        switch (v.getId()) {
            case R.id.skipLeftButton: {
                Log.d("mytag", "Previous song");
                if (nowPlaying == 0) {
                    nowPlaying = music.size();
                }
                nowPlaying--;
                break;
            }
            case R.id.skipRightButton: {
                Log.d("mytag", "Next song");
                nowPlaying++;
                if (nowPlaying == music.size()) {
                    nowPlaying = 0;
                }
                break;
            }
        }
        checkButtons();
    }

    private List<Integer> getRawIds() {
        List<Integer> rawIds = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for(int i = 0; i < fields.length; i++){
            int resId = getResources().getIdentifier(fields[i].getName(), "raw", getPackageName());
            rawIds.add(resId);
        }
        return rawIds;
    }

    private void checkButtons() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, music.get(nowPlaying));
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        if (wasPlaying){
            mediaPlayer.start();
            playButton.setBackgroundResource(R.drawable.pause_button);
        }
        if (isLooping == 1) {
            mediaPlayer.setLooping(true);
        }

        timeNowTV.setText("00:00");
        timeOverTV.setText(getSongDuration(mediaPlayer.getDuration()));
        Log.d("mytag", "Song duration: " + getSongDuration(mediaPlayer.getDuration()));
    }

    private String getSongDuration(int dur) {
        int songMin = dur / 1000 / 60;
        int songSec = dur / 1000 % 60;
        String res = Integer.toString(songMin) + ":";
        if (songMin / 10 == 0) {
            res = "0" + res;
        }
        if (songSec / 10 == 0) {
            res = res + "0" + Integer.toString(songSec);
        }
        else {
            res = res + Integer.toString(songSec);
        }
        return res;
    }
}