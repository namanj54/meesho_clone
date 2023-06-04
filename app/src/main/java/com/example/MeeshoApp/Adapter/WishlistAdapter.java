package com.example.MeeshoApp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Databases.Wishlist_database;
import com.example.MeeshoApp.R;


import java.util.ArrayList;
import java.util.HashMap;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {


    Context context;
    ArrayList<HashMap<String,String>> wishlistModels;
    Wishlist_database db_wish;

  OnAdapterclickListner listner;


    public interface OnAdapterclickListner {
        void onItemClick( int position, ArrayList<HashMap<String,String>> wishlistModels );

    }


    public WishlistAdapter(Context context, ArrayList<HashMap<String, String>> wishlistModels,OnAdapterclickListner listner) {
        this.context = context;
        this.wishlistModels = wishlistModels;
        this.listner = listner;
        db_wish= new Wishlist_database(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items_wishlist,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     HashMap<String,String> map = wishlistModels.get(position);
       holder.wish_iv.setImageResource(Integer.parseInt(map.get("wish_image")));
        holder.wish_tv.setText(map.get("wish_name"));
        holder.wish_price.setText(map.get("wish_price"));
        holder.wish_offers.setText(map.get("wish_offer"));

        holder.wish_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClick(position,wishlistModels);
                Toast.makeText(context, "itemremoved", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return wishlistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wish_iv,wish_remove,iv_empty;
        TextView wish_tv,wish_price,wish_offers;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wish_iv = itemView.findViewById(R.id.prod1);
            wish_tv = itemView.findViewById(R.id.prod_name);
            wish_price = itemView.findViewById(R.id.price);
            wish_offers = itemView.findViewById(R.id.offers);
            wish_remove = itemView.findViewById(R.id.remove);


        }
    }

}
