package com.example.xerox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class buyfruitRVadaptor extends RecyclerView.Adapter<buyfruitRVadaptor.Viewholder> {

    private ArrayList<fruit>fruitArrayList;
    private Context context;
    int laspos=-1;

    public buyfruitRVadaptor(ArrayList<fruit> fruitArrayList, Context context, buyfruitRVadaptor.fruitclickinterface fruitclickinterface) {
        this.fruitArrayList = fruitArrayList;
        this.context = context;
        this.fruitclickinterface = fruitclickinterface;
    }

    private fruitclickinterface fruitclickinterface;
    @NonNull
    @NotNull
    @Override
    public buyfruitRVadaptor.Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.buyfruit_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull buyfruitRVadaptor.Viewholder holder, int position) {
        fruit fruit=fruitArrayList.get(position);
        holder.fruitnameTV.setText(fruit.getFname());
        holder.fruitcuspriceTV.setText("RS "+fruit.getFcusprice()+"  Per 250g");

        setanimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fruitclickinterface.onfruitclick(position);
            }
        });
    }
    private void setanimation(View itemview,int position){
        if(position>laspos){
            Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemview.setAnimation(animation);
            laspos=position;
        }
    }
    @Override
    public int getItemCount() {
        return fruitArrayList.size();
    }

    public interface fruitclickinterface{
        void onfruitclick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView fruitnameTV,fruitcuspriceTV;
        private ImageView fruitimg;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            fruitnameTV=itemView.findViewById(R.id.idfname);
            fruitcuspriceTV=itemView.findViewById(R.id.idfcusprice);
            fruitimg=itemView.findViewById(R.id.idIVfruit);
        }
    }
}
