package com.example.xerox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>{

    ArrayList<Model> mList;
    Context context;

    public myAdapter(Context context,ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false );
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  myAdapter.myViewHolder holder, int position) {
        Model model=mList.get(position);
        holder.name.setText(model.getName());
        holder.contact.setText(model.getContact());
        holder.email.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,contact,email;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name_text);
            contact=itemView.findViewById(R.id.contact_text);
            email=itemView.findViewById(R.id.email_text);
        }
    }
}
