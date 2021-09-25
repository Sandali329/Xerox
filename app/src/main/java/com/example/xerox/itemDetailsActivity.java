package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class itemDetailsActivity extends AppCompatActivity {

    private FloatingActionButton addToCart;
    private ImageView itemImage;

    private TextView itemPrice,itemName,value;
    int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        addToCart = (FloatingActionButton) findViewById(R.id.btn_addCart);

        itemImage=(ImageView) findViewById(R.id.item_image_details);
        itemPrice=(TextView) findViewById(R.id.item_price);
        itemName=(TextView) findViewById(R.id.item_name);
        value=(TextView) findViewById(R.id.value);


    }
    public void increment(View v){
      count++;
      value.setText(""+count);
    }
    public void decrement(View v){
       if(count <= 0) count=0;
          else count--;

        value.setText(""+count);
    }

}