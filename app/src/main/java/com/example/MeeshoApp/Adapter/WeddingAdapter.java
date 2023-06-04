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


import com.example.MeeshoApp.Model.WedModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class WeddingAdapter extends RecyclerView.Adapter<WeddingAdapter.ViewHolder> {

    Context context;

    ArrayList<WedModel> list;

    String type="";


    public WeddingAdapter(Context context, ArrayList<WedModel> list, String type) {
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
        WedModel wedModel = list.get(position);
        holder.iv_wed.setImageResource(wedModel.getIv_wed());
        holder.tv_wed.setText(wedModel.getTv_wed());
        switch(position%5){
            case 0:
                holder.lin_wedcat.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.gray_color));
                break;
            case 1:
                holder.lin_wedcat.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.home_bg_color));

                break;
            case 2:
                holder.lin_wedcat.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.lightpink));

                break;
            case 3:
                holder.lin_wedcat.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.yellow));

                break;
            case 4:
                holder.lin_wedcat.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.pink));

                break;
            default:
                holder.lin_wedcat.setBackgroundColor(ContextCompat.getColor(context,R.color.pink));

                break;

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_wed;
        TextView tv_wed;
        LinearLayout lin_wedcat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_wed =  itemView.findViewById(R.id.iv_wed);
            tv_wed = itemView.findViewById(R.id.wedcat_txt);
            lin_wedcat = itemView.findViewById(R.id.lin_wedcat);

        }
    }
}
