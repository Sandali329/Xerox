package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cusselect extends AppCompatActivity {
    Button fruits, veg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusselect);

veg=(Button)findViewById(R.id.btnbuy_veg);
veg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent2 = new Intent(cusselect.this, BUYVEG.class);
        startActivity(intent2);
    }
});
        fruits=(Button)findViewById(R.id.btnbuy_fruits);

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(cusselect.this, BUYFRUIT.class);
                startActivity(intent2);
            }
        });



    }
}