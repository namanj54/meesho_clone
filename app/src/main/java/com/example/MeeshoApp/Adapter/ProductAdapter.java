package com.example.MeeshoApp.Adapter;


import static com.example.MeeshoApp.common.constant.PRODUCT_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_NAME_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.PRO_ID;
import static com.example.MeeshoApp.common.constant.PRO_IMAGE;
import static com.example.MeeshoApp.common.constant.PRO_NAME;
import static com.example.MeeshoApp.common.constant.PRO_OFFERS;
import static com.example.MeeshoApp.common.constant.PRO_PRICE;
import static com.example.MeeshoApp.common.constant.WISH_COLOUMN;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.Databases.Wishlist_database;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.Model.WishlistModel;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;


import java.util.ArrayList;
import java.util.HashMap;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

 Context context;
 ArrayList<ProductModel> List;
 Wishlist_database wishlist_database;

 SessionManagement sessionManagement;


 onAdapterclicklister click;

    public interface onAdapterclicklister {
        void itemview(int position);

    }

    public ProductAdapter(Context context, ArrayList<ProductModel> list, onAdapterclicklister click) {
        this.context = context;
        List = list;
        this.click = click;
        wishlist_database= new Wishlist_database(context) ;
        sessionManagement = new SessionManagement(context);
 }
    public void filterList(ArrayList<ProductModel> filterlist) {
        List = filterlist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_products,null);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductModel model3 = List.get(position);


        holder.prod1.setImageResource(model3.getIv_product());
        holder.prod_name.setText(model3.getProduct_name());
        holder.price.setText("₹"+ model3.getPrice());
        holder.offers.setText("₹"+model3.getOffers());
        holder.free.setText(model3.getDeliver_status());
        holder.rating.setText(model3.getRating());


    holder.lin_products.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        click.itemview(position);

    }
});

        holder.lin_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.iv_no_wish.getVisibility() == View.VISIBLE) {
                    wishlist_database.removewish(WISH_COLOUMN);
                    Toast.makeText(context, "Item removed from your wishlist", Toast.LENGTH_SHORT).show();

                        holder.iv_no_wish.setVisibility(View.GONE);
                        holder.iv_wish.setVisibility(View.VISIBLE);
                        wish_visiblity();
                }
                else {
                    HashMap<String, String> map  = new HashMap<>();
                    map.put(WISH_COLOUMN,String.valueOf(model3.getId()));
                    map.put(PRODUCT_IMAGE_KEY,String.valueOf(model3.getIv_product()));
                    map.put(PRODUCT_NAME_KEY,holder.prod_name.getText().toString().trim());
                    map.put(PRODUCT_PRICE_KEY,holder.price.getText().toString().trim());
                    map.put(PRODUCT_OFFERS_KEY,holder.offers.getText().toString().trim());
                    wishlist_database.insertwishData(map);
                    Log.e("map_productAdap", "onClick: "+map);
                    holder.iv_no_wish.setVisibility(View.VISIBLE);
                    holder.iv_wish.setVisibility(View.GONE);
                    MainActivity.check_wish_data(Integer.parseInt(String.valueOf(model3.getId())));
                    wish_visiblity();
                    Toast.makeText(context, "Item added to your wishlist", Toast.LENGTH_SHORT).show();


                }
            }


        });


        if (model3.getStatus().equalsIgnoreCase("0")){
            holder.lin_status.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.green)));
        }
        else if (model3.getStatus().equalsIgnoreCase("1")) {
            holder.lin_status.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.yellow)));

        } else if (model3.getStatus().equalsIgnoreCase("2")) {
            holder.lin_status.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.red)));


        }
        else {
            holder.lin_status.setBackgroundColor(context.getColor(R.color.green));

        }

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView prod1,iv_wish,iv_no_wish;
        TextView prod_name,offers,price,rating,free;
        LinearLayout lin_status,lin_wish;
        LinearLayout lin_products;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prod1 = itemView.findViewById(R.id.prod1);
            prod_name = itemView.findViewById(R.id.prod_name);
            offers = itemView.findViewById(R.id.offers);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            free = itemView.findViewById(R.id.free);
            lin_status = itemView.findViewById(R.id.lin_status);
            lin_wish = itemView.findViewById(R.id.lin_wish);
            iv_wish = itemView.findViewById(R.id.iv_wish);
            iv_no_wish = itemView.findViewById(R.id.iv_no_wish);
            lin_products = itemView.findViewById(R.id.lin_products);

        }
    }

    public void wish_visiblity(){
        MainActivity.wish_count();
    }



}
