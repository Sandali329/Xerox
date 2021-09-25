package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class supcusselect extends AppCompatActivity {

    Button buy, sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supcusselect);



        buy = findViewById(R.id.btn_cus);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(supcusselect.this, cusselect.class);
                startActivity(i);
            }
        });
        sup=findViewById(R.id.btn_sup);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(supcusselect.this, SupplierActivity1.class);
                startActivity(i);
            }
        });
    }
}