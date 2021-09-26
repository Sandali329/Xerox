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

public class adminviewvegetableRVadaptor extends RecyclerView.Adapter<adminviewvegetableRVadaptor .Viewholder> {

    private ArrayList<vegetable>vegetableArrayList;
    private Context context;
    int laspos=-1;

    public adminviewvegetableRVadaptor (ArrayList<vegetable> vegetableArrayList, Context context, adminviewvegetableRVadaptor .vegetableclickinterface vegetableclickinterface) {
        this.vegetableArrayList = vegetableArrayList;
        this.context = context;
        this.vegetableclickinterface = vegetableclickinterface;
    }

    private vegetableclickinterface vegetableclickinterface;
    @NonNull
    @NotNull
    @Override
    public adminviewvegetableRVadaptor .Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adminviewveg_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adminviewvegetableRVadaptor.Viewholder holder, int position) {
        vegetable vegetable=vegetableArrayList.get(position);
        holder.vegetablenameTV.setText(vegetable.getVname());
        Picasso.get().load(vegetable.getVimglink()).into(holder.vegetableimg);



        setanimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vegetableclickinterface.onvegetableclick(position);
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
        return vegetableArrayList.size();
    }

    public interface vegetableclickinterface{
        void onvegetableclick(int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView vegetablenameTV,vegetablepriceTV;
        private ImageView vegetableimg;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            vegetablenameTV=itemView.findViewById(R.id.idvname);
            vegetablepriceTV=itemView.findViewById(R.id.idvprice);
            vegetableimg=itemView.findViewById(R.id.idIVvegetable);
        }
    }
}
