package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //EditText Un, Pswd;
    Button signIn;
    //private ImageButton back;

    // DatabaseReference supplierref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.btn_login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, supcusselect.class);
                startActivity(i);
            }
        });
    }

    public void backToMain(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


        /*Un = findViewById(R.id.et_UN);
        Pswd = findViewById(R.id.et_PW);
        signIn = findViewById(R.id.btn_login);
        back = findViewById(R.id.Ibtn_back1);

       supplierref = FirebaseDatabase.getInstance().getReference().child("Supplier");

        String UserName = Un.getText().toString();
        String Password = Pswd.getText().toString();

        supplierref.child(UserName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Supplier supplier = dataSnapshot.getValue(Supplier.class);

                Intent start = new Intent(LoginActivity.this,SupplierActivity1.class);

                if (Password.equals(supplier.getPword()) && UserName.equals(supplier.getPword())) {
                    signIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(LoginActivity.this, "ELogin successful..", Toast.LENGTH_SHORT).show();
                            startActivity(start);
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Enter correct credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    public void supplierPage(View view) {
        if(supplierref.child(UserName) == supplierref.child(Password) ){

        }else{
                Toast.makeText(LoginActivity.this, "User Doesn't Exit..!", Toast.LENGTH_SHORT).show();
        }
    }*/


