package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityCartBinding;
import com.shubh.yuwonmedstore.databinding.FragmentAllItemsFragBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cart extends AppCompatActivity {

    ArrayList<Model_category> list= new ArrayList<>();
    ArrayList<Model_category> listKey=new ArrayList<>();
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
   cart_adapter adapter;


    //test for sum
    int sum=0;
//////////////


    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();


        adapter= new cart_adapter(list,listKey,getApplicationContext());
        binding.cartRecycler.setAdapter(adapter);
        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addValueEventListener(new ValueEventListener() {
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



                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        if(user !=null)
        {

            database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addValueEventListener(new ValueEventListener() {
                //int sum = 0;
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                       sum=0;
                        for (DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            String price=snapshot1.child("Price").getValue(String.class);
                            String Amount = snapshot1.child("Amount").getValue(String.class);
                            sum=sum+(Integer.parseInt(price)*Integer.parseInt(Amount));

                        }

                        binding.totalPriceCart.setText("₹ "+Integer.toString(sum));
                    }
                    catch (Exception e)
                    {

                    }




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }

        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),checkout.class);
                intent.putExtra("TotalPay",Integer.toString(sum));
                startActivity(intent);
            }
        });







    }
}