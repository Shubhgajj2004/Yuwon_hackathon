package com.shubh.yuwonmedstore.loginFragments;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Loginadapter extends FragmentStateAdapter {


    public Loginadapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new SignupFrag();
        }
        return new loginFrag();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
