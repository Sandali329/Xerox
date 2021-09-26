package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText userName, TelNo, Email, Pword;
    Button registerBtn;
    DatabaseReference supplierref;
    Supplier sup1;
    TextView Tvlogin;

    //method to clear all user inputs
    private  void clearControls(){
        userName.setText("");
        TelNo.setText("");
        Email.setText("");
        Pword.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.et_userName);
        TelNo = (EditText) findViewById(R.id.et_Phone);
        Email = (EditText)findViewById(R.id.et_Email);
        Pword = (EditText)findViewById(R.id.et_Password);
        registerBtn = (Button) findViewById(R.id.btn_register);
        Tvlogin = (TextView) findViewById(R.id.tv_login);

        sup1 = new Supplier();
        supplierref = FirebaseDatabase.getInstance().getReference().child("Supplier");

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sup1.setUserName(userName.getText().toString().trim());
                sup1.setTelNo(TelNo.getText().toString().trim());
                sup1.setEmail(Email.getText().toString().trim());
                sup1.setPword(Pword.getText().toString().trim());

                supplierref.push().setValue(sup1);

                Intent intent = new Intent(RegisterActivity.this,SupplierActivity1.class);

                String name = userName.getText().toString();

                intent.putExtra("sendName", name);
                Toast.makeText(RegisterActivity.this, "Successfully Registered..", Toast.LENGTH_SHORT).show();
                clearControls();
                startActivity(intent);
            }
        });


        Tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }


}