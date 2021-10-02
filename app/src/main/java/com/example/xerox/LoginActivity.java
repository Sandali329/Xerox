package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.xerox.classes.RegSuppliers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneN, pswd;
    private Button signIn;
    private ProgressDialog loadingBar;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        phoneN = (EditText) findViewById(R.id.et_UN);
        pswd = (EditText) findViewById(R.id.et_PW);
        signIn = (Button) findViewById(R.id.btn_login);
        loadingBar = new ProgressDialog(this);

        //login
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginSupplier();
            }
        });


    }


    private void LoginSupplier() {
        String phone = phoneN.getText().toString();
        String password = pswd.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(LoginActivity.this, "Please enter your phone no..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this, "Please enter your password..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToLogin(phone,password);
        }
    }


    //Login validation function
    private void AllowAccessToLogin(String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child("RegSuppliers").child(phone).exists())
                {
                    RegSuppliers supData = snapshot.child("RegSuppliers").child(phone).getValue(RegSuppliers.class);

                    if(supData.getPhone().equals(phone))
                    {
                        if(supData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "Logged in successfully..", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent i = new Intent(LoginActivity.this, SupplierActivity1.class);
                            name = supData.getName();
                            i.putExtra("sname",name);
                            startActivity(i);


                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Incorrect password..", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    //back arrow button
    public void backToMain(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
