package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xerox.ViewHolders.CartViewHolder;
import com.example.xerox.classes.SupOrder;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SupplierCartActivity4 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button confirm;
    private TextView total;
    private ImageView emptyCart;

    private int netTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_cart4);

        recyclerView = findViewById(R.id.cart_recyView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        confirm = (Button) findViewById(R.id.confirm_btn);
        total = (TextView) findViewById(R.id.price);
        emptyCart = (ImageView) findViewById(R.id.emptyCart);

        //total.setText(String.valueOf(netTotal));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total.setText(String.valueOf(netTotal));

                Intent intent = new Intent(SupplierCartActivity4.this,ConfirmSuporderActivity5.class);
                intent.putExtra("Total Price",String.valueOf(netTotal));
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onStart(){
        super.onStart();
        //CheckCart();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Supplier Cart");
        FirebaseRecyclerOptions<SupOrder> options =
                new FirebaseRecyclerOptions.Builder<SupOrder>()
                .setQuery(cartListRef.child("Current Supplier").child("Fruits"),SupOrder.class).build();
        FirebaseRecyclerAdapter<SupOrder, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<SupOrder, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position, @NonNull @NotNull SupOrder model)
            {
                holder.pQuantity.setText("Quantity: "+ model.getQuantity());
                holder.pPrice.setText("Price: Rs."+model.getFprice());
                holder.pName.setText("Item: "+model.getFname());

                int oneProductTotal = ((Integer.valueOf(model.getFprice())) * Integer.valueOf(model.getQuantity()) );
                netTotal = netTotal + oneProductTotal;
                total.setText("Total Price = Rs."+String.valueOf(netTotal));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]
                                {
                                       "Edit",
                                       "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(SupplierCartActivity4.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(i==0)
                                {
                                    Intent intent = new Intent(SupplierCartActivity4.this,SupplierActivity3.class);
                                    intent.putExtra("fcode",model.getFcode());
                                    startActivity(intent);
                                }
                                if(i==1)
                                {
                                    cartListRef.child("Current Supplier").child("Fruits").child(model.getFcode())
                                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(SupplierCartActivity4.this,"Item removed successfully",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(SupplierCartActivity4.this,SupplierActivity1.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }
                            }
                        });

                        builder.show();
                    }
                });
            }

            @NonNull
            @NotNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supcart_layout,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    /*private void CheckCart(){
        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order pickup Details");
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String state = snapshot.child("state").getValue().toString();
                    if(state.equals("Assign a rider for your order")){
                        recyclerView.setVisibility(View.GONE);
                        emptyCart.setVisibility(View.VISIBLE);
                        confirm.setVisibility(View.GONE);
                    }
                    else if(state.equals("Not yet assign a rider")){
                        recyclerView.setVisibility(View.GONE);
                        emptyCart.setVisibility(View.VISIBLE);
                        confirm.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }*/


}