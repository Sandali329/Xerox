package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BUYFRUIT extends AppCompatActivity implements buyfruitRVadaptor.fruitclickinterface{



    private RecyclerView fruitRV;
    private FirebaseDatabase db;
    private DatabaseReference fruitref;
    private ArrayList<fruit> fruitarraylist;
    private RelativeLayout viewfruitRL;
    private buyfruitRVadaptor buyfruitRVadaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyfruit);

        fruitRV=findViewById(R.id.idbuyRVfruit);
        db=FirebaseDatabase.getInstance();
        fruitref=FirebaseDatabase.getInstance().getReference("Fruits");
        fruitarraylist=new ArrayList<>();
        viewfruitRL=findViewById(R.id.idbuyRLfruit);

        buyfruitRVadaptor=new buyfruitRVadaptor(fruitarraylist,this,  this);
        fruitRV.setLayoutManager(new LinearLayoutManager(this));
        fruitRV.setAdapter(buyfruitRVadaptor);
        getallfruits();
    }
    private void getallfruits(){
        fruitarraylist.clear();
        fruitref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                fruitarraylist.add(snapshot.getValue(fruit.class));
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onfruitclick(int position) {

    }

}