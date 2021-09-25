package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class viewFruit_Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fruit_card);
    }

    public void Edit(View view) {
        Intent intent1 = new Intent(this,SupplierActivity1.class);
        startActivity(intent1);
    }
}