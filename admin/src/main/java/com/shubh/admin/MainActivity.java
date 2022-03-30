package com.shubh.admin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.admin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user=auth.getCurrentUser();

        binding.submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(binding.Emailtxt.getText().toString(), binding.passwordtxt.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(MainActivity.this,Dashboard.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });




    }

    //by only 1st time logging it will alwys open home activity...now util you dont dlete it
    //it will always open home activity.
    protected void onStart()
    {
        super.onStart();
        if(user!=null)
        {
            Intent intent=new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent);
            //BY doing this even after it close all the task that exist before opening this task.
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        }

    }

}