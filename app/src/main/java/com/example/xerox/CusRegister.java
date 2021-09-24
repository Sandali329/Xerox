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

public class CusRegister extends AppCompatActivity {

    EditText etname, etphone, etemail, etpassword;
    Button btn_Creg,btn_show;
    Customer CusObj;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_register);


        etname = (EditText) findViewById(R.id.et_name);
        etphone = (EditText) findViewById(R.id.et_phone);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        btn_Creg = (Button) findViewById(R.id.btn_Creg);
        btn_show=(Button) findViewById(R.id.btn_show);

        CusObj = new Customer();

    }
    public void clearControls(){
        etname.setText("");
        etphone.setText("");
        etemail.setText("");
        etpassword.setText("");
    }

    public void CreateData(View view) {
        dbref = FirebaseDatabase.getInstance().getReference().child("Customer");


        CusObj.setName(etname.getText().toString().trim());
        CusObj.setMobile(etphone.getText().toString().trim());
        CusObj.setEmail(etemail.getText().toString().trim());
        CusObj.setPassword(etpassword.getText().toString().trim());

        dbref.push().setValue(CusObj);
        Toast.makeText(CusRegister.this, "Successfully Registered customer", Toast.LENGTH_SHORT).show();
        clearControls();


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CusRegister.this,DisplayActivity.class));
            }
        });


    }



}
