package com.shubh.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.admin.databinding.ActivityAddPromocodeBinding;

public class add_promocode extends AppCompatActivity {

    FirebaseDatabase database;
    ActivityAddPromocodeBinding binding;
    String spinnerItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddPromocodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              spinnerItem=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.promoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarPromo.setVisibility(View.VISIBLE);
                DatabaseReference reference= database.getReference().child("PromoCodes").push();
                reference.child("Promo").setValue(binding.PromoTxt.getText().toString());
                reference.child("MinAmount").setValue(binding.minAmount.getText().toString());
                reference.child("Type").setValue(spinnerItem);
                reference.child("PercentDiscount").setValue(binding.discountPercent.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        binding.progressBarPromo.setVisibility(View.GONE);
                        binding.processCheckPromo.setText("Submitted");
                        binding.processCheckPromo.setVisibility(View.VISIBLE);

                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));

                    }
                });
            }
        });
    }
}