package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class ADMINVIEWFRUITS extends AppCompatActivity implements adminviewfruitRVadaptor.fruitclickinterface {

    private RecyclerView fruitRV;
    private FirebaseDatabase db;
    private DatabaseReference fruitref;
    private ArrayList<fruit>fruitarraylist;
    private RelativeLayout viewfruitRL,bottomsheetRL;
    private adminviewfruitRVadaptor adminviewfruitRVadaptor;
    private ImageButton adminbackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewfruits);

        fruitRV=findViewById(R.id.idRVadminviewfruits);
        db=FirebaseDatabase.getInstance();
        fruitref=FirebaseDatabase.getInstance().getReference("Fruits");
        fruitarraylist=new ArrayList<>();
        viewfruitRL=findViewById(R.id.idRLadminviewfruits);
adminbackbtn=findViewById(R.id.Ibtn_backadmin);
adminbackbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i =new Intent(ADMINVIEWFRUITS.this,Adminpage.class);
        startActivity(i);
    }
});
        bottomsheetRL=findViewById(R.id.bottomsheetid);

        adminviewfruitRVadaptor=new adminviewfruitRVadaptor(fruitarraylist,this,this);
        fruitRV.setLayoutManager(new LinearLayoutManager(this));
        fruitRV.setAdapter(adminviewfruitRVadaptor);
        getallfruits();
    }
    private void getallfruits(){
        fruitarraylist.clear();
        fruitref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                fruitarraylist.add(snapshot.getValue(fruit.class));
                adminviewfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                adminviewfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                adminviewfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                adminviewfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onfruitclick(int position) {

        displayborromsheet(fruitarraylist.get(position));
    }

    private void displayborromsheet(fruit fruit){
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        View layout= LayoutInflater.from(this).inflate(R.layout.activity_adminviewfruit,bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        TextView Fname=layout.findViewById(R.id.idfname);
        TextView Fprice=layout.findViewById(R.id.idfprice);
        TextView Fcusprice=layout.findViewById(R.id.idfcusprice);
        ImageView Fimg=layout.findViewById(R.id.idfimg);
        Picasso.get().load(fruit.getFimglink()).into(Fimg);
        Fname.setText(fruit.getFname());
        Fprice.setText("Supplier Price: "+fruit.getFprice());
Fcusprice.setText("Customer Price: "+fruit.getFcusprice());
        Button updatebtn=layout.findViewById(R.id.editbutton);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ADMINVIEWFRUITS.this,UPDATEFRUIT.class);
                i.putExtra("fruits",fruit);
                startActivity(i);
            }
        });
    }
}