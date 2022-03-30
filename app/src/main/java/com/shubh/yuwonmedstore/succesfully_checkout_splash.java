package com.shubh.yuwonmedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class succesfully_checkout_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_checkout_splash);
        getSupportActionBar().hide();

        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },1000);
    }
}