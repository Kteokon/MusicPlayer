package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

public class TextAndTranslationActivity extends AppCompatActivity {
    TextView lyricsTV, translationTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texts);

        lyricsTV = findViewById(R.id.lyrics);
        translationTV = findViewById(R.id.translation);

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
    }
}