package com.jcl.eatit.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jcl.eatit.Interface.ItemClickListner;
import com.jcl.eatit.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView orderId,orderStatus,orderAddress,phone;
    private ItemClickListner itemClickListner;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        orderAddress=itemView.findViewById(R.id.order_address);
        orderId=itemView.findViewById(R.id.order_id);
        orderStatus=itemView.findViewById(R.id.order_status);
        phone=itemView.findViewById(R.id.order_phone);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }
}
