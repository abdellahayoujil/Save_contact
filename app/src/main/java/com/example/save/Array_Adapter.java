package com.example.save;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Array_Adapter extends RecyclerView.Adapter<Array_Adapter.Viewholder> {

    ArrayList<Class_save> save_ArrayList = new ArrayList<>();
    Context context;

    public Array_Adapter(ArrayList<Class_save> Ar_ArrayList, Context context) {
        this.save_ArrayList = Ar_ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Array_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.save_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Array_Adapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {


        holder.name.setText(save_ArrayList.get(position).getName());
        holder.number.setText(save_ArrayList.get(position).getNumber());
        holder.email.setText(save_ArrayList.get(position).getEmail());
        holder.adresse.setText(save_ArrayList.get(position).getAdresse());


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);

                EditText customName= dialog.findViewById(R.id.customName);
                EditText customNumber= dialog.findViewById(R.id.customNumber);
                EditText customEmail= dialog.findViewById(R.id.customEmail);
                EditText customAdresse= dialog.findViewById(R.id.customAdresse);
                Button customBtn = dialog.findViewById(R.id.customBtn);



                customName.setText(save_ArrayList.get(position).getName());
                customNumber.setText(save_ArrayList.get(position).getNumber());
                customEmail.setText(save_ArrayList.get(position).getEmail());
                customAdresse.setText(save_ArrayList.get(position).getAdresse());

                String iD = String.valueOf(save_ArrayList.get(position).getId());



                customBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Db_save db_save = new Db_save(context);
                        if(customName.length()>0){
                            db_save.updateData(customName.getText().toString(),customNumber.getText().toString(),customEmail.getText().toString(),customAdresse.getText().toString(),iD);
                            Toast.makeText(context,"successfully Updated!",Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context,Recyclerview.class));

                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Enter at least the name!",Toast.LENGTH_SHORT).show();

                        }




                    }
                });




                dialog.show();




            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Db_save lemonHelper = new Db_save(context);
                Cursor cursor = lemonHelper.showData();

                String iD = String.valueOf(save_ArrayList.get(position).getId());






                new AlertDialog.Builder(context)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation

                                lemonHelper.deleteData(iD);
                                context.startActivity(new Intent(context,Recyclerview.class));
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();








            }
        });


    }

    @Override
    public int getItemCount() {
        return save_ArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView name,number,email,adresse;
        TextView delete,update;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            number = itemView.findViewById(R.id.tvNumber);
            email =itemView.findViewById(R.id.tvEmail);
            adresse =itemView.findViewById(R.id.tvAd);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);







        }
    }
}
