package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {

    Button fruit,veg,addfruit,addveg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        veg=findViewById(R.id.adminveg);
addfruit=findViewById(R.id.adminaddfruit);
addveg=findViewById(R.id.adminaddvegetable);
        addveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Adminpage.this, ADDVEG.class);
                startActivity(intent2);
            }
        });
        fruit=findViewById(R.id.adminfruit);
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Adminpage.this, ADMINVIEWVEGETABLES.class);
                startActivity(intent2);
            }
        });
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
    }
}