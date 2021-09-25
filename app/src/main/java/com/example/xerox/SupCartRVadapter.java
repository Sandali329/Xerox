package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.xerox.classes.SupOrder;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SupCartRVadapter extends RecyclerView.Adapter{

    List<SupOrder> supOrderList;

    public SupCartRVadapter(List<SupOrder> supOrder) {
        this.supOrderList = supOrder;
    }



    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_fruit_card,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        SupOrder supOrder = supOrderList.get(position);
        viewHolderClass.item.setText("Product name:                   " + supOrder.getFname());
        viewHolderClass.price.setText("Product price per 250g:        " + supOrder.getFprice());
        viewHolderClass.quantity.setText("Product quantity:             " + supOrder.getQty());

    }

    @Override
    public int getItemCount() {
        return supOrderList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item, price, quantity;

        Button btnDelete;

        public ViewHolderClass(@NonNull @NotNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.card_fruitName);
            price = itemView.findViewById(R.id.card_fruitPrice);
            quantity = itemView.findViewById(R.id.card_fruitQty);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SupOrder order = new SupOrder();
            //int id = supOrderList.get(getAdapterPosition().g)
        }
    }


}
