package com.shubh.yuwonmedstore;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class adapter_Vacc extends RecyclerView.Adapter<adapter_Vacc.VaccHolder> {

    ArrayList<modal_vaccination_pet> list;
    Context context;

    public adapter_Vacc(ArrayList<modal_vaccination_pet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VaccHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_vacc_pet , parent , false);
        return new VaccHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull VaccHolder holder, int position) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        Date date1 = null;

        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        modal_vaccination_pet adp = list.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE , adp.getDuration());

        Date date2 =calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

        String inActiveDate = null;

        inActiveDate = format1.format(date2);



        holder.Name.setText(adp.getName());
        holder.Type.setText(adp.getType());
        holder.TypeVacc.setText(adp.getNameVac());
        holder.NextDate.setText(inActiveDate);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VaccHolder extends RecyclerView.ViewHolder{

        TextView Name , Type , TypeVacc , NextDate;

        public VaccHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.pet_name3);
            Type = itemView.findViewById(R.id.pet_type3);
            TypeVacc = itemView.findViewById(R.id.pet_Vacc_type3);
            NextDate = itemView.findViewById(R.id.pet_Next_date3);
        }
    }

}
