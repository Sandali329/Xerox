package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xerox.LoginSuppliers.LoginSuppliers;
import com.example.xerox.classes.RegSuppliers;
import com.example.xerox.classes.SupOrder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SupplierActivity3 extends AppCompatActivity {

    private ImageView Image;
    private TextView Fname, Fprice;
    private EditText Qty;
    private Button addCart;
    private int count = 0;
    private String fruitId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier3);

        fruitId = getIntent().getStringExtra("fcode");

        Image = (ImageView) findViewById(R.id.p_image);
        Fname = (TextView) findViewById(R.id.I_name);
        Fprice = (TextView) findViewById(R.id.I_price);
        Qty = (EditText) findViewById(R.id.et_qty);
        addCart = (Button) findViewById(R.id.Cart_btn);

        getProductDetails(fruitId);

        //add to cart button
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToSupCartList();
            }
        });

    }

    //display selected product details
    private void getProductDetails(String fruitId) {
        DatabaseReference fruitRef = FirebaseDatabase.getInstance().getReference().child("Fruits");
        fruitRef.child(fruitId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    fruit fruit =  snapshot.getValue(fruit.class);

                    Picasso.get().load(fruit.getFimglink()).into(Image);
                    Fname.setText(fruit.getFname());
                    Fprice.setText(fruit.getFprice());

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    private void addingToSupCartList() {
        String savecurrentDate, savecurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM. dd. yyyy");
        savecurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Supplier Cart");
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("fcode",fruitId);
        cartMap.put("fname",Fname.getText().toString().trim());
        cartMap.put("fprice",Fprice.getText().toString().trim());
        cartMap.put("quantity",Qty.getText().toString().trim());
        cartMap.put("date",savecurrentDate);
        cartMap.put("time",savecurrentTime);

        cartListRef.child("Current Supplier").child("Fruits").child(fruitId)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(SupplierActivity3.this, "Item added to Cart List..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SupplierActivity3.this, SupplierActivity1.class);
                    startActivity(intent);

                }
            }
        });
    }


    //Quantity increment
    public void incrementQty(View v){
        count++;
        Qty.setText("" +count);
    }

    //Quantity decrement
    public void decrementQty(View v){
        if(count<=0){
            count = 0;
        }else {
            count--;
        }
        Qty.setText("" +count);
    }


}
