package com.shubh.yuwonmedstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pets_adapter_profile extends RecyclerView.Adapter<pets_adapter_profile.PetsHolder>{

    ArrayList<Model_for_pets_profile> list;
    Context context;

    public pets_adapter_profile(ArrayList<Model_for_pets_profile> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PetsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.modal_petsprofile,parent,false);
        return new PetsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetsHolder holder, int position) {

        Model_for_pets_profile adp = list.get(position);

        holder.name.setText(adp.getName());
        holder.sex.setText(adp.getSex());
        holder.type.setText(adp.getType());
        holder.DOB.setText(adp.getDOB());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PetsHolder extends RecyclerView.ViewHolder
    {
        TextView name,type,DOB , sex;
        public PetsHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.pet_name2);
            type=itemView.findViewById(R.id.pet_type2);
            DOB=itemView.findViewById(R.id.pet_DOB2);
            sex = itemView.findViewById(R.id.pet_sex2);
        }
    }
}
