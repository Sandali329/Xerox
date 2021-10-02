package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName, TelNo, Email, Pword;
    private Button registerBtn;
    private DatabaseReference supplierref;
    private TextView Tvlogin;

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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
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


    private void CreateAccount() {
        String name = userName.getText().toString();
        String phone = TelNo.getText().toString();
        String email = Email.getText().toString();
        String password = Pword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(RegisterActivity.this, "Please enter your name..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(RegisterActivity.this, "Please enter your phone no..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this, "Please enter your email..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(RegisterActivity.this, "Please enter your password..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            validateEmail(name, phone, email, password);
        }
    }


    //insert supplier details
    private void validateEmail(String name, String phone, String email, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot)
            {
                if(!(snapshot.child("RegSuppliers").child(email).exists()))
                {
                    HashMap<String, Object> supdataMap = new HashMap<>();
                    supdataMap.put("name", name);
                    supdataMap.put("phone", phone);
                    supdataMap.put("email", email);
                    supdataMap.put("password", password);

                    RootRef.child("RegSuppliers").child(phone).updateChildren(supdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Your account created successfully.. ", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(RegisterActivity.this,SupplierActivity1.class);
                                        i.putExtra("sname",name);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Cannot create an account. Please try again..", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This " +phone +"already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }



}