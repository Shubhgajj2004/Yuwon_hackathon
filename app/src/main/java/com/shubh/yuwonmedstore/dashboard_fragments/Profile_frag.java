package com.shubh.yuwonmedstore.dashboard_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubh.yuwonmedstore.Profile.Attribution;
import com.shubh.yuwonmedstore.Profile.Need_help;
import com.shubh.yuwonmedstore.Profile.Privacy_policy;
import com.shubh.yuwonmedstore.Profile.Term_and_cond;
import com.shubh.yuwonmedstore.R;
import com.shubh.yuwonmedstore.databinding.FragmentProfileFragBinding;


public class Profile_frag extends Fragment {

    public Profile_frag() {
        // Required empty public constructor
    }


FragmentProfileFragBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileFragBinding.inflate(inflater,container,false);

        binding.PrivacyPo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Privacy_policy.class));
            }
        });
        binding.NeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Need_help.class));
            }
        });

        binding.TermsAndCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Term_and_cond.class));


            }
        });
        binding.Attribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Attribution.class));


            }



        });

        return binding.getRoot();

    }
}