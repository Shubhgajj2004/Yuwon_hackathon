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

public class promoAdapters extends RecyclerView.Adapter<promoAdapters.PromoHolder>{

    ArrayList<promo_modal_for_adp> list;
    ArrayList<promo_modal_for_adp> listKey;

    Context context;
    //child thread
    public promoAdapters(ArrayList<promo_modal_for_adp> list,ArrayList<promo_modal_for_adp> listKey, Context context) {

        this.list =list;
        this.listKey =listKey;

        this.context = context;
    }

    @NonNull
    @Override
    public PromoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.modal_promo,parent,false);
        return new PromoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoHolder holder, int position) {



        promo_modal_for_adp adp= list.get(position);
        promo_modal_for_adp adp2= listKey.get(position);

        holder.promo.setText(adp.getPromo());
        holder.percent.setText(adp.getPercentDiscount()+" %");
        holder.price.setText("₹ "+adp.getMinAmount());
        holder.type.setText(adp.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,detail_promo.class);
                intent.putExtra("Promo",adp.getPromo());
                intent.putExtra("minAmount",adp.getMinAmount());
                intent.putExtra("percent",adp.getPercentDiscount());
                intent.putExtra("Key",adp2.getKey());
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PromoHolder extends RecyclerView.ViewHolder{

        TextView promo,percent,price,type;
        public PromoHolder(@NonNull View itemView) {
            super(itemView);
            promo=itemView.findViewById(R.id.sample_promo);
            percent=itemView.findViewById(R.id.sample_percentage);
            price=itemView.findViewById(R.id.sample_min_amount);
            type=itemView.findViewById(R.id.sample_promo_type);

        }
    }
}
