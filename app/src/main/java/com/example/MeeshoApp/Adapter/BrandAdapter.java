package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.BrandModel;
import com.example.MeeshoApp.R;


import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    Context context;
    ArrayList<BrandModel> list;

    String type="";

    public BrandAdapter(Context context, ArrayList<BrandModel> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wed,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrandModel brandModel = list.get(position);
        holder.iv_brand.setImageResource(brandModel.getIv_brand());
        holder.tv_brand.setText(brandModel.getTv_brand());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_brand;
        TextView tv_brand;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            iv_brand = itemView.findViewById(R.id.iv_wed);
            tv_brand = itemView.findViewById(R.id.wedcat_txt);

        }
    }
}
