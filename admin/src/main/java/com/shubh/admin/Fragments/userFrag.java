package com.shubh.admin.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.admin.Adding.Adding_user;
import com.shubh.admin.Items_modal_for_adp;
import com.shubh.admin.R;
import com.shubh.admin.databinding.FragmentUserBinding;
import com.shubh.admin.itemAdapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class userFrag extends Fragment {



    public userFrag() {
        // Required empty public constructor
    }
    itemAdapters adapters;
   FragmentUserBinding binding;
    ArrayList<Items_modal_for_adp> list=new ArrayList<>();
    ArrayList<Items_modal_for_adp> listKey=new ArrayList<>();
    FirebaseDatabase database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentUserBinding.inflate(inflater, container, false);
        database=FirebaseDatabase.getInstance();

        binding.floatingbtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Adding_user.class);
                startActivity(intent);
            }
        });

         adapters=new itemAdapters(list,listKey,getContext());

        binding.recyclerUser.setAdapter(adapters);
        binding.recyclerUser.setLayoutManager(new LinearLayoutManager(getContext()));

        database.getReference().child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Items_modal_for_adp adp=snapshot1.getValue(Items_modal_for_adp.class);
                    list.add(adp);
                }
                listKey.clear();
                 for(DataSnapshot snapshot2 : snapshot.getChildren())
                {
                  Items_modal_for_adp adp=new Items_modal_for_adp(snapshot2.getKey());

                            listKey.add(adp);
                }

                 //To get in random order.
                long seed = System.nanoTime();
                Collections.shuffle(list, new Random(seed));
                Collections.shuffle(listKey, new Random(seed));


                adapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.serach,menu);
        MenuItem item=menu.findItem(R.id.serach_recycler);

        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextChange(String newText) {

              processsearch(newText);

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
        });
    }

    private void processsearch(String newText) {

        ArrayList<Items_modal_for_adp> list2=new ArrayList<>();
        ArrayList<Items_modal_for_adp> listKey2=new ArrayList<>();

        database.getReference().child("Items").orderByChild("Search").startAt(newText.toLowerCase()).endAt(newText.toLowerCase()+"\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Items_modal_for_adp adp=snapshot1.getValue(Items_modal_for_adp.class);
                    list2.add(adp);
                }
                listKey2.clear();
                for(DataSnapshot snapshot2 : snapshot.getChildren())
                {
                    Items_modal_for_adp adp=new Items_modal_for_adp(snapshot2.getKey());

                    listKey2.add(adp);
                }


                adapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapters=new itemAdapters(list2,listKey2,getContext());

        binding.recyclerUser.setAdapter(adapters);

    }






}