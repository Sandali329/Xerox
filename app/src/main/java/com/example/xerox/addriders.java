package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addriders extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText phone;
    Button add;

    DatabaseReference adddataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addriders);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);

        adddataRef = FirebaseDatabase.getInstance().getReference().child("Rider");
        
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRiderData();
            }
        });
    }
    
    private void insertRiderData(){
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        
        Rider rider = new Rider(Name,Email,Phone);
        
        adddataRef.push().setValue(rider);
        Toast toast = Toast.makeText(getApplicationContext(), "Add Successful",Toast.LENGTH_LONG);
        toast.show();
        
    }
    
}