package com.example.marcin.englishlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marcin.englishlearning.audioplayer.PlayerActivity;
import com.example.marcin.englishlearning.recording.RecordLaunchActivity;
import com.example.marcin.englishlearning.reminder.MainReminderActivity;

public class MainActivity extends AppCompatActivity {

    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit = findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }

    //Odtwarzacz
    public void createNewIntentPlayer (View view){
        Intent i = new Intent(this, PlayerActivity.class);
        startActivity(i);
    }

    //Kalendarz
    public void createNewIntentReminder (View view){
        Intent i = new Intent(this, MainReminderActivity.class);
        startActivity(i);
    }

    //Rekorder
    public void createNewIntentRecorder (View view){
        Intent i = new Intent(this, RecordLaunchActivity.class);
        startActivity(i);
    }
}