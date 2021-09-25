package com.example.xerox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ADDFRUIT extends AppCompatActivity {

    public Button viewfruit, addfruit;

    private EditText fcode, fname, fprice,fcusprice,fimglink;
    private FirebaseDatabase db;
    DatabaseReference fruitref;


    fruit fruit1;

    private void clearControls() {
        fcode.setText("");
        fname.setText("");
        fprice.setText("");
        fcusprice.setText("");
        fimglink.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addfruit);


        viewfruit = (Button) findViewById(R.id.viewFbtn);


        viewfruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ADDFRUIT.this, ADMINVIEWFRUITS.class);
                //startActivity(intent);


            }
        });


        /*fcode = findViewById(R.id.Fcode);
        fname = findViewById(R.id.Fname);
        fprice = findViewById(R.id.Fprice);
        fcusprice=findViewById(R.id.Fcusprice);
        fimglink=findViewById(R.id.Fimglink);
        addfruit = findViewById(R.id.addFbtn);*/

        db = FirebaseDatabase.getInstance();
        fruitref = db.getReference("Fruits");

        addfruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fcode = fcode.getText().toString();
                String Fname = fname.getText().toString();
                String Fprice = fprice.getText().toString();
                String Fcusprice=fcusprice.getText().toString();
                String Fimglink=fimglink.getText().toString();
                fruit fruit = new fruit(Fcode, Fname, Fprice,Fcusprice,Fimglink);
                fruitref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fruitref.child(Fcode).setValue(fruit);
                        Toast.makeText(ADDFRUIT.this, "Fruit Added Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ADDFRUIT.this, "error is " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }


        });
    }
}
