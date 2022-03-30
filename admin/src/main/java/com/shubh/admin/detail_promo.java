package com.shubh.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.shubh.admin.databinding.ActivityDetailPromoBinding;

public class detail_promo extends AppCompatActivity {

    FirebaseDatabase database;
    ActivityDetailPromoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailPromoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        binding.PromoTxt2.setText(getIntent().getStringExtra("Promo"));
        binding.minAmount2.setText(getIntent().getStringExtra("minAmount"));
        binding.discountPercent2.setText(getIntent().getStringExtra("percent"));





        binding.deleteItembtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarPromo2.setVisibility(View.VISIBLE);
                DatabaseReference reference=database.getReference().child("PromoCodes");
                reference.child(getIntent().getStringExtra("Key")).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.progressBarPromo2.setVisibility(View.GONE);
                        binding.processCheckPromo2.setText("Deleted");
                        binding.processCheckPromo2.setVisibility(View.VISIBLE);
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        finish();
                    }
                });

            }
        });

        binding.updateItembtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarPromo2.setVisibility(View.VISIBLE);
                {
                    DatabaseReference reference=database.getReference().child("PromoCodes").child(getIntent().getStringExtra("Key"));

                    reference.child("MinAmount").setValue(binding.minAmount2.getText().toString());
                    reference.child("PercentDiscount").setValue(binding.discountPercent2.getText().toString());
                    reference.child("Promo").setValue(binding.PromoTxt2.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            binding.progressBarPromo2.setVisibility(View.GONE);
                            binding.processCheckPromo2.setText("Updated");
                            binding.processCheckPromo2.setVisibility(View.VISIBLE);
                            startActivity(new Intent(getApplicationContext(),Dashboard.class));
                            finish();
                        }
                    });
                }



            }
        });

    }
}