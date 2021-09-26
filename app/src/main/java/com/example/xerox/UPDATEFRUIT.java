package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UPDATEFRUIT extends AppCompatActivity {


    public Button updatefruit, deletefruit;

    private EditText fcode, fname, fprice,fcusprice,fimglink;
    private FirebaseDatabase db;
    DatabaseReference fruitref;
private fruit fruit;
private String Fcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatefruit);
db=FirebaseDatabase.getInstance();

        fcode = findViewById(R.id.Fcode);
        fname = findViewById(R.id.Fname);
        fprice = findViewById(R.id.Fprice);
        fcusprice=findViewById(R.id.Fcusprice);
        fimglink=findViewById(R.id.Fimglink);
        updatefruit = findViewById(R.id.updateFbtn);
        deletefruit=findViewById(R.id.deleteFbtn);

fruit=getIntent().getParcelableExtra("fruits");
if(fruit!=null){

    fcode.setText(fruit.getFcode());
    fname.setText(fruit.getFname());
    fprice.setText(fruit.getFprice());
    fcusprice.setText(fruit.getFcusprice());
    fimglink.setText(fruit.getFimglink());
    Fcode=fruit.getFcode();
}

        fruitref=db.getReference("Fruits").child(Fcode);
updatefruit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String Fcode = fcode.getText().toString();
        String Fname = fname.getText().toString();
        String Fprice = fprice.getText().toString();
        String Fcusprice=fcusprice.getText().toString();
        String Fimglink=fimglink.getText().toString();

        Map<String,Object>map=new HashMap<>();
        map.put("fcode",Fcode);
        map.put("fname",Fname);
        map.put("fprice",Fprice);
        map.put("fcusprice",Fcusprice);
        map.put("fimglink",Fimglink);

        fruitref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                fruitref.updateChildren(map);
                Toast.makeText(UPDATEFRUIT.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UPDATEFRUIT.this,ADMINVIEWFRUITS.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(UPDATEFRUIT.this, "Updating Failed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
});
// delete function codes
deletefruit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        deletefruit();
    }
});
    }
    private void deletefruit(){

        fruitref.removeValue();
        Toast.makeText(this, "Fruit Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UPDATEFRUIT.this,ADMINVIEWFRUITS.class));
    }
}