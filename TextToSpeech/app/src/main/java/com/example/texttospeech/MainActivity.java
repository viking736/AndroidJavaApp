package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Button btnSpeak;
    private EditText entText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech = new TextToSpeech(this, this);
        entText = (EditText) findViewById(R.id.et_text);
        btnSpeak = (Button) findViewById(R.id.btn_speak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please input some text", Toast.LENGTH_LONG).show();
                }else{
                    speak(entText.getText().toString());
                }
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.UK);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language not supported :(");
            }
        }else {
            Log.e("TTS", "Initialization failed");
        }
    }

    private void speak(String text){
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "");
    }
}