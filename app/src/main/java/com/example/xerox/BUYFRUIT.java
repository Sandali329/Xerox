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

public class BUYFRUIT extends AppCompatActivity implements buyfruitRVadaptor.fruitclickinterface {


    private RecyclerView fruitRV;
    private FirebaseDatabase db;
    private DatabaseReference fruitref;
    private ArrayList<fruit> fruitarraylist;
    private RelativeLayout buyfruitRL, bottomsheetRL;
    private buyfruitRVadaptor buyfruitRVadaptor;
    private FloatingActionButton addToCart;
    private Button select;
    private ImageView itemImage;

    private TextView itemPrice, itemName, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyfruit);

        fruitRV = findViewById(R.id.idbuyRVfruit);
        db = FirebaseDatabase.getInstance();
        fruitref = FirebaseDatabase.getInstance().getReference("Fruits");
        fruitarraylist = new ArrayList<>();
        buyfruitRL = findViewById(R.id.fruitLBsheet);

        buyfruitRVadaptor = new buyfruitRVadaptor(fruitarraylist, this, this);
        fruitRV.setLayoutManager(new LinearLayoutManager(this));
        fruitRV.setAdapter(buyfruitRVadaptor);
        getallfruits();
    }

    private void getallfruits() {
        fruitarraylist.clear();
        fruitref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                fruitarraylist.add(snapshot.getValue(fruit.class));
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                buyfruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, String previousChildName) {
                buyfruitRVadaptor.notifyDataSetChanged();
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

    private void displayborromsheet(fruit fruit) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.itemdescription, bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);


        addToCart = layout.findViewById(R.id.btn_addCart);

        itemImage = layout.findViewById(R.id.item_image_details);
        itemPrice = layout.findViewById(R.id.item_price);
        itemName = layout.findViewById(R.id.item_name);
        value = layout.findViewById(R.id.value);
        select=layout.findViewById(R.id.select);

        itemName.setText(fruit.getFname());
        itemPrice.setText(fruit.getFcusprice());


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(BUYFRUIT.this,itemDetailsActivity.class);
                i.putExtra("fruits",fruit);
                startActivity(i);
            }
        });
    }
}