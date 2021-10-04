package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ADDVEG extends AppCompatActivity {
    public Button viewveg;
    public Button  addveg;

    private EditText vcode, vname, vprice,vcusprice,vimglink;
    private FirebaseDatabase db;
    DatabaseReference fruitref;


    vegetable vegetable;

    private void clearControls() {
        vcode.setText("");
        vname.setText("");
        vprice.setText("");
        vcusprice.setText("");
        vimglink.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addveg);

        viewveg=(Button) findViewById(R.id.viewFbtn);
        viewveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ADDVEG.this,ADMINVIEWVEGETABLES.class);
                startActivity(intent);
            }
        });

        vcode = findViewById(R.id.Vcode);
        vname = findViewById(R.id.Vname);
        vprice = findViewById(R.id.Vprice);
        vcusprice=findViewById(R.id.Vcusprice);
        vimglink=findViewById(R.id.Vimglink);
        addveg = findViewById(R.id.addVbtn);

        db = FirebaseDatabase.getInstance();
        fruitref = db.getReference("Vegetables");

        //Add fruits to database

        addveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Vcode = vcode.getText().toString();
                String Vname = vname.getText().toString();
                String Vprice = vprice.getText().toString();
                String Vcusprice=vcusprice.getText().toString();
                String Vimglink=vimglink.getText().toString();
                vegetable vegetable = new vegetable(Vcode, Vname, Vprice,Vcusprice,Vimglink);
                fruitref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fruitref.child(Vcode).setValue(vegetable);
                        Toast.makeText(ADDVEG.this, "Vegetables Added Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ADDVEG.this, "error is " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }


        });

    }
}