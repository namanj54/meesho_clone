package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.CATMODEL;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<CATMODEL> catmodelArrayList ;

    public CategoryAdapter(Context context, ArrayList<CATMODEL> catmodelArrayList) {
        this.context = context;
        this.catmodelArrayList = catmodelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat_frag,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CATMODEL catmodel  = catmodelArrayList.get(position);
        holder.iv_cat_img.setImageResource(catmodel.getIv_cat());
        holder.tv_cat_name.setText(catmodel.getTv_cat_name());

        switch(position%5){
            case 0:
                holder.cat_lin.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.gray_color));
                break;
            case 1:
                holder.cat_lin.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.home_bg_color));

                break;
            case 2:
                holder.cat_lin.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.lightpink));

                break;
            case 3:
                holder.cat_lin.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.yellow));

                break;
            case 4:
                holder.cat_lin.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.pink));

                break;
            default:
                holder.cat_lin.setBackgroundColor(ContextCompat.getColor(context,R.color.pink));

                break;

        }
    }

    @Override
    public int getItemCount() {
        return catmodelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cat_name;

        LinearLayout cat_lin;
        ImageView iv_cat_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cat_name = itemView.findViewById(R.id.sub_cat_frg_nme);
            iv_cat_img  = itemView.findViewById(R.id.cat_frg_img);
            cat_lin = itemView.findViewById(R.id.cat_lin);
        }
    }
}
