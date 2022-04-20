package com.shubh.yuwonmedstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import com.shubh.yuwonmedstore.databinding.ActivitySpeachRecognitionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class speach_Recognition extends AppCompatActivity {

    ActivitySpeachRecognitionBinding binding;
    speech_adapter adapter;
    ArrayList<modal_speech> list1 = new ArrayList<>();
    TextToSpeech tts;
    SpeechRecognizer recognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySpeachRecognitionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());






        adapter = new speech_adapter(list1, getApplicationContext());
        binding.SpeechRecyclerView.setAdapter(adapter);
        binding.SpeechRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        //list1.add(new modal_speech("What you can do for me" ,"sender"));

        list1.add(new modal_speech("Hello , What i can i do for you. You can do many things on me from searching medicines to order. \n 1.) Say ' find ' to search the medicines. ) " , "reciever"));
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS)
                {
                    tts.setLanguage(Locale.UK);
                    tts.setSpeechRate(1.05f);
                    tts.speak("Hello , What i can i do for you. You can do many things on me from searching medicines to order. \n 1.) Say ' find ' to search the medicines. " , TextToSpeech.QUEUE_ADD , null);
                }
            }
        });










        adapter.notifyDataSetChanged();





    }

    public void startTalking()
    {
        recognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    }
}