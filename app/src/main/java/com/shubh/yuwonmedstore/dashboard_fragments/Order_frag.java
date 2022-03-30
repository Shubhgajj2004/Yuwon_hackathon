package com.shubh.yuwonmedstore.dashboard_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.Model_Orders;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.FragmentHomeFragBinding;
import com.shubh.yuwonmedstore.databinding.FragmentOrderFragBinding;
import com.shubh.yuwonmedstore.orders_adapter;

import java.util.ArrayList;


public class Order_frag extends Fragment {

    public Order_frag() {
        // Required empty public constructor
    }


    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    ArrayList<Model_Orders> list=new ArrayList<>();
    ArrayList<Model_Orders> listId=new ArrayList<>();
    orders_adapter adapter;
    FragmentOrderFragBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOrderFragBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();

        adapter=new orders_adapter(list,listId,getContext());
        binding.orderFragRecycler.setAdapter(adapter);
        binding.orderFragRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


            database.getReference().child("User").child(user.getUid()).child("Orders").child("SuccesfullOrders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    list.clear();
                    listId.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        Model_Orders adp =snapshot1.child("Details").getValue(Model_Orders.class);
                        Model_Orders adp2 = new Model_Orders(snapshot1.getKey());
                        list.add(adp);
                        listId.add(adp2);
                    }


                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });






        return binding.getRoot();
    }
}