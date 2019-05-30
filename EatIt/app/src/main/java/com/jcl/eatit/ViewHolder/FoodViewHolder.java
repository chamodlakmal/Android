package com.jcl.eatit.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcl.eatit.Interface.ItemClickListner;
import com.jcl.eatit.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_name;
    public ImageView food_image;
    private ItemClickListner itemClickListner;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        food_name=itemView.findViewById(R.id.food_name);
        food_image=itemView.findViewById(R.id.food_Image);
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
