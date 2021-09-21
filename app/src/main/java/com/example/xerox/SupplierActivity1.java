package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SupplierActivity1 extends AppCompatActivity {
    EditText et_supName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier1);

        et_supName = findViewById(R.id.et_supName);
        Intent start = getIntent();
        name = start.getStringExtra("name");
        et_supName.setText(name);
    }
}