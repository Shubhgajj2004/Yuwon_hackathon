package com.shubh.yuwonmedstore.dashboard_fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.Cart;
import com.shubh.yuwonmedstore.Category_recyclerview_adapter;
import com.shubh.yuwonmedstore.Diagnostic_Form;
import com.shubh.yuwonmedstore.Items_category;
import com.shubh.yuwonmedstore.Model_category;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.FragmentHomeFragBinding;
import com.shubh.yuwonmedstore.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Home_frag extends Fragment {



    public Home_frag() {
        // Required empty public constructor
    }

FragmentHomeFragBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    ArrayList<Model_category> list= new ArrayList<>();
    ArrayList<Model_category> listKey=new ArrayList<>();
    Category_recyclerview_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentHomeFragBinding.inflate(inflater, container, false);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference().child("User").child(user.getUid()).child("Name");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name= (String) snapshot.getValue();
                binding.NameDashboard.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.home_anim);
        binding.ellipse4.setAnimation(animation);


        binding.rectangle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Fitness");
                startActivity(intent);
            }
        });
        binding.rectangle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Covid");
                startActivity(intent);
            }
        });
        binding.rectangle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Device");
                startActivity(intent);
            }
        });
         binding.rectangle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Generics");
                startActivity(intent);
            }
        });
         binding.rectangle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Ointment");
                startActivity(intent);
            }
        });
        binding.rectangle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Beauty");
                startActivity(intent);
            }
        });


        binding.white1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Vicks");
                startActivity(intent);
            }
        });
  binding.white2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Dabur");
                startActivity(intent);
            }
        });
  binding.white3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Himalaya");
                startActivity(intent);
            }
        });
  binding.white4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), Items_category.class);
                intent.putExtra("Cat","Nivea");
                startActivity(intent);
            }
        });


        binding.cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Cart.class);
                startActivity(intent);
            }
        });





        adapter= new Category_recyclerview_adapter(list,listKey,getContext());
        binding.recyclerviewHome.setAdapter(adapter);
        binding.recyclerviewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        database.getReference().child("Items").addValueEventListener(new ValueEventListener() {
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(preferences.getBoolean("value",false))
        {
            binding.speakFLoatingButton.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.speakFLoatingButton.setVisibility(View.GONE);
        }


        // For Diagnostic

        binding.DiagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext() , Diagnostic_Form.class));
            }
        });




        return binding.getRoot();
    }
}