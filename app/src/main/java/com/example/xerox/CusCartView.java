package com.example.xerox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CusCartView extends AppCompatActivity {

    RecyclerView recyclerView;
    CusAdapter cusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_cart_view);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CusModel> options =
                new FirebaseRecyclerOptions.Builder<CusModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CustomerOrder"), CusModel.class)
                        .build();

        cusAdapter = new CusAdapter(options);
        recyclerView.setAdapter(cusAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cusAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cusAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cussearch, menu);
        MenuItem item = menu.findItem(R.id.Csearch);
        SearchView searchview = (SearchView) item.getActionView();

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                CusSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                CusSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void CusSearch(String str) {
        FirebaseRecyclerOptions<CusModel> options =
                new FirebaseRecyclerOptions.Builder<CusModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CustomerOrder").orderByChild("itemName").startAt(str).endAt(str + "~"), CusModel.class)
                        .build();

        cusAdapter=new CusAdapter(options);
        cusAdapter.startListening();
        recyclerView.setAdapter(cusAdapter);

    }


}