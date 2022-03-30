package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityMedDetailBinding;

public class Med_detail extends AppCompatActivity {

    ActivityMedDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMedDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        ColorDrawable drawable=new ColorDrawable(Color.parseColor("#FFFFFF"));
        actionBar.setBackgroundDrawable(drawable);


        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("uri")).into(binding.imageDetail);
        binding.nameDetail.setText(getIntent().getStringExtra("name"));
        binding.DescriptionDetail.setText(getIntent().getStringExtra("description"));
        binding.price.setText("₹ "+getIntent().getStringExtra("price"));
        binding.MRPDetail.setText("MRP "+getIntent().getStringExtra("MRP"));
        binding.MRPDetail.setPaintFlags(binding.MRPDetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.offDetail.setText(Integer.toString((int) ((Integer.parseInt(getIntent().getStringExtra("MRP"))-Integer.parseInt(getIntent().getStringExtra("price")))*100/(Integer.parseInt(getIntent().getStringExtra("MRP")))))+"% off");





        if(user !=null) {


            database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if (snapshot1.getKey().equals(getIntent().getStringExtra("Key"))) {

                            if(!snapshot1.child("Amount").getValue(String.class).equals("0")) {

                                binding.AddToCart2.setVisibility(View.GONE);
                                binding.plus.setVisibility(View.VISIBLE);
                                binding.number.setVisibility(View.VISIBLE);
                                binding.minus.setVisibility(View.VISIBLE);

                            }
                            if(snapshot1.child("Amount").getValue(String.class).equals("0")) {

                                DatabaseReference ref1=database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(getIntent().getStringExtra("Key"));
                            ref1.removeValue();

                                binding.AddToCart2.setVisibility(View.VISIBLE);
                                binding.plus.setVisibility(View.GONE);
                                binding.number.setVisibility(View.GONE);
                                binding.minus.setVisibility(View.GONE);
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        }



        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user !=null)
                {



                   DatabaseReference ref= database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(getIntent().getStringExtra("Key")).child("Amount");
                   ref.addValueEventListener(new ValueEventListener() {

                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {


                           if(snapshot.exists()){
                           String value= snapshot.getValue(String.class);


                               val = Integer.parseInt(value);
                           }

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }


                   });
                   if(val!=0) {
                       val = val + 1;
                       ref.setValue(Integer.toString(val));
                   }


                }
            }
        });

        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user !=null)
                {



                   DatabaseReference ref= database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(getIntent().getStringExtra("Key")).child("Amount");
                   ref.addValueEventListener(new ValueEventListener() {

                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {

                           if(snapshot.exists()) {
                           String value= snapshot.getValue(String.class);

                               val = Integer.parseInt(value);
                           }

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }


                   });
                   if(val!=0&&val>0) {
                       val = val - 1;
                       ref.setValue(Integer.toString(val));
                   }


                }
            }
        });



        if(user !=null) {

            database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(getIntent().getStringExtra("Key")).child("Amount").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {
                        String value = snapshot.getValue(String.class);
                        binding.number.setText(value);
                        binding.MRPDetail.setText("MRP "+Integer.toString(Integer.parseInt(value)*Integer.parseInt(getIntent().getStringExtra("MRP"))));
                        binding.price.setText("₹ "+Integer.toString(Integer.parseInt(value)*Integer.parseInt(getIntent().getStringExtra("price"))));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        binding.AddToCart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null) {
                    DatabaseReference reference = database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(getIntent().getStringExtra("Key"));
                    reference.child("Amount").setValue("1");
                    reference.child("Name").setValue(getIntent().getStringExtra("name"));
                    reference.child("Price").setValue(getIntent().getStringExtra("price"));
                    reference.child("Image").setValue(getIntent().getStringExtra("uri"));
                    reference.child("MRP").setValue(getIntent().getStringExtra("MRP"));
                    binding.AddToCart2.setVisibility(View.GONE);
                    binding.plus.setVisibility(View.VISIBLE);
                    binding.number.setVisibility(View.VISIBLE);
                    binding.minus.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Added succesfully", Toast.LENGTH_SHORT).show();
                }

            }
        });








    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}