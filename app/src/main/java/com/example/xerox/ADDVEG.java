package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class ADDVEG extends AppCompatActivity {
    public Button viewveg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addveg);

        viewveg=(Button) findViewById(R.id.viewVbtn);
        viewveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ADDVEG.this,VIEWVEG.class);
                startActivity(intent);
            }
        });

    }
}