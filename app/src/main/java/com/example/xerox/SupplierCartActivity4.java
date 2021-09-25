package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xerox.classes.SupOrder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SupplierCartActivity4 extends AppCompatActivity {

    List<SupOrder> supOrder;
    RecyclerView recyclerView;
    SupCartRVadapter supCartRVadapter;
    DatabaseReference supCartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_cart4);

        recyclerView = findViewById(R.id.cart_recyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        supOrder = new ArrayList<>();

        supCartRef = FirebaseDatabase.getInstance().getReference("SupOrder");

        supCartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    SupOrder data = ds.getValue(SupOrder.class);
                    supOrder.add(data);
                }
                supCartRVadapter = new SupCartRVadapter(supOrder);
                recyclerView.setAdapter(supCartRVadapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }


}