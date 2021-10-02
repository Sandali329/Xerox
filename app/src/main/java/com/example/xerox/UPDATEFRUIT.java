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

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UPDATEFRUIT extends AppCompatActivity {


    public Button updatefruit, deletefruit;

    private EditText fupdatecode, fupdatename, fupdateprice,fupdatecusprice,fupdateimglink;
    private FirebaseDatabase db;
    DatabaseReference fruitrefupdate;
private fruit fruit;
private String Fcode;
    private ImageButton adminbackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatefruit);


        adminbackbtn=findViewById(R.id.Ibtn_backadmin);
        adminbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(UPDATEFRUIT.this,Adminpage.class);
                startActivity(i);
            }
        });


db=FirebaseDatabase.getInstance();

        fupdatecode = findViewById(R.id.Fupdatecode);
        fupdatename = findViewById(R.id.Fupdatename);
        fupdateprice = findViewById(R.id.Fupdateprice);
        fupdatecusprice=findViewById(R.id.Fupdatecusprice);
        fupdateimglink=findViewById(R.id.Fupdateimglink);
        updatefruit = findViewById(R.id.updateFbtn);
        deletefruit=findViewById(R.id.deleteFbtn);



fruit=getIntent().getParcelableExtra("fruits");
if(fruit!=null){

    fupdatecode.setText(fruit.getFcode());
    fupdatename.setText(fruit.getFname());
    fupdateprice.setText(fruit.getFprice());
    fupdatecusprice.setText(fruit.getFcusprice());
    fupdateimglink.setText(fruit.getFimglink());
    Fcode=fruit.getFcode();
}

        fruitrefupdate=db.getReference("Fruits").child(Fcode);
updatefruit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String Fcode = fupdatecode.getText().toString();
        String Fname = fupdatename.getText().toString();
        String Fprice = fupdateprice.getText().toString();
        String Fcusprice=fupdatecusprice.getText().toString();
        String Fimglink=fupdateimglink.getText().toString();

        Map<String,Object>map=new HashMap<>();
        map.put("fcode",Fcode);
        map.put("fname",Fname);
        map.put("fprice",Fprice);
        map.put("fcusprice",Fcusprice);
        map.put("fimglink",Fimglink);

        fruitrefupdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                fruitrefupdate.updateChildren(map);
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

        fruitrefupdate.removeValue();
        Toast.makeText(this, "Fruit Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UPDATEFRUIT.this,ADMINVIEWFRUITS.class));
    }
}