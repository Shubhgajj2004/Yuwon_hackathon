package com.shubh.yuwonmedstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orders_adapter extends RecyclerView.Adapter<orders_adapter.OrdersHolder>{

    ArrayList<Model_Orders> list;
    ArrayList<Model_Orders> listId;

    Context context;

    public orders_adapter(ArrayList<Model_Orders> list ,ArrayList<Model_Orders> listId, Context context) {
        this.list = list;
        this.listId=listId;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.model_orders,parent,false);
        return new OrdersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {

       Model_Orders adp=list.get(position);
       Model_Orders adp2=listId.get(position);

      holder.status.setText(adp.getStatus());
      holder.PayableAmount.setText("₹ "+adp.getPayableAmount());
      holder.id.setText("Order id: "+adp2.getId());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,Detail_Orders.class);
              intent.putExtra("Id",adp2.getId());
              intent.putExtra("Status",adp.getStatus());
              intent.putExtra("PayableAmount",adp.getPayableAmount());
              intent.putExtra("Time",adp.getTime());
              intent.putExtra("UsedPromo",adp.getUsedPromo());
              intent.putExtra("WithoutPromo",adp.getWithoutPromo());
              context.startActivity(intent);


              Intent intent2=new Intent(context,orders_detail_adapter.class);
              intent.putExtra("PayableAmount",adp.getPayableAmount());
              intent.putExtra("WithoutPromo",adp.getWithoutPromo());

          }
      });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrdersHolder extends RecyclerView.ViewHolder{

        TextView id,PayableAmount,status;
        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.order_frag_id);
            PayableAmount=itemView.findViewById(R.id.order_frag_payable);
            status=itemView.findViewById(R.id.Order_frag_status);



        }
    }


}
