package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xerox.classes.SupOrder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SupplierActivity3 extends AppCompatActivity {

    //private ImageView productImage;
    private TextView Fname, Fprice;
    private EditText Qty;
    private Button select;
    FirebaseDatabase DB;
    private fruit fruit;
    DatabaseReference fruitRef;
    private SupOrder Order;
    DatabaseReference orderRef;
    private int no;
    private int count = 0;


    private  void clearControls(){
        Fname.setText("prodName");
        Fprice.setText("price");
        Qty.setText("quantity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier3);

        DB = FirebaseDatabase.getInstance();
        fruitRef = DB.getReference("Fruits");
        fruit = getIntent().getParcelableExtra("fruits");

        ImageView Image = findViewById(R.id.p_image);
        Fname = findViewById(R.id.I_name);
        Fprice = findViewById(R.id.I_price);
        Qty = findViewById(R.id.et_qty);
        select = findViewById(R.id.Cart_btn);


        if (fruit != null) {
            Picasso.get().load(fruit.getFimglink()).into(Image);
            Fname.setText(fruit.getFname());
            Fprice.setText(fruit.getFprice() +"/ 250g");
        }

                addToCart();

    }




    private void addToCart() {

        String savecurrentDate, savecurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        savecurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calForDate.getTime());

        Order = new SupOrder();
        orderRef = FirebaseDatabase.getInstance().getReference().child("SupOrder");

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.setFname(Fname.getText().toString().trim());
                Order.setFprice(Fprice.getText().toString().trim());
                Order.setQty(Qty.getText().toString().trim());
                Order.setDate(savecurrentDate);
                Order.setTime(savecurrentTime);

                if(Qty!=null){

                    orderRef.push().setValue(Order);

                    Intent intent1 = new Intent(SupplierActivity3.this, SupplierActivity1.class);
                    Toast.makeText(SupplierActivity3.this, "Item added to Cart List..", Toast.LENGTH_SHORT).show();
                    clearControls();
                    startActivity(intent1);
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
