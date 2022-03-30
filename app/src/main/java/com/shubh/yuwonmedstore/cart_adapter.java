package com.shubh.yuwonmedstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cart_adapter extends RecyclerView.Adapter<cart_adapter.CartHolder> {

    ArrayList<Model_category> list;
    ArrayList<Model_category> listKey;
    Context context;
    FirebaseUser user;
    FirebaseDatabase database;
    FirebaseAuth auth;
    int val;




    public cart_adapter(ArrayList<Model_category> list, ArrayList<Model_category> listKey, Context context) {
        this.list = list;
        this.listKey = listKey;
        this.context = context;
    }



    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.model_cart,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {

        Model_category adp= list.get(position);
        Model_category adp2= listKey.get(position);

        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();


        holder.name.setText(adp.getName());
        holder.price.setText("₹ "+adp.getPrice());
        holder.MRP.setText("MRP "+adp.getMRP());
        holder.MRP.setPaintFlags(holder.MRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(adp.getImage()).into(holder.img);
        holder.off.setText((int) ((Integer.parseInt(adp.getMRP()) - Integer.parseInt(adp.getPrice())) * 100 / (Integer.parseInt(adp.getMRP()))) +"% off");

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user !=null)
                {



                    DatabaseReference ref= database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(adp2.getKey()).child("Amount");
                    ref.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if(snapshot.exists()){
                                String value= snapshot.getValue(String.class);


                                val = Integer.parseInt(value);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                    });
                    if(val!=0) {
                        val = val + 1;
                        ref.setValue(Integer.toString(val));
                    }


                }
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user !=null)
                {



                    DatabaseReference ref= database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(adp2.getKey()).child("Amount");
                    ref.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()) {
                                String value= snapshot.getValue(String.class);

                                val = Integer.parseInt(value);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                    });
                    if(val!=0&&val>0) {
                        val = val - 1;
                        ref.setValue(Integer.toString(val));
                    }


                }
            }
        });



        if(user !=null) {

            database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").child(adp2.getKey()).child("Amount").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {
                        String value = snapshot.getValue(String.class);
                        holder.number.setText(value);
                        holder.MRP.setText("MRP "+Integer.toString(Integer.parseInt(value)*Integer.parseInt(adp.getMRP())));
                        holder.price.setText("₹ "+Integer.toString(Integer.parseInt(value)*Integer.parseInt(adp.getPrice())));


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

        if(user !=null) {


            database.getReference().child("User").child(user.getUid()).child("Orders").child("TempOrder").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if (snapshot1.getKey().equals(adp2.getKey())) {

                            if(snapshot1.child("Amount").getValue(String.class).equals("0")) {

                                DatabaseReference ref1=database.getReference().child("User").child(user.getUid()).child("TempOrder").child(adp2.getKey());
                                ref1.removeValue();

                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });






        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public static class CartHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name,price,MRP,off,number;
        Button plus , minus;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.image_recycler2);
            name=itemView.findViewById(R.id.Name_recycler2);
            price=itemView.findViewById(R.id.Amount_recycler2);
            MRP=itemView.findViewById(R.id.MRP_recycler2);
            off=itemView.findViewById(R.id.off_recycler2);
            number=itemView.findViewById(R.id.number2);
            plus=itemView.findViewById(R.id.plus2);
            minus=itemView.findViewById(R.id.minus2);



        }
    }
}
