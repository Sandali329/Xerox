package com.example.xerox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CusAdapter extends FirebaseRecyclerAdapter<CusModel,CusAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CusAdapter(@NonNull @NotNull FirebaseRecyclerOptions<CusModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull CusModel model) {
        holder.CName.setText(model.getItemName());
        holder.CQuantity.setText(model.getCusqty());
        holder.CPrice.setText(model.getCusprice());

        holder.BtnCusEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.CPrice.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cus_update_popup))
                        .setExpanded(true,1200)
                        .create();

               dialogPlus.show();

                View view=dialogPlus.getHolderView();

                EditText Name=view.findViewById(R.id.editItemName);
                EditText Quantity=view.findViewById(R.id.editQuantity);
                EditText Price=view.findViewById(R.id.editItemPrice);

                Button btn_update=view.findViewById(R.id.btn_cus_update);

                Name.setText(model.getItemName());
                Quantity.setText(model.getCusqty());
                Price.setText(model.getCusprice());

                dialogPlus.show();

                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Map<String,Object> map=new HashMap<>();
                         map.put("itemName",Name.getText().toString());
                         map.put("cusqty",Quantity.getText().toString());
                         map.put("cusprice",Price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("CustomerOrder")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.CName.getContext(), "Data updated succesfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.CName.getContext(), "error while updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });



            }
        });

        holder.BtnCusDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.CName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted Data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("CustomerOrder")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.CName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_items,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView CName,CQuantity,CPrice;
        Button BtnCusEdit,BtnCusDelete;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            CName=(TextView)itemView.findViewById(R.id.itemNametxt);
            CQuantity=(TextView)itemView.findViewById(R.id.itemQtytxt);
            CPrice=(TextView)itemView.findViewById(R.id.itemPricetxt);

            BtnCusEdit=(Button)itemView.findViewById(R.id.btnEdit);
            BtnCusDelete=(Button)itemView.findViewById(R.id.btnDelete);

        }
    }
}
