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

public class UPDATEVEG extends AppCompatActivity {


    public Button updateveg, deleteveg;

    private EditText vcode, vname, vprice,vcusprice,vimglink;
    private FirebaseDatabase db;
    DatabaseReference fruitref;
    private vegetable vegetable;
    private String Vcode;
    private ImageButton adminbackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateveg);


        adminbackbtn=findViewById(R.id.Ibtn_backadmin);
        adminbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(UPDATEVEG.this,Adminpage.class);
                startActivity(i);
            }
        });



        db=FirebaseDatabase.getInstance();

        vcode = findViewById(R.id.Vcode);
        vname = findViewById(R.id.Vname);
        vprice = findViewById(R.id.Vprice);
        vcusprice=findViewById(R.id.Vcusprice);
        vimglink=findViewById(R.id.Vimglink);
        updateveg = findViewById(R.id.updateVbtn);
        deleteveg=findViewById(R.id.deleteVbtn);

        vegetable=getIntent().getParcelableExtra("vegetables");
        if(vegetable!=null){

            vcode.setText(vegetable.getVcode());
            vname.setText(vegetable.getVname());
            vprice.setText(vegetable.getVprice());
            vcusprice.setText(vegetable.getVcusprice());
            vimglink.setText(vegetable.getVimglink());
            Vcode=vegetable.getVcode();
        }

        fruitref=db.getReference("Vegetables").child(Vcode);
        updateveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Vcode = vcode.getText().toString();
                String Vname = vname.getText().toString();
                String Vprice = vprice.getText().toString();
                String Vcusprice=vcusprice.getText().toString();
                String Vimglink=vimglink.getText().toString();

                Map<String,Object>map=new HashMap<>();
                map.put("vcode",Vcode);
                map.put("vname",Vname);
                map.put("vprice",Vprice);
                map.put("vcusprice",Vcusprice);
                map.put("vimglink",Vimglink);

                fruitref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        fruitref.updateChildren(map);
                        Toast.makeText(UPDATEVEG.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UPDATEVEG.this,ADMINVIEWVEGETABLES.class));
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(UPDATEVEG.this, "Updating Failed!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
// delete function codes
        deleteveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletevegetable();
            }
        });
    }
    private void deletevegetable(){

        fruitref.removeValue();
        Toast.makeText(this, "vegetable Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UPDATEVEG.this,ADMINVIEWVEGETABLES.class));
    }
}