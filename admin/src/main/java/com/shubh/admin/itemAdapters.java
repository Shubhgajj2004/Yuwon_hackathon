package com.shubh.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class itemAdapters extends RecyclerView.Adapter<itemAdapters.MyHolder> {

    ArrayList<Items_modal_for_adp> list;
    ArrayList<Items_modal_for_adp> listKey;


    Context context;
        //child thread
    public itemAdapters(ArrayList<Items_modal_for_adp> list,ArrayList<Items_modal_for_adp> listKey, Context context) {

        this.list =list;
        this.listKey =listKey;

        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.modal_items,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

      Items_modal_for_adp adp= list.get(position);
      Items_modal_for_adp adp2= listKey.get(position);

      holder.name.setText(adp.getName());
      holder.amount.setText(adp.getAmount());
      holder.price.setText("₹ "+adp.getPrice());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,detail_Item.class);
              intent.putExtra("image",adp.getImage());
              intent.putExtra("name",adp.getName());
              intent.putExtra("description",adp.getDesciption());
              intent.putExtra("Amount",adp.getAmount());
              intent.putExtra("price",adp.getPrice());
              intent.putExtra("uri",adp.getImage());
              intent.putExtra("Key",adp2.getKey());
              intent.putExtra("MRP",adp.getMRP());
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
        TextView name,amount,price;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sample_name);
            amount=itemView.findViewById(R.id.sample_amount);
            price=itemView.findViewById(R.id.sample_price);
        }
    }
}
