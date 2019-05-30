package com.jcl.androideatitserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcl.androideatitserver.Common.Common;
import com.jcl.androideatitserver.Interface.ItemClickListner;
import com.jcl.androideatitserver.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
View.OnCreateContextMenuListener{

    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListner itemClickListner;

    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName=itemView.findViewById(R.id.menu_name);
        imageView=itemView.findViewById(R.id.menu_Image);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);

    }
}
