package com.example.MeeshoApp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.AccountModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    Context Context;
    ArrayList<AccountModel> List;
    OnAdapterclickListner  listner;
    public interface OnAdapterclickListner {
         void onItemClick( int position);
         void onDeleteClick( int position);
         void onEditClick( int position);


    }

    public AccountAdapter(android.content.Context context, ArrayList<AccountModel> list, OnAdapterclickListner listner) {
        Context = context;
        List = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Context).inflate(R.layout.item_account,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AccountModel model4 = List.get(position);
        holder.acc_img.setImageResource(model4.getImageview5());
        holder.acc_txt.setText(model4.getTextview8());
        holder.lin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView acc_img;
        TextView acc_txt;
        LinearLayout lin_main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            acc_img = itemView.findViewById(R.id.acc_img);
            acc_txt = itemView.findViewById(R.id.acc_txt);
            lin_main = itemView.findViewById(R.id.lin_main);
        }
    }
}


