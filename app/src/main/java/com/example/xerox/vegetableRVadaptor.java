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

public class vegetableRVadaptor extends RecyclerView.Adapter<vegetableRVadaptor .Viewholder> {

    private ArrayList<vegetable>vegetableArrayList;
    private Context context;
    int laspos=-1;

    public vegetableRVadaptor (ArrayList<vegetable> vegetableArrayList, Context context, vegetableRVadaptor .vegetableclickinterface vegetableclickinterface) {
        this.vegetableArrayList = vegetableArrayList;
        this.context = context;
        this.vegetableclickinterface = vegetableclickinterface;
    }

    private vegetableclickinterface vegetableclickinterface;
    @NonNull
    @NotNull
    @Override
    public vegetableRVadaptor .Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.vegetable_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull vegetableRVadaptor.Viewholder holder, int position) {
        vegetable vegetable=vegetableArrayList.get(position);
        holder.vegetablenameTV.setText(vegetable.getVname());
        holder.vegetablepriceTV.setText("RS "+vegetable.getVprice()+"  Per 250g");
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
