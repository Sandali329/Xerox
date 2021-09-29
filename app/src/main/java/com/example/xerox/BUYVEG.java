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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BUYVEG extends AppCompatActivity implements buyvegRVadaptor.vegetableclickinterface {


    private RecyclerView vegetableRV;
    private FirebaseDatabase db;
    private DatabaseReference fruitref;
    private ArrayList<vegetable> vegetableArraylist;
    private RelativeLayout buyvegRL, bottomsheetRL;
    private buyvegRVadaptor buyvegRVadaptor;
    private FloatingActionButton addToCart;
    private Button select;
    private ImageView itemImage;

    private TextView itemPrice, itemName, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyveg);

       vegetableRV = findViewById(R.id.idbuyRVvegetable);
        db = FirebaseDatabase.getInstance();
        fruitref = FirebaseDatabase.getInstance().getReference("Vegetables");
        vegetableArraylist = new ArrayList<>();
        buyvegRL = findViewById(R.id.idbuyRLvegetable);

        buyvegRVadaptor = new buyvegRVadaptor(vegetableArraylist, this, this);
        vegetableRV.setLayoutManager(new LinearLayoutManager(this));
        vegetableRV.setAdapter(buyvegRVadaptor);
        getallfruits();
    }

    private void getallfruits() {
        vegetableArraylist.clear();
        fruitref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                vegetableArraylist.add(snapshot.getValue(vegetable.class));
                buyvegRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                buyvegRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                buyvegRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                buyvegRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onvegetableclick(int position) {
        displayborromsheet(vegetableArraylist.get(position));
    }

    private void displayborromsheet(vegetable vegetable) {



    }
}