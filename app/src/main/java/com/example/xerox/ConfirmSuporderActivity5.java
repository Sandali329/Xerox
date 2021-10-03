package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xerox.LoginSuppliers.LoginSuppliers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmSuporderActivity5 extends AppCompatActivity {

    private EditText supName, supNo, supAddress;
    private Button place_order;

    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_suporder5);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(ConfirmSuporderActivity5.this,"Total Price = " + totalAmount,Toast.LENGTH_SHORT).show();

        supName = (EditText) findViewById(R.id.supName);
        supNo = (EditText) findViewById(R.id.supNo);
        supAddress = (EditText) findViewById(R.id.supAddress);
        place_order = (Button) findViewById(R.id.place_order);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDetail();
            }
        });
    }

    private void checkDetail() {
        if (TextUtils.isEmpty(supName.getText().toString())){
            Toast.makeText(ConfirmSuporderActivity5.this,"Please enter your name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(supNo.getText().toString())){
            Toast.makeText(ConfirmSuporderActivity5.this,"Please enter your phone no",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(supAddress.getText().toString())){
            Toast.makeText(ConfirmSuporderActivity5.this,"Please enter your address",Toast.LENGTH_SHORT).show();
        }
        else {
            ConfirmOrder();
        }
    }


    private void ConfirmOrder() {
        final String savecurrentDate, savecurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM. dd. yyyy");
        savecurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        savecurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Order pickup Details");

        HashMap<String,Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount",totalAmount);
        orderMap.put("name",supName.getText().toString().trim());
        orderMap.put("phone",supNo.getText().toString().trim());
        orderMap.put("address",supAddress.getText().toString().trim());
        orderMap.put("date",savecurrentDate);
        orderMap.put("time",savecurrentTime);
        orderMap.put("state","Not yet assign a rider");

        String phone = supNo.getText().toString();

        orderRef.child("Order").child(phone).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference().child("Supplier Cart")
                            .child("Current Supplier").child("Fruits").removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ConfirmSuporderActivity5.this,"Your order has been succussfull",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ConfirmSuporderActivity5.this,SupplierActivity1.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }
}