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
private fruit fruit2;
private String Fupdatecode;
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



fruit2=getIntent().getParcelableExtra("fruits");
if(fruit2!=null){

    fupdatecode.setText(fruit2.getFcode());
    fupdatename.setText(fruit2.getFname());
    fupdateprice.setText(fruit2.getFprice());
    fupdatecusprice.setText(fruit2.getFcusprice());
    fupdateimglink.setText(fruit2.getFimglink());
    Fupdatecode=fruit2.getFcode();
}

        fruitrefupdate=db.getReference("Fruits").child(Fupdatecode);
updatefruit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String Fupdatecode = fupdatecode.getText().toString();
        String Fupdatename = fupdatename.getText().toString();
        String Fupdateprice = fupdateprice.getText().toString();
        String Fupdatecusprice=fupdatecusprice.getText().toString();
        String Fupdateimglink=fupdateimglink.getText().toString();

        Map<String,Object>map=new HashMap<>();
        map.put("fcode",Fupdatecode);
        map.put("fname",Fupdatename);
        map.put("fprice",Fupdateprice);
        map.put("fcusprice",Fupdatecusprice);
        map.put("fimglink",Fupdateimglink);

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