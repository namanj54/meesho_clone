package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.CAT2Model;

import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class Category2Adapter extends RecyclerView.Adapter<Category2Adapter.ViewHolder> {

    Context context;
    ArrayList<CAT2Model> cat2ModelArrayList;

    public Category2Adapter(Context context, ArrayList<CAT2Model> cat2ModelArrayList) {
        this.context = context;
        this.cat2ModelArrayList = cat2ModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_cat_rec_2,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category2Adapter.ViewHolder holder, int position) {
        CAT2Model cat2Model  = cat2ModelArrayList.get(position);
        holder.iv_cat_2_img.setImageResource(cat2Model.getIv_cat_2());
        holder.tv_cat_2_name.setText(cat2Model.getTv_cat_2_name());
    }

    @Override
    public int getItemCount() {
        return cat2ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cat_2_name;

        ImageView iv_cat_2_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cat_2_name = itemView.findViewById(R.id.tv_cat_2);
            iv_cat_2_img  = itemView.findViewById(R.id.iv_cat_2);

        }
    }
}
