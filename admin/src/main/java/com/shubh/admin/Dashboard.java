package com.shubh.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.shubh.admin.Fragments.Order_frag;
import com.shubh.admin.Fragments.Promocode_frag;
import com.shubh.admin.Fragments.userFrag;

import com.shubh.admin.databinding.ActivityDashboardBinding;


public class Dashboard extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.ToolBar);
        toggle=new ActionBarDrawerToggle(this,binding.DrawerLay,binding.ToolBar,R.string.Open_toggle,R.string.close_toggle);
        binding.DrawerLay.addDrawerListener(toggle);
        toggle.syncState();

        Fragment frag=new userFrag();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,frag).commit();


        binding.nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Items:
                       fragment=new userFrag();
                       getSupportActionBar().setTitle("Items");
                        binding.DrawerLay.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Orders:
                        fragment=new Order_frag();
                        getSupportActionBar().setTitle("Orders");
                        binding.DrawerLay.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.PromoCode:
                        fragment=new Promocode_frag();
                        getSupportActionBar().setTitle("Promocodes");
                        binding.DrawerLay.closeDrawer(GravityCompat.START);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,fragment).commit();
                return true;
            }

        });

    }
}