package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.MenModel;

import com.example.MeeshoApp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Men_catAdapter extends RecyclerView.Adapter<Men_catAdapter.ViewHolder> {
    Context context;

    ArrayList<MenModel> menModels;

    String type ="";

    public Men_catAdapter(Context context, ArrayList<MenModel> menModels, String type) {
        this.context = context;
        this.menModels = menModels;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mencat,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenModel model  = menModels.get(position);
        Picasso.get ( ).load (model.getMen_catimage ( )).placeholder (R.drawable.logo).into (holder.iv_men);
        holder.men_cattext.setText(model.getMen_catext());



    }

    @Override
    public int getItemCount() {
       return menModels.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_men;
        TextView men_cattext;
        LinearLayout ln_mn;
        public ViewHolder(View view) {
            super(view);
            iv_men = view.findViewById(R.id.iv_men);
            men_cattext = view.findViewById(R.id.mencat_txt);
            ln_mn = view.findViewById(R.id.lin_mencat);


        }
    }
}





