package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityPetCareHomeBinding;
import com.shubh.yuwonmedstore.databinding.ActivityProfilePetBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class profile_Pet extends AppCompatActivity {



    pets_adapter_profile adapter;

    ArrayList<Model_for_pets_profile> list = new ArrayList<>();
    ActivityProfilePetBinding binding;
    FirebaseDatabase database;
    FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfilePetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        getSupportActionBar().hide();
        binding.floatingbtnAddingPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , Adding_pet.class));
            }
        });




        database.getReference().child("User").child(user.getUid()).child("Pets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Model_for_pets_profile adp=snapshot1.getValue(Model_for_pets_profile.class);
                    list.add(adp);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter=new pets_adapter_profile(list,getApplicationContext());

        binding.recyclerPetProfile.setAdapter(adapter);
        binding.recyclerPetProfile.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




    }
}