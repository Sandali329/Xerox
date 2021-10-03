package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.text.SimpleDateFormat;

public class itemDetailsActivity extends AppCompatActivity {

    private FloatingActionButton addToCart;
    private ImageView itemImage;
    DatabaseReference fruitref,cartListref;
    FirebaseDatabase FB;
    private fruit fruit;
    private CusOrder order;

    private TextView itemPrice,itemName,value;
    int count=0;
    private String itemID="";

    private  void clearControls(){
        itemName.setText("");
        itemPrice.setText("");
        value.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        FB = FirebaseDatabase.getInstance();
        fruitref = FB.getReference("Fruits");
        fruit = getIntent().getParcelableExtra("fruits");


        addToCart = (FloatingActionButton) findViewById(R.id.btn_addCart);
        itemImage=(ImageView) findViewById(R.id.item_image_details);
        itemPrice=(TextView) findViewById(R.id.item_price);
        itemName=(TextView) findViewById(R.id.item_name);
        value=(TextView) findViewById(R.id.value);

        if (fruit != null) {
            Picasso.get().load(fruit.getFimglink()).into(itemImage);
            itemName.setText(fruit.getFname());
            itemPrice.setText(fruit.getFcusprice());
        }
       addingTOCartList();

    }

    private void addingTOCartList() {

        String saveCurTime,saveCurDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurTime = currentTime.format(calForDate.getTime());

        order=new CusOrder();
        cartListref=FirebaseDatabase.getInstance().getReference().child("CustomerOrder");

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setItemName(itemName.getText().toString().trim());
                order.setCusprice(itemPrice.getText().toString().trim());
                order.setCusqty(value.getText().toString().trim());
                order.getCusdate(saveCurDate);
                order.getCustime(saveCurTime);

                if(value!=null){
                    cartListref.push().setValue(order);

                    Intent intent1 = new Intent(itemDetailsActivity.this, cusselect.class);
                    Toast.makeText(itemDetailsActivity.this, "Item added to Cart List..", Toast.LENGTH_SHORT).show();
                    clearControls();
                    startActivity(intent1);
                }


            }
        });

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