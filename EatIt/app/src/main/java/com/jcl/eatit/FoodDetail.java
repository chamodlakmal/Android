package com.jcl.eatit;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jcl.eatit.Database.Database;
import com.jcl.eatit.Model.Food;
import com.jcl.eatit.Model.Order;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView food_name,food_price,food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    ElegantNumberButton elegantNumberButton;
    String foodId;
    FirebaseDatabase database;
    DatabaseReference foods;
    Food food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Food");
        floatingActionButton=findViewById(R.id.btn_cart);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                    foodId,food.getName(),elegantNumberButton.getNumber(),food.getPrice(),food.getDiscount()

                ));
                Toast.makeText(FoodDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        food_name=findViewById(R.id.food_name);
        food_image=findViewById(R.id.img_food);
        food_price=findViewById(R.id.food_price);
        food_description=findViewById(R.id.food_description);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandingAppbar);
        elegantNumberButton=findViewById(R.id.number_button);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingAppbar);

        if(getIntent()!=null)
        {
            foodId=getIntent().getStringExtra("foodId");
        }
        if(!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }
    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food=dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(food.getImage()).into(food_image);
                food_name.setText(food.getName());
                food_price.setText(food.getPrice());
                food_description.setText(food.getDescription());
                collapsingToolbarLayout.setTitle(food.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
