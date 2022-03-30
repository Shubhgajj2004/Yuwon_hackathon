package com.shubh.yuwonmedstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class orders_detail_adapter extends RecyclerView.Adapter<orders_detail_adapter.OrderDetailHolder> {


    ArrayList<Model_Order_details> list;
    Context context;

    public orders_detail_adapter(ArrayList<Model_Order_details> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.model_detail_order,parent,false);
        return new OrderDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailHolder holder, int position) {

        Model_Order_details adp= list.get(position);


        holder.Name.setText(adp.getName());
        holder.Price.setText("₹ "+adp.getPrice());
        holder.MRP.setText("MRP "+adp.getMRP());
        holder.off.setText( (((Integer.parseInt(adp.getMRP())-Integer.parseInt(adp.getPrice()))*100)/Integer.parseInt(adp.getMRP())) +" %");
        holder.Quant.setText(adp.getAmount());
        Glide.with(context).load(adp.getImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class OrderDetailHolder extends RecyclerView.ViewHolder{

        TextView Name,Price,MRP,off,Quant;
        ImageView imageView;
        public OrderDetailHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name_recycler2_order);
            Price=itemView.findViewById(R.id.Amount_recycler2_order);
            MRP=itemView.findViewById(R.id.MRP_recycler2_order);
            off=itemView.findViewById(R.id.off_recycler2_order);
            Quant=itemView.findViewById(R.id.number2_detail_order);
            imageView=itemView.findViewById(R.id.image_recycler2_order);

        }
    }


}
