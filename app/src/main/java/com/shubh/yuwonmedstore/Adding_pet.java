package com.shubh.yuwonmedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.yuwonmedstore.databinding.ActivityAddingPetBinding;
import com.shubh.yuwonmedstore.databinding.ActivityProfilePetBinding;

public class Adding_pet extends AppCompatActivity {


    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;

    RadioButton checkedbutton1;
    ActivityAddingPetBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddingPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        binding.SubmitPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ButtonsID1 = binding.RadioGrpPet.getCheckedRadioButtonId();
                checkedbutton1 = (RadioButton) findViewById(ButtonsID1);


                DatabaseReference databaseReference = database.getReference().child("User").child(user.getUid()).child("Pets").push();

                databaseReference.child("Name").setValue(binding.petName.getText().toString());
                databaseReference.child("Type").setValue(binding.petType.getText().toString());
                databaseReference.child("DOB").setValue(binding.petDOB.getText().toString());
                databaseReference.child("Sex").setValue(checkedbutton1.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(getApplicationContext() , petCare_home.class));
                    }
                });


            }
        });





    }
}