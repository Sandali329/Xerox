package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ADMINVIEWVEGETABLES extends AppCompatActivity implements adminviewvegetableRVadaptor.vegetableclickinterface {

    private RecyclerView adminvegetableRV;
    private FirebaseDatabase db;
    private DatabaseReference vegetableref;
    private ArrayList<vegetable>vegetablearraylist;
    private RelativeLayout viewvegetableRL,bottomsheetRL;
    private adminviewvegetableRVadaptor adminviewvegetableRVadaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewveg);

        adminvegetableRV=findViewById(R.id.idRVadminviewvegetables);
        db=FirebaseDatabase.getInstance();
        vegetableref=FirebaseDatabase.getInstance().getReference("Vegetables");
        vegetablearraylist=new ArrayList<>();
        viewvegetableRL=findViewById(R.id.idRLadminviewvegetables);

        bottomsheetRL=findViewById(R.id.bottomsheetvegid);

        adminviewvegetableRVadaptor=new adminviewvegetableRVadaptor(vegetablearraylist,this,this);
       adminvegetableRV.setLayoutManager(new LinearLayoutManager(this));
        adminvegetableRV.setAdapter(adminviewvegetableRVadaptor);
        getallvegetable();
    }
    private void getallvegetable(){
        vegetablearraylist.clear();
        vegetableref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                vegetablearraylist.add(snapshot.getValue(vegetable.class));
                adminviewvegetableRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                adminviewvegetableRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                adminviewvegetableRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                adminviewvegetableRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onvegetableclick(int position) {
        displayborromsheet(vegetablearraylist.get(position));
    }


    private void displayborromsheet(vegetable vegetable){
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        View layout= LayoutInflater.from(this).inflate(R.layout.activity_adminviewveg,bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        TextView Vname=layout.findViewById(R.id.idfname);
        TextView Vprice=layout.findViewById(R.id.idfprice);
        Button editBtn = layout.findViewById(R.id.editvegbutton);


        Vname.setText(vegetable.getVname());
        Vprice.setText(vegetable.getVprice());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ADMINVIEWVEGETABLES.this,SupplierActivity3.class);
                startActivity(intent);
            }
        });

    }

}