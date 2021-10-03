package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class placeOrder extends AppCompatActivity {

    TextView itemname,address,amount;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        button1 = findViewById(R.id.cod);
        button2 = findViewById(R.id.card);
        itemname = findViewById(R.id.itemname);
        address = findViewById(R.id.address);
        amount = findViewById(R.id.amount);




        final Intent intent = new Intent(this, cash_on_delivery.class);

        String itemName = itemname.getText().toString();
        String Address = address.getText().toString();
        String Amount = amount.getText().toString();

        Bundle extras = new Bundle();

        extras.putString("ITEM_NAME",itemName);
        extras.putString("ADDRESS",Address);
        extras.putString("AMOUNT",Amount);

        intent.putExtras(extras);

        button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(intent);
                }
            });


        button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getApplicationContext(), Card_details.class);
                    startActivity(intent1);
                }
            });

    }

}