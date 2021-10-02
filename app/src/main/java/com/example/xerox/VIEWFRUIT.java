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

public class VIEWFRUIT extends AppCompatActivity implements fruitRVadaptor.fruitclickinterface {

    private RecyclerView fruitRV;
    private FirebaseDatabase db;
    private DatabaseReference fruitref;
    private ArrayList<fruit>fruitarraylist;
    private RelativeLayout viewfruitRL,bottomsheetRL;
    private fruitRVadaptor fruitRVadaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfruit);

        fruitRV=findViewById(R.id.idRVfruit);
        db=FirebaseDatabase.getInstance();
        fruitref=FirebaseDatabase.getInstance().getReference("Fruits");
        fruitarraylist=new ArrayList<>();
        viewfruitRL=findViewById(R.id.idRLfruit);

        bottomsheetRL=findViewById(R.id.prod_add_layout);

        fruitRVadaptor=new fruitRVadaptor(fruitarraylist,this,this);
        fruitRV.setLayoutManager(new LinearLayoutManager(this));
        fruitRV.setAdapter(fruitRVadaptor);
        getallfruits();
    }
    private void getallfruits(){
        fruitarraylist.clear();
        fruitref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                fruitarraylist.add(snapshot.getValue(fruit.class));
                fruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                fruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                fruitRVadaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot,  String previousChildName) {
                fruitRVadaptor.notifyDataSetChanged();
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
        View layout= LayoutInflater.from(this).inflate(R.layout.activity_sup_select_item,bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.show();
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        TextView Fname=layout.findViewById(R.id.I_name);
        TextView Fprice=layout.findViewById(R.id.I_price);
        Button selectBtn = layout.findViewById(R.id.Cart_btn);
        ImageView Image = layout.findViewById(R.id.p_image);


        Picasso.get().load(fruit.getFimglink()).into(Image);
        Fname.setText(fruit.getFname());
        Fprice.setText(fruit.getFprice() + ".00/ 250g");

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(VIEWFRUIT.this,SupplierActivity3.class);
                intent.putExtra("fruit",fruit);
                startActivity(intent);*/

                Intent intent = new Intent(VIEWFRUIT.this,SupplierActivity3.class);
                intent.putExtra("fcode",fruit.getFcode());
                startActivity(intent);
            }
        });

    }

}