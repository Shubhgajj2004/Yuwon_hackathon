package com.shubh.yuwonmedstore.loginFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.yuwonmedstore.Dashboard;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.FragmentLoginBinding;
import com.shubh.yuwonmedstore.databinding.FragmentWelcomeNameBinding;


public class WelcomeNameFrag extends Fragment {



    public WelcomeNameFrag() {
        // Required empty public constructor
    }

    FragmentWelcomeNameBinding binding;
    FirebaseDatabase database;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentWelcomeNameBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();


        binding.Namesubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.WelcomeName.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Enter your name", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(user!=null) {

                        DatabaseReference reference = database.getReference().child("User").child(user.getUid());

                        reference.child("Name").setValue(binding.WelcomeName.getText().toString());
                        startActivity(new Intent(getContext(), Dashboard.class));
                    }
                }

            }
        });



        return binding.getRoot();
    }
}