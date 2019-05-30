package com.jcl.eatit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jcl.eatit.Common.Common;
import com.jcl.eatit.Model.Request;
import com.jcl.eatit.ViewHolder.OrderViewHolder;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager manager;
    FirebaseDatabase database;
    DatabaseReference request;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database=FirebaseDatabase.getInstance();
        request=database.getReference("Requests");

        recyclerView=findViewById(R.id.listOrders);

        recyclerView.setHasFixedSize(true);
        manager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

        loadOrders(Common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {
        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                request.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.orderId.setText(adapter.getRef(position).getKey());
                viewHolder.orderAddress.setText(model.getAddress());
                viewHolder.orderStatus.setText(convertCodetToString(model.getStatus()));
                viewHolder.phone.setText(model.getPhone());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private String convertCodetToString(String status) {
        if(status.equals("0"))
        {
            return "Placed";
        }
        else if(status.equals("1"))
        {
            return "On My Way";
        }
        else
            return "Shipped";
    }
}
