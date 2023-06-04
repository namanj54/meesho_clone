package com.example.MeeshoApp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.LookingModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

public class LookingAdapter extends RecyclerView.Adapter<LookingAdapter.ViewHolder> {
    Context context;
    ArrayList<LookingModel> list;

    OnAdapterclickListner listner;


    public LookingAdapter(Context context, ArrayList<LookingModel> list, OnAdapterclickListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    public interface  OnAdapterclickListner {
        void itemview(int position);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_looking_for,null);
       return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LookingModel model= list.get(position);
        holder.img1.setImageResource(model.getImageview1());
        holder.img2.setImageResource(model.getImageview2());
        holder.txt1.setText(model.getTextview());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.itemview(position);
            }
        });


        switch(position%5){
            case 0:
                holder.lin_bg.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.home_bg_color));
                break;
                case 1:
                    holder.lin_bg.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.lightpink));

                    break;
            case 2:
                holder.lin_bg.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.yellow));

                    break;
                case 3:
                    holder.lin_bg.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.lightorange));

                    break;
                case 4:
                    holder.lin_bg.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.blue));

                    break;
            default:
                holder.lin_bg.setBackgroundColor(ContextCompat.getColor(context,R.color.primaryColor));

                break;

        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1,img2;
        TextView txt1;
      LinearLayout lin_bg;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);
            txt1=itemView.findViewById(R.id.txt1);
            img2=itemView.findViewById(R.id.img2);
            lin_bg=itemView.findViewById(R.id.lin_bg);


        }
    }


}
