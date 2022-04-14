package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityDetailOrdersBinding;

import java.util.ArrayList;

public class Detail_Orders extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    ArrayList<Model_Order_details> list=new ArrayList<>();
    orders_detail_adapter adapter;


    ActivityDetailOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ColorDrawable drawable=new ColorDrawable(Color.parseColor("#FFFFFF"));

        getSupportActionBar().setTitle("Order details");
        getSupportActionBar().setBackgroundDrawable(drawable);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        adapter=new orders_detail_adapter(list,getApplicationContext());
        binding.orderDetailRecylerview.setAdapter(adapter);
        binding.orderDetailRecylerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        database.getReference().child("User").child(user.getUid()).child("Orders").child("SuccesfullOrders").child(getIntent().getStringExtra("Id")).child("OrderItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                        Model_Order_details adp = snapshot1.getValue(Model_Order_details.class);

                        list.add(adp);


                }


                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        binding.orderdetailId.setText(getIntent().getStringExtra("Id"));
        binding.timeOrder.setText(getIntent().getStringExtra("Time"));
        binding.userdPromoDetail.setText(getIntent().getStringExtra("UsedPromo"));
        binding.TotalAmountDetail.setText("₹ "+getIntent().getStringExtra("WithoutPromo"));
        binding.PayabaleAmoutDetail.setText("₹ "+getIntent().getStringExtra("PayableAmount"));
        binding.StatusDetail.setText(getIntent().getStringExtra("Status"));



    }
}