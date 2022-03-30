package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.shubh.yuwonmedstore.dashboard_fragments.All_Items_frag;
import com.shubh.yuwonmedstore.dashboard_fragments.Home_frag;
import com.shubh.yuwonmedstore.dashboard_fragments.Order_frag;
import com.shubh.yuwonmedstore.dashboard_fragments.Profile_frag;
import com.shubh.yuwonmedstore.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       binding.navView.setItemIconTintList(null);

        binding.navView.setOnItemSelectedListener(this);

        Fragment frag=new Home_frag();
        getSupportFragmentManager().beginTransaction().replace(R.id.Dashboard_Frame,frag).commit();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId())
        {
            case R.id.Home:
                fragment=new Home_frag();
                break;

            case R.id.All_Items:
                fragment=new All_Items_frag();
                break;

            case R.id.Profile:
                fragment=new Profile_frag();
                break;

            case R.id.Orders:
                fragment=new Order_frag();
                break;




        }


        getSupportFragmentManager().beginTransaction().replace(R.id.Dashboard_Frame,fragment).commit();




        return true;
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder=new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Are you sure you want to exit ?");
        builder.setTitle("Exit");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog= builder.create();
        dialog.show();

    }
}