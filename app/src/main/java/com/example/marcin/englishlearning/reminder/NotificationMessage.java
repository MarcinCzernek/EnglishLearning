package com.example.marcin.englishlearning.reminder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marcin.englishlearning.R;

public class NotificationMessage extends AppCompatActivity {
TextView textView;

@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reminder_message);
    textView = findViewById(R.id.message);
    Bundle bundle = getIntent().getExtras();
    //call the data which is passed by another intent
    textView.setText(bundle.getString("message"));
}


}
