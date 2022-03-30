package com.shubh.admin.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.admin.Adding.Adding_user;
import com.shubh.admin.Items_modal_for_adp;
import com.shubh.admin.R;
import com.shubh.admin.add_promocode;
import com.shubh.admin.databinding.FragmentPromocodeFragBinding;
import com.shubh.admin.databinding.FragmentUserBinding;
import com.shubh.admin.itemAdapters;
import com.shubh.admin.promoAdapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Promocode_frag extends Fragment {

    public Promocode_frag() {
        // Required empty public constructor
    }

    promoAdapters adapters;
    ArrayList<promo_modal_for_adp> list=new ArrayList<>();
    ArrayList<promo_modal_for_adp> listKey=new ArrayList<>();

    FragmentPromocodeFragBinding binding;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding=FragmentPromocodeFragBinding.inflate(inflater,container,false);
      database=FirebaseDatabase.getInstance();



        adapters=new promoAdapters(list,listKey,getContext());

        binding.recyclerPromo.setAdapter(adapters);
        binding.recyclerPromo.setLayoutManager(new LinearLayoutManager(getContext()));

        database.getReference().child("PromoCodes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    promo_modal_for_adp adp=snapshot1.getValue(promo_modal_for_adp.class);
                    list.add(adp);
                }
                listKey.clear();
                for(DataSnapshot snapshot2 : snapshot.getChildren())
                {
                    promo_modal_for_adp adp=new promo_modal_for_adp(snapshot2.getKey());

                    listKey.add(adp);
                }



                adapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        binding.floatingbtnPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), add_promocode.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}