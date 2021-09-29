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

public class buyvegRVadaptor extends RecyclerView.Adapter<buyvegRVadaptor.Viewholder> {

    private ArrayList<vegetable>vegetableArrayList;
    private Context context;
    int laspos=-1;

    public buyvegRVadaptor(ArrayList<vegetable> vegetableArrayList, Context context, buyvegRVadaptor.vegetableclickinterface vegetableclickinterface) {
        this.vegetableArrayList = vegetableArrayList;
        this.context = context;
        this.vegetableclickinterface = vegetableclickinterface;
    }

    private vegetableclickinterface vegetableclickinterface;
    @NonNull
    @NotNull
    @Override
    public buyvegRVadaptor.Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.buyvegetable_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull buyvegRVadaptor.Viewholder holder, int position) {
        vegetable vegetable=vegetableArrayList.get(position);
        holder.vegnameTV.setText(vegetable.getVname());
        holder.vegcuspriceTV.setText("RS "+vegetable.getVcusprice()+"  Per 250g");
        Picasso.get().load(vegetable.getVimglink()).into(holder.vegimg);

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

        private TextView vegnameTV,vegcuspriceTV;
        private ImageView vegimg;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            vegnameTV=itemView.findViewById(R.id.idvname);
            vegcuspriceTV=itemView.findViewById(R.id.idvcusprice);
            vegimg=itemView.findViewById(R.id.idIVvegetable);
        }
    }
}
