package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {

    Button fruit,veg,addfruit,addriders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        addriders = findViewById(R.id.adminRiders);


addfruit=findViewById(R.id.adminaddfruit);
        fruit=findViewById(R.id.adminfruit);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Adminpage.this, ADMINVIEWFRUITS.class);
                startActivity(intent2);
            }
        });
        addfruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Adminpage.this, ADDFRUIT.class);
                startActivity(intent2);
            }
        });


        addriders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Adminpage.this, addriders.class);
                startActivity(intent3);
            }
        });
    }
}