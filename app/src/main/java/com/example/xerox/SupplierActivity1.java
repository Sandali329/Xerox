package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SupplierActivity1 extends AppCompatActivity {
    EditText et_supName;
    String name;

    Button fruits, veg,buyfruit,addfruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier1);

        buyfruit = (Button) findViewById(R.id.idbuyfruitbtn);
addfruit=(Button)findViewById(R.id.idaddfruit);
addfruit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent2 = new Intent(SupplierActivity1.this, ADDFRUIT.class);
        startActivity(intent2);
    }
});

        buyfruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SupplierActivity1.this, BUYFRUIT.class);
                startActivity(intent2);


            }
        });




        et_supName = findViewById(R.id.et_supName);
        Intent start = getIntent();
        name = start.getStringExtra("name");
        et_supName.setText(name);
    }

    public void gotoFruitsPage(){

    }

    public void FruitsPage(View view) {
        Intent intent1 = new Intent(this,VIEWFRUIT.class);
        startActivity(intent1);
    }

    public void VegPage(View view) {
        Intent intent1 = new Intent(this,VIEWFRUIT.class);
        startActivity(intent1);
    }


}