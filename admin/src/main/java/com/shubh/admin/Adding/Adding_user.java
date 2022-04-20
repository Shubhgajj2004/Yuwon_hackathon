package com.shubh.admin.Adding;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shubh.admin.Dashboard;
import com.shubh.admin.R;

import java.util.HashMap;
import java.util.Map;

public class Adding_user extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressBar progressBar;
    ImageView imageView;
    TextView checker;
    EditText item_name, item_des, item_amount, item_price, item_price2;
    Button Addimage, submit_item;
    ActivityResultLauncher<String> launcher;
    Uri result1;
    CheckBox device, beauty, covid, generics, fitness, ointment, dabur, himalaya, vicks, nivea, w_looser, w_gainer , men , women , pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.item_image);
        Addimage = findViewById(R.id.Add_image_btn);
        submit_item = findViewById(R.id.submitItembtn);
        item_name = findViewById(R.id.Item_name);
        item_price = findViewById(R.id.Item_price);
        item_price2 = findViewById(R.id.Item_price2);
        item_des = findViewById(R.id.Item_des);
        item_amount = findViewById(R.id.Item_amount);
        checker = findViewById(R.id.process_check);
        device = findViewById(R.id.device);
        beauty = findViewById(R.id.beauty);
        covid = findViewById(R.id.covid);
        generics = findViewById(R.id.genrics);
        fitness = findViewById(R.id.fitness);
        ointment = findViewById(R.id.ointment);
        dabur = findViewById(R.id.dabur);
        himalaya = findViewById(R.id.himalaya);
        vicks = findViewById(R.id.mama);
        nivea = findViewById(R.id.nivea);
        w_gainer = findViewById(R.id.weight_gainer);
        w_looser = findViewById(R.id.weight_looser);
        men = findViewById(R.id.men);
        women = findViewById(R.id.women);
        pet = findViewById(R.id.pet);


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageView.setImageURI(result);
                result1 = result;
            }
        });

        Addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");  //to add only images.
            }
        });
        submit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                final StorageReference ref = storage.getReference().child(item_name.getText().toString());
                ref.putFile(result1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference databaseReference = database.getReference().child("Items").push();
                                databaseReference.child("Image").setValue(uri.toString());
                                databaseReference.child("Name").setValue(item_name.getText().toString());
                                databaseReference.child("Desciption").setValue(item_des.getText().toString());
                                databaseReference.child("Amount").setValue(item_amount.getText().toString());
                                databaseReference.child("MRP").setValue(item_price.getText().toString());
                                databaseReference.child("Price").setValue(item_price2.getText().toString());

                                databaseReference.updateChildren(listChecker());

                                databaseReference.child("Search").setValue(item_name.getText().toString().toLowerCase());

                            }
                        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressBar.setVisibility(View.GONE);
                                checker.setText("Submitted");
                                checker.setVisibility(View.VISIBLE);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                            }
                        });
                    }
                });
            }
        });


    }

    public Map<String, Object> listChecker() {
        Map<String, Object> arrayList = new HashMap<>();

        if (device.isChecked()) {
            arrayList.put("Device", "true");
        } else {
            arrayList.put("Device", "false");

        }

        if (beauty.isChecked()) {
            arrayList.put("Beauty", "true");
        } else {
            arrayList.put("Beauty", "false");

        }

        if (covid.isChecked()) {
            arrayList.put("Covid", "true");
        } else {
            arrayList.put("Covid", "false");

        }

        if (generics.isChecked()) {
            arrayList.put("Generics", "true");
        } else {
            arrayList.put("Generics", "false");

        }

        if (fitness.isChecked()) {
            arrayList.put("Fitness", "true");
        } else {
            arrayList.put("Fitness", "false");

        }

        if (ointment.isChecked()) {
            arrayList.put("Ointment", "true");
        } else {
            arrayList.put("Ointment", "false");

        }

        if (dabur.isChecked()) {
            arrayList.put("Dabur", "true");
        } else {
            arrayList.put("Dabur", "false");

        }

        if (himalaya.isChecked()) {
            arrayList.put("Himalaya", "true");
        } else {
            arrayList.put("Himalaya", "false");

        }

        if (vicks.isChecked()) {
            arrayList.put("Vicks", "true");
        } else {
            arrayList.put("Mama-earth", "false");

        }

        if (nivea.isChecked()) {
            arrayList.put("Nivea", "true");
        } else {
            arrayList.put("Nivea", "false");

        }
        if (w_looser.isChecked()) {
            arrayList.put("WLooser", "true");
        } else {
            arrayList.put("WLooser", "false");

        }
        if (w_gainer.isChecked()) {
            arrayList.put("WGainer", "true");
        } else {
            arrayList.put("WGainer", "false");

        }
        if (men.isChecked()) {
            arrayList.put("Male", "true");
        } else {
            arrayList.put("Male", "false");

        }
        if (women.isChecked()) {
            arrayList.put("Female", "true");
        } else {
            arrayList.put("Female", "false");

        }
        if (pet.isChecked()) {
            arrayList.put("Pet", "true");
        } else {
            arrayList.put("Pet", "false");

        }

        return arrayList;


    }

}

