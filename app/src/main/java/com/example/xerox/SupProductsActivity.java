package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class SupProductsActivity extends AppCompatActivity {

    FloatingActionButton plus, minus;

    ImageView productImage;
    TextView pName, pPrice;
    EditText Qty;
    Button select;
    FirebaseDatabase DB;
    //DatabaseReference fruitRef;
    private fruit fruit;

    /*DatabaseReference cartListRef;
    itemList list1;
    itemList list2;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_products);

        DB = FirebaseDatabase.getInstance();
        fruit = getIntent().getParcelableExtra("fruits");

        plus = findViewById(R.id.cart_btn);
        minus = findViewById(R.id.minus_btn);

        productImage = findViewById(R.id.p_image);
        pName = findViewById(R.id.I_name);
        pPrice = findViewById(R.id.I_price);
        Qty = findViewById(R.id.et_qty);
        select = findViewById(R.id.Cart_btn);

        pName.setText(fruit.getFname());
        pPrice.setText("RS. " + fruit.getFprice() +"   Per 250g");




        /*select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SupProductsActivity.this, "Item added to Cart List..", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SupProductsActivity.this,SupplierActivity1.class);
                startActivity(intent1);
            }
        });*/


    }





   /* private void getQtyButton(){
        String no;
        no = Qty.getText().toString().trim();

    }


    private void addingToCartList() {
        String savecurrentDate, savecurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        savecurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("fcode", ItemCode);
        cartMap.put("pname", pName.getText().toString());
        cartMap.put("price", pPrice.getText().toString());
        cartMap.put("date", savecurrentDate);
        cartMap.put("time", savecurrentTime);
        cartMap.put("quantity", Qty.getText());

        cartListRef.child("Supplier").child("Fruits").child(ItemCode).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    cartListRef.child("Supplier")
                            .child("Fruits")
                            .child(ItemCode).updateChildren(cartMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SupProductsActivity.this, "Added to Cart List..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SupProductsActivity.this,VIEWFRUIT.class);
                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });

    }




    private void AddCart(){
        String savecurrentDate, savecurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        savecurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calForDate.getTime());

        final  itemList list1;
        list1 = new itemList();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("itemList");

        //list1.setItemCode(ItemCode);
        list1.setItemName(pName.getText().toString().trim());
        list1.setItemPrice(pPrice.getText().toString().trim());
        list1.setItemPrice(Qty.getText().toString().trim());
        list1.setDate(savecurrentDate);
        list1.setTime(savecurrentTime);

        cartListRef.push().setValue(list1);
        Toast.makeText(SupProductsActivity.this, "Item added to Cart List..", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SupProductsActivity.this,VIEWFRUIT.class);
        startActivity(intent);

    }




    /*private void getProductDetails(String ItemCode){
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference();

        productRef.child(ItemCode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    fruit product = snapshot.getValue(fruit.class);

                    pName.setText(product.getFname());
                    pPrice.setText(product.getFprice());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }*/


}