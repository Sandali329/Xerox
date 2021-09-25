package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cash_on_delivery extends AppCompatActivity {

    TextView item1,address1,amount1;
    Button confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery);

        item1 = findViewById(R.id.item);
        address1 = findViewById(R.id.address);
        amount1 = findViewById(R.id.amount);
        confirm = findViewById(R.id.confirm);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String item = extras.getString("ITEM_NAME");
        String address = extras.getString("ADDRESS");
        String amount = extras.getString("AMOUNT");

        item1.setText(item);
        address1.setText(address);
        amount1.setText(amount);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), order_successfull.class);
                startActivity(intent1);
            }
        });

     }
}