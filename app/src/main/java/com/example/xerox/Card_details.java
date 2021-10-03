package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Card_details extends AppCompatActivity {

        EditText cardno;
        EditText cardname;
        EditText date;
        EditText cvv;
        Button pay;

        DatabaseReference adddataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);


        cardno = findViewById(R.id.cardno);
        cardname = findViewById(R.id.cardname);
        date = findViewById(R.id.date);
        cvv = findViewById(R.id.cvv);
        pay = findViewById(R.id.paynow);

        adddataRef = FirebaseDatabase.getInstance().getReference().child("Card");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { insertCardData();}

        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), order_successfull.class);
                startActivity(intent);
            }
        });
    }



    private void insertCardData(){

        String CardNo = cardno.getText().toString();
        String Name =  cardname.getText().toString();
        String Date = date.getText().toString();
        String Cvv = cvv.getText().toString();

        Card card = new Card(CardNo,Name,Date,Cvv);

        adddataRef.push().setValue(card);
        Toast toast = Toast.makeText(getApplicationContext(),  "Payment Successfull", Toast.LENGTH_LONG);
            toast.show();
    }


}