package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public abstract class itemDetailsActivity extends AppCompatActivity {

   // private FloatingActionButton addToCart;
    private ImageView itemImage;

    private TextView itemPrice, itemName, value;
    int count = 0;
    private String itemID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemID = getIntent().getStringExtra("fruits");


        //addToCart = (FloatingActionButton) findViewById(R.id.btn_addCart);

        itemImage = (ImageView) findViewById(R.id.item_image_details);
        itemPrice = (TextView) findViewById(R.id.item_price);
        itemName = (TextView) findViewById(R.id.item_name);
        value = (TextView) findViewById(R.id.value);

        getitemDetails(itemID);
    }

    public void increment(View v) {
        count++;
        value.setText("" + count);
    }

    public void decrement(View v) {
        if (count <= 0) count = 0;
        else count--;

        value.setText("" + count);
    }


    private void getitemDetails(String itemID) {
        DatabaseReference itemRef= FirebaseDatabase.getInstance().getReference().child("items");

        itemRef.child(itemID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        fruit item=snapshot.getValue(fruit.class);

                        itemName.setText(item.getFname());
                        itemPrice.setText(item.getFcusprice());
                        Picasso.get().load(item.getFimglink()).into(itemImage);
                    }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}