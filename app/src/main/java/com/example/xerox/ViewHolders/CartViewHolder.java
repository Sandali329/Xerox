package com.example.xerox.ViewHolders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xerox.Interfaces.ItemClickListner;
import com.example.xerox.R;

import org.jetbrains.annotations.NotNull;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView pName, pPrice, pQuantity;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull @NotNull View itemView)
    {
        super(itemView);

        pName = itemView.findViewById(R.id.cart_name);
        pPrice = itemView.findViewById(R.id.cart_price);
        pQuantity = itemView.findViewById(R.id.cart_qty);
    }

    @Override
    public void onClick(View view) {
        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
