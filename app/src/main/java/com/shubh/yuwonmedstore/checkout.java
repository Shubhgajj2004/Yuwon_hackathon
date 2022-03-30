package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.yuwonmedstore.databinding.ActivityCheckoutBinding;
import com.shubh.yuwonmedstore.databinding.ActivityMedDetailBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class checkout extends AppCompatActivity {
    ArrayList<String> publicpromo =new ArrayList<>();
    String [] cities= {"Kalol" , "Kadi" , "Ahmedabad"};

    String selectedcity;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    ActivityCheckoutBinding binding;
    int netPayable;
    int benifiet;
    boolean promoapllied;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        getSupportActionBar().hide();

        ArrayAdapter<String> adapterCity;
        adapterCity=new ArrayAdapter<>(getApplicationContext(),R.layout.array_for_city,cities);
        binding.cityCheckout.setAdapter(adapterCity);




      binding.cityCheckout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              selectedcity=parent.getItemAtPosition(position).toString();
              //Toast.makeText(getApplicationContext(), selectedcity, Toast.LENGTH_SHORT).show();
          }
      });


      binding.TotalPay.setText("₹ "+getIntent().getStringExtra("TotalPay"));



      /////test
        database.getReference().child("PromoCodes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                publicpromo.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    String type =snapshot1.child("Type").getValue(String.class);

                    if(!type.equals("Private")) {
                        String val = snapshot1.child("Promo").getValue(String.class);
                        publicpromo.add(val);
                    }
                }
                if(!publicpromo.isEmpty()) {

                    String [] promo=new String[publicpromo.size()];

                    promo=publicpromo.toArray(promo);


                    ArrayAdapter<String> adapterpromo;
                    adapterpromo=new ArrayAdapter<>(getApplicationContext(),R.layout.array_for_city,promo);
                    binding.promoCheckout.setAdapter(adapterpromo);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


/////////






        binding.placeOrder.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(binding.nameCheckout.getEditText().getText().toString().isEmpty())
              {
                  binding.nameCheckout.setError("Empty");
              }
              else if(binding.contactCheckout.getEditText().getText().toString().isEmpty())
              {
                  binding.nameCheckout.setError(null);
                  binding.contactCheckout.setError("Empty");
              }
              else if(binding.addressCheckout.getEditText().getText().toString().isEmpty())
              {
                  binding.contactCheckout.setError(null);
                  binding.addressCheckout.setError("Empty");
              }
              else {

                  binding.addressCheckout.setError(null);
                  database.getReference().child("PromoCodes").orderByChild("Promo").equalTo(binding.enterPromo.getEditText().getText().toString()).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if (snapshot.exists()) {
                              // Toast.makeText(getApplicationContext(), "Applied Succesfully", Toast.LENGTH_SHORT).show();
                              for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                                  if (snapshot1.child("Promo").getValue(String.class).equals(binding.enterPromo.getEditText().getText().toString())) {



                                            ///////

                                      database.getReference().child("User").child(user.getUid()).child("Orders").child("SuccesfullOrders").addValueEventListener(new ValueEventListener() {
                                          @Override
                                          public void onDataChange(@NonNull DataSnapshot snapshot) {


                                              if(snapshot.exists())
                                              {
                                                  binding.enterPromo.setError("Only valid for new user");
                                              }
                                              else

                                              {

                                                  if (Integer.parseInt(getIntent().getStringExtra("TotalPay")) >= Integer.parseInt(snapshot1.child("MinAmount").getValue(String.class)))
                                                  {
                                                      netPayable = (Integer.parseInt(getIntent().getStringExtra("TotalPay")) * (100 - Integer.parseInt(snapshot1.child("PercentDiscount").getValue(String.class)))) / 100;

                                                      benifiet = Integer.parseInt(getIntent().getStringExtra("TotalPay")) - netPayable;

                                                      binding.TotalPay.setText("₹ " + Integer.toString(netPayable));
                                                      binding.afterPromo.setText("Hurray! You saved total ₹ " + Integer.toString(benifiet) + " using promocode");
                                                      binding.afterPromo.setVisibility(View.VISIBLE);
                                                      promoapllied = true;
                                                      binding.enterPromo.setError(null);
                                                  }
                                                  else {

                                                      binding.enterPromo.setError("Minimum amount must be ₹ " + snapshot1.child("MinAmount").getValue(String.class));
                                                  }


                                              }






                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError error) {

                                          }
                                      });

















                                  }

                              }

                          } else {
                              binding.enterPromo.setError("invalid");
                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }

             if(promoapllied==true)
             {
                 Date currentime=Calendar.getInstance().getTime();
                 startActivity(new Intent(getApplicationContext(),succesfully_checkout_splash.class));

                 database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {

                         DatabaseReference refu=database.getReference().child("User").child(user.getUid()).child("Orders").child("SuccesfullOrders").push();
                         refu.child("OrderItems").setValue(snapshot.getValue());

                         refu.child("Details").child("PayableAmount").setValue(Integer.toString(netPayable));
                         refu.child("Details").child("WithoutPromo").setValue(getIntent().getStringExtra("TotalPay"));
                         refu.child("Details").child("Time").setValue(currentime.toString());
                         refu.child("Details").child("UsedPromo").setValue(binding.enterPromo.getEditText().getText().toString());
                         refu.child("Details").child("Status").setValue("Pending").addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {

                                 DatabaseReference ref=database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder");
                                 ref.removeValue();
                             }
                         }).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {

                                //changes made in 1dt line
                                 DatabaseReference ref2= database.getReference().child("AllUsersOrders").child(refu.getKey());
                                 ref2.child("OrderItems").setValue(snapshot.getValue());



                                 ref2.child("Details").child("Name").setValue(binding.nameCheckout.getEditText().getText().toString());
                                 ref2.child("Details").child("ContactNo").setValue(binding.contactCheckout.getEditText().getText().toString());
                                 ref2.child("Details").child("City").setValue(selectedcity);
                                 ref2.child("Details").child("Address").setValue(binding.addressCheckout.getEditText().getText().toString());
                                 ref2.child("Details").child("PayableAmount").setValue(Integer.toString(netPayable));
                                 ref2.child("Details").child("WithoutPromo").setValue(getIntent().getStringExtra("TotalPay"));

                                 ref2.child("Details").child("UsedPromo").setValue(binding.enterPromo.getEditText().getText().toString());
                                 ref2.child("Details").child("Status").setValue("Pending");
                                 ref2.child("Details").child("UID").setValue(user.getUid());
                                 ref2.child("Details").child("Time").setValue(currentime.toString());






                             }
                         });
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });



                 //




                 Toast.makeText(getApplicationContext(), "Your order has been placed", Toast.LENGTH_SHORT).show();
             }
             else if(binding.enterPromo.getEditText().getText().toString().isEmpty())
             {

                 if(binding.nameCheckout.getEditText().getText().toString().isEmpty())
                 {
                     binding.nameCheckout.setError("Empty");
                 }
                 else if(binding.contactCheckout.getEditText().getText().toString().isEmpty())
                 {
                     binding.nameCheckout.setError(null);
                     binding.contactCheckout.setError("Empty");
                 }
                 else if(binding.addressCheckout.getEditText().getText().toString().isEmpty())
                 {
                     binding.contactCheckout.setError(null);
                     binding.addressCheckout.setError("Empty");
                 }
                 else {
                     Date currentime=Calendar.getInstance().getTime();
                     database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {


                             DatabaseReference refu=database.getReference().child("User").child(user.getUid()).child("Orders").child("SuccesfullOrders").push();
                             refu.child("OrderItems").setValue(snapshot.getValue());

                             refu.child("Details").child("PayableAmount").setValue(getIntent().getStringExtra("TotalPay"));
                             refu.child("Details").child("WithoutPromo").setValue(getIntent().getStringExtra("TotalPay"));
                             refu.child("Details").child("Time").setValue(currentime.toString());
                             refu.child("Details").child("UsedPromo").setValue("null");
                             refu.child("Details").child("Status").setValue("Pending").addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void unused) {
                                     DatabaseReference ref=database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder");
                                     ref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                         @Override
                                         public void onSuccess(Void unused) {

                                             DatabaseReference ref2= database.getReference().child("AllUsersOrders").child(refu.getKey());
                                             ref2.child("OrderItems").setValue(snapshot.getValue());




                                             ref2.child("Details").child("Name").setValue(binding.nameCheckout.getEditText().getText().toString());
                                             ref2.child("Details").child("ContactNo").setValue(binding.contactCheckout.getEditText().getText().toString());
                                             ref2.child("Details").child("City").setValue(selectedcity);
                                             ref2.child("Details").child("Address").setValue(binding.addressCheckout.getEditText().getText().toString());
                                             ref2.child("Details").child("PayableAmount").setValue(getIntent().getStringExtra("TotalPay"));
                                             ref2.child("Details").child("WithoutPromo").setValue(getIntent().getStringExtra("TotalPay"));

                                             ref2.child("Details").child("UsedPromo").setValue("null");
                                             ref2.child("Details").child("Status").setValue("Pending");
                                             ref2.child("Details").child("UID").setValue(user.getUid());
                                             ref2.child("Details").child("Time").setValue(currentime.toString());

                                         }
                                     });
                                 }
                             });
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });

                     startActivity(new Intent(getApplicationContext(),succesfully_checkout_splash.class));
                     Toast.makeText(getApplicationContext(), "Your order has been placed", Toast.LENGTH_LONG).show();
                 }

             }




          }


      });





    }
}