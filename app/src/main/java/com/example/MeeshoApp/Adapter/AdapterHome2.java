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

import com.example.MeeshoApp.Model.HomeModel2;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class AdapterHome2 extends RecyclerView.Adapter<AdapterHome2.ViewHolder> {

    Context Context;
    ArrayList<HomeModel2> List;

    public AdapterHome2(android.content.Context context, ArrayList<HomeModel2> list) {
        Context = context;
        List = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Context).inflate(R.layout.item_home2,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel2 model2 = List.get(position);
        holder.bot1.setImageResource(model2.getImageview3());
        holder.txt2.setText(model2.getTextview2());

        switch(position%5){
            case 0:
                holder.lin_lo.setBackgroundTintList(ContextCompat.getColorStateList(Context,R.color.gray_color));
                break;
            case 1:
                holder.lin_lo.setBackgroundTintList(ContextCompat.getColorStateList(Context,R.color.home_bg_color));

                break;
            case 2:
                holder.lin_lo.setBackgroundTintList(ContextCompat.getColorStateList(Context,R.color.lightpink));

                break;
            case 3:
                holder.lin_lo.setBackgroundTintList(ContextCompat.getColorStateList(Context,R.color.yellow));

                break;
            case 4:
                holder.lin_lo.setBackgroundTintList(ContextCompat.getColorStateList(Context,R.color.pink));

                break;
            default:
                holder.lin_lo.setBackgroundColor(ContextCompat.getColor(Context,R.color.pink));

                break;

        }

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView bot1;
        TextView txt2;
        LinearLayout lin_lo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bot1 = itemView.findViewById(R.id.bot);
            txt2 = itemView.findViewById(R.id.bot1);
            lin_lo = itemView.findViewById(R.id.lin_lo);
        }
    }
}
