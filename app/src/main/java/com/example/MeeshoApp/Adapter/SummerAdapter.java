package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.SummerModel;
import com.example.MeeshoApp.R;


import java.util.ArrayList;

public class SummerAdapter extends RecyclerView.Adapter<SummerAdapter.ViewHolder> {

    Context context;
    ArrayList<SummerModel> list;

    String type="";

    public SummerAdapter(Context context, ArrayList<SummerModel> list, String type) {
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
        SummerModel summerModel = list.get(position);
        holder.iv_sum.setImageResource(summerModel.getIv_sum());
        holder.tv_sum.setText(summerModel.getTv_sum());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_sum;
        TextView tv_sum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           iv_sum = itemView.findViewById(R.id.iv_wed);
           tv_sum = itemView.findViewById(R.id.wedcat_txt);

        }
    }
}
