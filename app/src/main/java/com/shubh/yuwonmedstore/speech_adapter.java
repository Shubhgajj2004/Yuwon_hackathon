package com.shubh.yuwonmedstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class speech_adapter extends RecyclerView.Adapter {

   ArrayList<modal_speech> list;
   Context context;

   int SENDER_TYPE = 1;
   int RECIEVER_TYPE = 2;

    public speech_adapter(ArrayList<modal_speech> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new senderHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_recieve,parent,false);
            return new recieverHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        modal_speech adp = list.get(position);

        if(holder.getClass() == senderHolder.class)
        {
            ((senderHolder)holder).senderText.setText(adp.getConversation());
        }
        else
        {
            ((recieverHolder)holder).recieveText.setText(adp.getConversation());
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getType().equals("sender"))
        {
            return SENDER_TYPE;
        }
        else
        {
            return RECIEVER_TYPE;
        }

    }

    public class senderHolder extends RecyclerView.ViewHolder
    {

        TextView senderText;
        public senderHolder(@NonNull View itemView) {
            super(itemView);

            senderText = itemView.findViewById(R.id.sender_res);
        }
    }

    public class recieverHolder extends RecyclerView.ViewHolder
    {

        TextView recieveText;
        public recieverHolder(@NonNull View itemView) {
            super(itemView);

            recieveText = itemView.findViewById(R.id.reciever_res);
        }
    }

}
