package com.shubh.yuwonmedstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Category_recyclerview_adapter extends RecyclerView.Adapter<Category_recyclerview_adapter.MyHolder> {


    ArrayList<Model_category> list;
    ArrayList<Model_category> listKey;
    Context context;

    public Category_recyclerview_adapter(ArrayList<Model_category> list, ArrayList<Model_category> listKey, Context context) {
        this.list = list;
        this.listKey = listKey;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.model_for_category,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


       Model_category adp= list.get(position);
       Model_category adp2= listKey.get(position);

        holder.Name.setText(adp.getName());
        holder.price.setText("₹ "+adp.getPrice());
        holder.MRP.setText("₹ "+adp.getMRP());
        holder.MRP.setPaintFlags(holder.MRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(adp.getImage()).into(holder.image);
        holder.OFF.setText(Integer.toString((int) ((Integer.parseInt(adp.getMRP())-Integer.parseInt(adp.getPrice()))*100/(Integer.parseInt(adp.getMRP()))))+"% off");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(context,Med_detail.class);
                 intent.putExtra("image",adp.getImage());
                 intent.putExtra("name",adp.getName());
                 intent.putExtra("description",adp.getDesciption());
                 intent.putExtra("Amount",adp.getAmount());
                 intent.putExtra("price",adp.getPrice());
                intent.putExtra("uri",adp.getImage());
                intent.putExtra("Key",adp2.getKey());
                intent.putExtra("MRP",adp.getMRP());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder
    {
        TextView Name,price,MRP,OFF,add;
        ImageView image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_recycler);
            Name=itemView.findViewById(R.id.Name_recycler);
            price=itemView.findViewById(R.id.Amount_recycler);
            MRP=itemView.findViewById(R.id.MRP_recycler);
            OFF=itemView.findViewById(R.id.off_recycler);
            add=itemView.findViewById(R.id.Add_to_cart);

        }
    }
}
