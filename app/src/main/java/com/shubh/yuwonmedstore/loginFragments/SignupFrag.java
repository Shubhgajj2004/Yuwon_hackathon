package com.shubh.yuwonmedstore.loginFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.OtpAndName;
import com.shubh.yuwonmedstore.databinding.FragmentSignupBinding;


public class SignupFrag extends Fragment {


    public SignupFrag() {
        // Required empty public constructor
    }



        FragmentSignupBinding binding;
        String combine_phone;
        FirebaseDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentSignupBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();

        binding.Signupsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number=binding.PhoneNo.getText().toString();
                combine_phone="+91"+phone_number;
                if(phone_number.isEmpty()||phone_number.length()!=10)
                {   // to make this text view visible.
                    binding.warning.setText("enter valid mobile number");
                    binding.warning.setVisibility(View.VISIBLE);

                }
                else
                {
                    DatabaseReference reference= database.getReference().child("User");
                    reference.orderByChild("PhoneNo").equalTo(combine_phone).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.getValue()!=null)
                            {
                                binding.warning.setText("You are already registered , Please login");
                                binding.warning.setVisibility(View.VISIBLE);
                            }

                            else {

                                Toast.makeText(getContext(), combine_phone, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getContext(), OtpAndName.class);

                                intent.putExtra("MobileNo",combine_phone);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                }




            }
        });



        return binding.getRoot();
    }


}