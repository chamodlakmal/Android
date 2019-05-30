package com.jcl.eatit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcl.eatit.Interface.ItemClickListner;
import com.jcl.eatit.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListner itemClickListner;

    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName=itemView.findViewById(R.id.menu_name);
        imageView=itemView.findViewById(R.id.menu_Image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
