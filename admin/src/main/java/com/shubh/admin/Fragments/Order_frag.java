package com.shubh.admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.admin.Model_orders_for_adp;
import com.shubh.admin.Orders_adapater;
import com.shubh.admin.R;
import com.shubh.admin.databinding.FragmentOrderFragBinding;
import com.shubh.admin.databinding.FragmentPromocodeFragBinding;

import java.util.ArrayList;


public class Order_frag extends Fragment {


    public Order_frag() {
        // Required empty public constructor
    }


    FirebaseDatabase database;
    ArrayList<Model_orders_for_adp> list=new ArrayList<>();
    ArrayList<Model_orders_for_adp> listKey=new ArrayList<>();

    Orders_adapater adapter;

    FragmentOrderFragBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOrderFragBinding.inflate(inflater,container,false);

        database=FirebaseDatabase.getInstance();
        adapter=new Orders_adapater(list,listKey,getContext());
        binding.orderRes.setAdapter(adapter);
      binding.orderRes.setLayoutManager(new LinearLayoutManager(getContext()));


        database.getReference().child("AllUsersOrders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                listKey.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Model_orders_for_adp adp =snapshot1.child("Details").getValue(Model_orders_for_adp.class);
                    Model_orders_for_adp adp2 = new Model_orders_for_adp(snapshot1.getKey());

                    list.add(adp);
                    listKey.add(adp2);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       adapter.notifyDataSetChanged();

        return  binding.getRoot();
    }
}