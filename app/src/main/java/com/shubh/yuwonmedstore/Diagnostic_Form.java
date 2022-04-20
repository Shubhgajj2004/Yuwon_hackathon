package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityDiagnosticFormBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Diagnostic_Form extends AppCompatActivity {

    ActivityDiagnosticFormBinding binding;

    RadioButton checkedbutton;
    double BMI;
    double Hieght , weight;
    String Cat;

    ArrayList<Model_category> list= new ArrayList<>();
    ArrayList<Model_category> listKey=new ArrayList<>();
    FirebaseDatabase database;
    Category_recyclerview_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDiagnosticFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());








        database=FirebaseDatabase.getInstance();

        binding.DiagnostDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.fakeTipsDial.setVisibility(View.VISIBLE);
                binding.lineDiag2.setVisibility(View.VISIBLE);
                binding.tipsRecycler.setVisibility(View.VISIBLE);
                binding.TipsLayout.setVisibility(View.VISIBLE);

                int ButtonsID = binding.RadioGrpDiag.getCheckedRadioButtonId();
                checkedbutton = (RadioButton) findViewById(ButtonsID);


                Hieght = Double.parseDouble(binding.hieghtDiag.getText().toString()) /100;
                weight = Double.parseDouble(binding.weightDiag.getText().toString());

                BMI = (double) weight /( Hieght *Hieght);


                if(BMI <18.5 && BMI >= 15)
                {
                    Cat = "WGainer";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD You are underweight \uD83D\uDE11 . \n\uD83D\uDC49\uD83C\uDFFD Don't worry , start following given below instructions. \n \n \t \t \t \t 1.)   Choose higher calorie foods \n \t \t \t \t 2.)  Drink lots of fluids \n \t \t \t \t 3.)  Say yes to carbs \n\n\uD83D\uDC49\uD83C\uDFFD Hey , We've fetched some things you can start using 😀");
                }

                else if(BMI<15)
                {
                    Cat = "WGainer";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD You are very severely underweight \uD83D\uDE31 . \n\uD83D\uDC49\uD83C\uDFFD It's totally not okey start consuming given below items.");
                }
                else if(BMI>=18.5 && BMI<25) {
                    Cat ="WGainer";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD You weigh perfect \uD83D\uDE09\uD83D\uDC4D . \n\uD83D\uDC49\uD83C\uDFFD That's why you don't need any tips ,But still you can boost your height and make amazing body 😀.");
                }

                else if(BMI >=25 && BMI <30)
                {
                    Cat ="WLooser";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD Ummm ,May be your weight is somehow more. \n\uD83D\uDC49\uD83C\uDFFD You don't need more , just start \n \n \t \t \t \t 1.)  Eat sugar free items \n \t \t \t \t 2.)  Drink Green Tea \n \t \t \t \t 3.)  start weight lifting exercise ");
                }

                else if(BMI >=30 && BMI <40)
                {
                    Cat ="WLooser";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD You are severely obese \uD83D\uDE11 . \n\uD83D\uDC49\uD83C\uDFFD Don't worry , we know you can make perfect body by following given instructions. \n \n \t \t \t \t 1.)  Take a walk everyday! \n \t \t \t \t 2.)  Eat sugar free items \n \t \t \t \t 3.)  Drink Green Tea \n \t \t \t \t 4.)  Start weight lifting exercise. \n \t \t \t \t 5.)  Wear sweat slimming belt. \n \t \t \t \t 6.)  Make control over what you eat.\n\n\uD83D\uDC49\uD83C\uDFFD Hey ,see what i found for you 😀 ");
                }
                else if(BMI >=40)
                {
                    Cat ="WLooser";
                    binding.tipsDial.setText("\uD83D\uDC49\uD83C\uDFFD You are very severely obese \uD83D\uDE31 . \n\uD83D\uDC49\uD83C\uDFFD You should contact your doctor. \n\uD83D\uDC49\uD83C\uDFFD You must start using given items.");
                }

                binding.tipsDial.setVisibility(View.VISIBLE);

                adapter= new Category_recyclerview_adapter(list,listKey,getApplicationContext());
                binding.tipsRecycler.setAdapter(adapter);
                binding.tipsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                database.getReference().child("Items").orderByChild(Cat).equalTo("true").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();

                        for(DataSnapshot snapshot1 : snapshot.getChildren())
                        {


                            if(snapshot1.child(checkedbutton.getText().toString()).getValue(String.class).equals("true"))
                            {
                                Model_category adp = snapshot1.getValue(Model_category.class);
                                list.add(adp);
                            }

                        }

                        listKey.clear();
                        for(DataSnapshot snapshot2 : snapshot.getChildren())
                        {
                            Model_category adp= new Model_category(snapshot2.getKey());
                            listKey.add(adp);
                        }
                        long seed = System.nanoTime();
                        Collections.shuffle(list, new Random(seed));
                        Collections.shuffle(listKey, new Random(seed));

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




        getSupportActionBar().hide();


    }
}