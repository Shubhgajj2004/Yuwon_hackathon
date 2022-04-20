package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityPetCareHomeBinding;
import com.shubh.yuwonmedstore.databinding.ActivityProductsPetBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class products_Pet extends AppCompatActivity {

    ActivityProductsPetBinding binding;
    ArrayList<Model_category> list= new ArrayList<>();
    ArrayList<Model_category> listKey=new ArrayList<>();
    FirebaseDatabase database;
    Category_recyclerview_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProductsPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        database=FirebaseDatabase.getInstance();

        adapter= new Category_recyclerview_adapter(list,listKey,getApplicationContext());
        binding.petRecyclerview.setAdapter(adapter);
        binding.petRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        database.getReference().child("Items").orderByChild("Pet").equalTo("true").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Model_category adp = snapshot1.getValue(Model_category.class);
                    list.add(adp);
                }

                listKey.clear();
                for(DataSnapshot snapshot2 : snapshot.getChildren())
                {
                    Model_category adp= new Model_category(snapshot2.getKey());
                    listKey.add(adp);
                }
                long seed = System.nanoTime();
                Collections.shuffle(list, new Random(seed));
                Collections.shuffle(listKey, new Random(seed));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}