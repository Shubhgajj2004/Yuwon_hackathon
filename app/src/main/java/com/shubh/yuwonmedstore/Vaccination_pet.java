package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityPetCareHomeBinding;
import com.shubh.yuwonmedstore.databinding.ActivityVaccinationPetBinding;

import java.util.ArrayList;

public class Vaccination_pet extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    ActivityVaccinationPetBinding binding;

    adapter_Vacc adapter;
    ArrayList<modal_vaccination_pet> list = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityVaccinationPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        adapter = new adapter_Vacc(list , getApplicationContext() );
        binding.textPetVaccinationRes.setAdapter(adapter);
        binding.textPetVaccinationRes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        database.getReference().child("User").child(user.getUid()).child("Pets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    String name =snapshot1.child("Name").getValue(String.class);
                    String type  = snapshot1.child("Type").getValue(String.class);
                    String nameVacc = "DHCP";
                    String DOB = snapshot1.child("DOB").getValue(String.class);

                    if(type.equals("Dog"))
                    {


                        if(findDifference(DOB , dtf.format(now))<=49)
                        {
                            list.add(new modal_vaccination_pet(name , type , "DHLPPC (First Dose)" , 49 ));
                        }
                        else if(findDifference(DOB ,dtf.format(now))<=70)
                        {
                            list.add(new modal_vaccination_pet(name , type , "DHLPPC (Second Dose)" , 70 ));

                        }
                        else if(findDifference(DOB ,dtf.format(now))<=91)
                        {
                            list.add(new modal_vaccination_pet(name , type , "DHLPPC (Third Dose)" , 91 ));

                        }
                        else if(findDifference(DOB ,dtf.format(now))<=117)
                        {
                            list.add(new modal_vaccination_pet(name , type , "DHLPPC (Fourth Dose)" , 117 ));

                        }

                        int booster = 365;

                        for(int i =1; i<5; i++)

                        {
                            if(findDifference(DOB ,dtf.format(now))<=booster)
                            {
                                list.add(new modal_vaccination_pet(name , type , "DHLPPC (Booster Dose)" , booster ));
                                booster+=365;
                            }


                        }


                        if(findDifference(DOB ,dtf.format(now))<=98)
                         {
                            list.add(new modal_vaccination_pet(name , type , "Bordetella (First Dose)" , 98 ));

                         }

                        int booster2 = 210;
                        for(int i =1; i<5; i++)

                        {
                            if(findDifference(DOB ,dtf.format(now))<=booster)
                            {
                                list.add(new modal_vaccination_pet(name , type , "Bordetella (Booster Dose)" , booster2));
                                booster2+=210;
                            }


                        }

                        if(findDifference(DOB ,dtf.format(now))<=112)
                        {
                            list.add(new modal_vaccination_pet(name , type , "Rabies (First Dose)" , 112 ));

                        }

                        int booster3= 1000;

                        for(int i =0; i<=4; i++)
                        {
                            if(findDifference(DOB ,dtf.format(now))<=800)
                            {
                                list.add(new modal_vaccination_pet(name , type , "Rabies (Booster Dose)" , booster3 ));

                                booster3+=1000;

                            }
                        }










                    }












//                    list.add(new modal_vaccination_pet(name , type , nameVacc , DOB ));

                }

//                Collections.sort(list, new Comparator<modal_vaccination_pet>() {
//                    @Override
//                    public int compare(modal_vaccination_pet t1, modal_vaccination_pet t2) {
//                        return t1.Duration;
//                    }
//                });
                Collections.sort(list, new Comparator<modal_vaccination_pet>() {
                    @Override
                    public int compare(modal_vaccination_pet t2, modal_vaccination_pet t1) {
                        if(t1.Duration> t2.Duration)
                        {
                            return  -1;
                        }
                        else {
                            return 1;
                        }
                    }
                });


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    static int findDifference(String start_date,
                   String end_date)
    {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd/MM/yyyy");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();



            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;


                  return  (int) difference_In_Days;


        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}