package com.example.marcin.englishlearning.recording;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.marcin.englishlearning.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class RecordLaunchActivity extends AppCompatActivity {

    private ConstraintLayout parent;
    private Button record, stop;
    private TextView status;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;


    private static String fileName = null;
    //zmienna na stałą pozwolenia na odtwarzanie
    public static final int RECORD_AUDIO_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        //set views id
        parent = findViewById(R.id.parent);
        status = findViewById(R.id.status);
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);


        //start audio recording
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start audio recording.
                startRecording();
            }
        });
        //stop audio recording
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopRecording();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //ta metoda jest wywoływana, gdy użytkownik chce
        //udzielić zgody na nagrywanie dźwięku.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RECORD_AUDIO_PERMISSION:
                if (grantResults.length > 0) {
                    boolean recordPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (recordPermission && storePermission) {
                        Toast.makeText(getApplicationContext(), "Pozwolenie przyznane", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Pozwolenie oddalone", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }



    private void startRecording() {
        // sprawdza metodę pozwolenia czu użytkownik ma pozwolenie na nagrywanie i przechowywanie dzwięku
        if(CheckPermissions()){

            //inicjalizuję zmienną pliku ze ścieżka nagrywanego pliku audio
            fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            fileName += "/audioRecording.3gp";

            //inicializacja klasy media recorder
            mediaRecorder = new MediaRecorder();
            //ustawia żródło dzwięku aby było używane do nagrywania
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //wybierz format wyjściowy dla dzwięku
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //wybierz encoder dzwięku dla nagranego dzwięku
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //wybierz lokację pliku wyjściowego dla nagranego audio
            mediaRecorder.setOutputFile(fileName);
            try{
                //przygotowuje rejestr aby rozpoczął nagrywanie i kodowanie danych
                mediaRecorder.prepare();
            } catch (IOException e){
                Log.e("TAG", "prepare() failed");

            }
            //zacznij nagrywanie audio
            mediaRecorder.start();
            status.setText("Nagrywam dzwięk");
        }else{
            //gdy pozwolenie na nagrywanie nie jest przydzielone
            // przez użytkownika ta metoda poprosi o pozwolenie na działanie mikrofonu i pamięci.
            RequestPermission();
        }
    }

    private void stopRecording() {

        //zatrzymaj nagrywanie dzwięku
        mediaRecorder.stop();

        //uwolnij obiekt rejestratora dzwięku
        mediaRecorder.release();
        mediaRecorder = null;

        status.setText("Nagrywanie zatrzymane");

    }



    //ta metoda jest stosowana do sprawdzania pozwoleń
    private boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }

    //ta metoda jest używana do żądania
    //pozwolenia na nagrywanie i przechowywanie dźwięku.
    private void RequestPermission() {
        ActivityCompat.requestPermissions(RecordLaunchActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, RECORD_AUDIO_PERMISSION);
    }

    @Override
    public void onStop(){
        super.onStop();
        // releasing the media player and the media recorder object
        // and set it to null
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}