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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class fruitRVadaptor extends RecyclerView.Adapter<fruitRVadaptor.Viewholder> {

    private ArrayList<fruit>fruitArrayList;
    private Context context;
    int laspos=-1;

    public fruitRVadaptor(ArrayList<fruit> fruitArrayList, Context context, fruitRVadaptor.fruitclickinterface fruitclickinterface) {
        this.fruitArrayList = fruitArrayList;
        this.context = context;
        this.fruitclickinterface = fruitclickinterface;
    }

    private fruitclickinterface fruitclickinterface;
    @NonNull
    @NotNull
    @Override
    public fruitRVadaptor.Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull fruitRVadaptor.Viewholder holder, int position) {
        fruit fruit=fruitArrayList.get(position);
        holder.fruitnameTV.setText(fruit.getFname());
        holder.fruitpriceTV.setText("RS "+fruit.getFprice()+"  Per 250g");
        Picasso.get().load(fruit.getFimglink()).into(holder.fruitimg);



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

        private TextView fruitnameTV,fruitpriceTV;
        private ImageView fruitimg;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            fruitnameTV=itemView.findViewById(R.id.idfname);
            fruitpriceTV=itemView.findViewById(R.id.idfprice);
            fruitimg=itemView.findViewById(R.id.idIVfruit);
        }
    }
}