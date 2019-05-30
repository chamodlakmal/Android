package com.jcl.eatit.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.jcl.eatit.Interface.ItemClickListner;
import com.jcl.eatit.Model.Order;
import com.jcl.eatit.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cart_name,price;
    public ImageView imageView;

    private ItemClickListner itemClickListner;

    public void setCart_name(TextView cart_name) {
        this.cart_name = cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {


        super(itemView);
        cart_name=itemView.findViewById(R.id.cart_item_name);
        price=itemView.findViewById(R.id.cart_item_Price);
        imageView=itemView.findViewById(R.id.cart_item_count);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listData=new ArrayList<>();
    private Context context;


    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.cart_layout,viewGroup,false);
        return new CartViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        TextDrawable drawable=TextDrawable.builder().buildRound(""+listData.get(i).getQuantity(), Color.RED);
        cartViewHolder.imageView.setImageDrawable(drawable);
        Locale locale=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price=(Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getQuantity()));
        cartViewHolder.price.setText(fmt.format(price));
        cartViewHolder.cart_name.setText(listData.get(i).getProductName());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
