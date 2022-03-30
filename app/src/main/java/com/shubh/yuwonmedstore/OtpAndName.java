package com.shubh.yuwonmedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.yuwonmedstore.loginFragments.WelcomeNameFrag;

import java.util.concurrent.TimeUnit;

public class OtpAndName extends AppCompatActivity {
    String combine_phone2;
    FirebaseAuth auth;
    String otpID;
    FloatingActionButton otpbtn;
    EditText otp;
    RelativeLayout layout;
    FirebaseDatabase database;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_and_name);

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        auth=FirebaseAuth.getInstance();
        combine_phone2= getIntent().getStringExtra("MobileNo");
        otpbtn=findViewById(R.id.Otpsubmitbtn);
        otp=findViewById(R.id.OtpId2);
        layout=findViewById(R.id.Otp_name);
        intiateOTP();

     otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.getText().toString().isEmpty())
                {
                    Toast.makeText(OtpAndName.this, "enter valid otp", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpID,otp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });


    }

    public void intiateOTP()
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(combine_phone2)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(OtpAndName.this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpID=s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OtpAndName.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                           try {
                               if (getIntent().getStringExtra("boolean").contains("true")) {
                                   startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                   finish();
                               }
                           }


                           catch (Exception e){


                               user=auth.getCurrentUser();
                               if(user!=null) {

                                   DatabaseReference reference = database.getReference().child("User").child(user.getUid());

                                   reference.child("PhoneNo").setValue(combine_phone2);
                               }

                                layout.setVisibility(View.VISIBLE);
                                otpbtn.setVisibility(View.GONE);
                                Fragment fragment = new WelcomeNameFrag();
                                getSupportFragmentManager().beginTransaction().replace(R.id.Otp_name, fragment).commit();
                            }
                        } else {
                            Toast.makeText(OtpAndName.this, "error in otp", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}