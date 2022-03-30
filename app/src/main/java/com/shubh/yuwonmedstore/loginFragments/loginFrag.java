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
import com.shubh.yuwonmedstore.Dashboard;
import com.shubh.yuwonmedstore.OtpAndName;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.FragmentLoginBinding;
import com.shubh.yuwonmedstore.databinding.FragmentSignupBinding;


public class loginFrag extends Fragment {



    public loginFrag() {
        // Required empty public constructor
    }

    FragmentLoginBinding binding;
    String combine_phone2;
    FirebaseDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();

        binding.Loginsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number=binding.PhoneNoLogin.getText().toString();
                combine_phone2="+91"+phone_number;
                if(phone_number.isEmpty()||phone_number.length()!=10)
                {   // to make this text view visible.
                    binding.warning2.setText("enter valid mobile number");
                    binding.warning2.setVisibility(View.VISIBLE);

                }
                else
                {


                    DatabaseReference reference= database.getReference().child("User");
                    reference.orderByChild("PhoneNo").equalTo(combine_phone2).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.getValue()!=null)
                            {
                                Toast.makeText(getContext(), combine_phone2, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getContext(), OtpAndName.class);

                                intent.putExtra("MobileNo",combine_phone2);
                                intent.putExtra("boolean","true");
                                startActivity(intent);
                            }

                            else {

                                binding.warning2.setText("User not found. Please Sign up");
                                binding.warning2.setVisibility(View.VISIBLE);
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