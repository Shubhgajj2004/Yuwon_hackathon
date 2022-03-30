package com.shubh.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubh.admin.Fragments.promo_modal_for_adp;

import java.util.ArrayList;

public class Orders_adapater extends  RecyclerView.Adapter<Orders_adapater.OrderHolder>{

    ArrayList<Model_orders_for_adp> list;
    ArrayList<Model_orders_for_adp> listKey;
    Context context;

    public Orders_adapater(ArrayList<Model_orders_for_adp> list,ArrayList<Model_orders_for_adp> listKey, Context context) {
        this.list = list;
        this.listKey=listKey;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.model_orders,parent,false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {

        Model_orders_for_adp adp= list.get(position);
        Model_orders_for_adp adp2= listKey.get(position);
        holder.amount.setText("₹ "+adp.getPayableAmount());
        holder.Id.setText("Order id: "+adp2.getId());

        holder.status.setText(adp.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Money_fromUser.class);
                intent.putExtra("PayableAmount",adp.getPayableAmount());
                intent.putExtra("Status",adp.getStatus());
                intent.putExtra("Time",adp.getTime());
                intent.putExtra("UsedPromo",adp.getUsedPromo());
                intent.putExtra("WithoutPromo",adp.getWithoutPromo());
                intent.putExtra("Address",adp.getAddress());
                intent.putExtra("City",adp.getCity());
                intent.putExtra("ContactNo",adp.getContactNo());
                intent.putExtra("Name",adp.getName());
                intent.putExtra("Id",adp2.getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class OrderHolder extends RecyclerView.ViewHolder{

        TextView status,amount,Id;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            status=itemView.findViewById(R.id.Order_frag_status);
            amount=itemView.findViewById(R.id.order_frag_payable);
            Id=itemView.findViewById(R.id.order_frag_id);
        }
    }

}
