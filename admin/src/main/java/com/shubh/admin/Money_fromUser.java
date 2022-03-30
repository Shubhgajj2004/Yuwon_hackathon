package com.shubh.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.admin.databinding.ActivityMoneyFromUserBinding;

import java.util.ArrayList;

public class Money_fromUser extends AppCompatActivity {


    FirebaseDatabase database;
    ArrayList<Model_moner> list =new ArrayList<>();
    Money_adapter adapter;
    ActivityMoneyFromUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoneyFromUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database=FirebaseDatabase.getInstance();

        binding.userNameDetail.setText(getIntent().getStringExtra("Name"));
        binding.userContactDetail.setText(getIntent().getStringExtra("ContactNo"));
        binding.cityUserDetail.setText(getIntent().getStringExtra("City"));
        binding.addressUserDetail.setText(getIntent().getStringExtra("Address"));
        binding.orderdetailId.setText(getIntent().getStringExtra("Id"));
        binding.timeOrder.setText(getIntent().getStringExtra("Time"));
        binding.userdPromoDetail.setText(getIntent().getStringExtra("UsedPromo"));
        binding.TotalAmountDetail.setText("₹ "+getIntent().getStringExtra("WithoutPromo"));
        binding.PayabaleAmoutDetail.setText("₹ "+getIntent().getStringExtra("PayableAmount"));
        binding.StatusDetail.setText(getIntent().getStringExtra("Status"));



        adapter=new Money_adapter(list,getApplicationContext());
        binding.orderDetailRecylerview.setAdapter(adapter);
        binding.orderDetailRecylerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        database.getReference().child("AllUsersOrders").child(getIntent().getStringExtra("Id")).child("OrderItems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    Model_moner adp = snapshot1.getValue(Model_moner.class);

                    list.add(adp);


                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.deliverdbtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DatabaseReference reference= database.getReference().child("AllUsersOrders").child(getIntent().getStringExtra("Id")).child("Details");
                       reference.child("Status").setValue("Delivered").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {



                        reference.child("UID").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                database.getReference().child("User").child(snapshot.getValue(String.class)).child("Orders").child("SuccesfullOrders").child(getIntent().getStringExtra("Id")).child("Details").child("Status").setValue("Delivered");
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });





                    }
                });



            }
        });
 binding.CanceledDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DatabaseReference reference= database.getReference().child("AllUsersOrders").child(getIntent().getStringExtra("Id")).child("Details");
                       reference.child("Status").setValue("Canceled").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {



                        reference.child("UID").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                database.getReference().child("User").child(snapshot.getValue(String.class)).child("Orders").child("SuccesfullOrders").child(getIntent().getStringExtra("Id")).child("Details").child("Status").setValue("Canceled");
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                finish();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });





                    }
                });



            }
        });


    }
}