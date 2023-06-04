package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.AccountModel;
import com.example.MeeshoApp.Model.MyorderModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyorderAdapter extends RecyclerView.Adapter<MyorderAdapter.ViewHolder> {

    Context context;

    ArrayList<HashMap<String,String>> myorderModels;

    public MyorderAdapter(Context context, ArrayList<HashMap<String,String>>  myorderModels) {
        this.context = context;
        this.myorderModels = myorderModels;
    }

    @NonNull
    @Override
    public MyorderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myorder,null);
        return new MyorderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyorderAdapter.ViewHolder holder, int position) {
        HashMap<String,String> cmap = myorderModels.get(position);
        holder.iv_prod_image.setImageResource(Integer.parseInt(cmap.get("order_image")));
        holder.tv_ordernme.setText(cmap.get("order_name"));
        holder.tv_orderid.setText("Product id: "+cmap.get("order_id"));
        holder.tv_supplier.setText("supplier: "+cmap.get("order_supplier"));
        holder.tv_sold.setText("Sold to:"+cmap.get("order_sold"));
        holder.tv_orderdt.setText("Date:"+cmap.get("order_date"));

    }

    @Override
    public int getItemCount() {
        return myorderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_orderdt,tv_orderid,tv_sold,tv_supplier,tv_ordernme;
        ImageView iv_prod_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_prod_image = itemView.findViewById(R.id.order_img);
            tv_orderdt = itemView.findViewById(R.id.order_dte);
            tv_orderid = itemView.findViewById(R.id.order_id);
            tv_sold = itemView.findViewById(R.id.sold_to);
            tv_supplier = itemView.findViewById(R.id.supplier);
            tv_ordernme = itemView.findViewById(R.id.order_nme);

        }
    }
}
