package com.shubh.yuwonmedstore.dashboard_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.Category_recyclerview_adapter;
import com.shubh.yuwonmedstore.Model_category;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.ActivityItemsCategoryBinding;
import com.shubh.yuwonmedstore.databinding.FragmentAllItemsFragBinding;
import com.shubh.yuwonmedstore.databinding.FragmentHomeFragBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class All_Items_frag extends Fragment {


    public All_Items_frag() {
        // Required empty public constructor
    }

    FragmentAllItemsFragBinding binding;
    ArrayList<Model_category> list= new ArrayList<>();
    ArrayList<Model_category> listKey=new ArrayList<>();
    FirebaseDatabase database;
    Category_recyclerview_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAllItemsFragBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();


        adapter= new Category_recyclerview_adapter(list,listKey,getContext());
        binding.recyclerviewAllItems.setAdapter(adapter);
        binding.recyclerviewAllItems.setLayoutManager(new LinearLayoutManager(getContext()));
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
        binding.SearchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Model_category> list2=new ArrayList<>();
                ArrayList<Model_category> listKey2=new ArrayList<>();

                database.getReference().child("Items").orderByChild("Search").startAt(newText.toLowerCase()).endAt(newText.toLowerCase()+"\uf8ff").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list2.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            Model_category adp=snapshot1.getValue(Model_category.class);
                            list2.add(adp);
                        }
                        listKey2.clear();
                        for(DataSnapshot snapshot2 : snapshot.getChildren())
                        {
                            Model_category adp=new Model_category(snapshot2.getKey());

                            listKey2.add(adp);
                        }


                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                adapter=new Category_recyclerview_adapter(list2,listKey2,getContext());

                binding.recyclerviewAllItems.setAdapter(adapter);
                return false;
            }
        });


        return binding.getRoot();
    }
}