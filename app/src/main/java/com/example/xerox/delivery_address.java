package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delivery_address extends AppCompatActivity {

    DatabaseReference addRef;

    EditText address1;
    EditText name;
    EditText phone1;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);

        name = findViewById(R.id.name1);
        address1 = findViewById(R.id.address1);
        phone1 = findViewById(R.id.phone1);
        next = findViewById(R.id.next);

        addRef = FirebaseDatabase.getInstance().getReference().child("Address");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), placeOrder.class);
                startActivity(intent);
                insertAddressData();
            }
        });
      }

    private void insertAddressData(){

        final Intent intent = new Intent(this, placeOrder.class);

        String Name = name.getText().toString();
        String Address = address1.getText().toString();
        String Phone = phone1.getText().toString();

        Bundle extras = new Bundle();
        extras.putString("ITEM_NAME",Name);
        extras.putString("ADDRESS",Address);
        extras.putString("AMOUNT",Phone);

        intent.putExtras(extras);

        address address = new address(Name,Address,Phone);

        addRef.push().setValue(address);
        Toast toast = Toast.makeText(getApplicationContext(), "Add Successful",Toast.LENGTH_LONG);
        toast.show();

    }
}