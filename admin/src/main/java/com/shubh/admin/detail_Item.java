package com.shubh.admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shubh.admin.databinding.ActivityDetailItemBinding;

public class detail_Item extends AppCompatActivity {

    ActivityDetailItemBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri result1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database= FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        binding.ItemName2.setText(getIntent().getStringExtra("name"));
        binding.ItemDes2.setText(getIntent().getStringExtra("description"));
        binding.ItemAmount2.setText(getIntent().getStringExtra("Amount"));
        binding.ItemPrice2.setText(getIntent().getStringExtra("MRP"));
        binding.ItemPrice3.setText(getIntent().getStringExtra("price"));

        Glide.with(detail_Item.this).load(getIntent().getStringExtra("uri")).into(binding.itemImage2);



        binding.deleteItembtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               binding.progressBar2.setVisibility(View.VISIBLE);
                DatabaseReference reference=database.getReference().child("Items");
                reference.child(getIntent().getStringExtra("Key")).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        StorageReference ref= storage.getReference().child(getIntent().getStringExtra("name"));
                        ref.delete();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.progressBar2.setVisibility(View.GONE);
                        binding.processCheck2.setText("Deleted");
                        binding.processCheck2.setVisibility(View.VISIBLE);
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        finish();
                    }
                });

            }
        });

        binding.updateItembtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar2.setVisibility(View.VISIBLE);
               {
                                DatabaseReference reference=database.getReference().child("Items").child(getIntent().getStringExtra("Key"));
                                reference.child("Amount").setValue(binding.ItemAmount2.getText().toString());
                                reference.child("Desciption").setValue(binding.ItemDes2.getText().toString());
                                reference.child("MRP").setValue(binding.ItemPrice2.getText().toString());
                                reference.child("Search").setValue(binding.ItemName2.getText().toString().toLowerCase());

                                reference.child("Name").setValue(binding.ItemName2.getText().toString());
                                reference.child("Price").setValue(binding.ItemPrice3.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        binding.progressBar2.setVisibility(View.GONE);
                                        binding.processCheck2.setText("Updated");
                                        binding.processCheck2.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                        finish();
                                    }
                                });
                            }



            }
        });
    }

}