package com.example.MeeshoApp.Adapter;



import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.Fragment.ProductViewFragment;
import com.example.MeeshoApp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

String up_num;

  ElegantNumberButton up_quant;
    Button add;
    Context context;

    ArrayList<HashMap<String,String>> cartlist;

    OnAdapterclickListner listner;
    ArrayList<HashMap<String, String>> cartqty = new ArrayList<>();

    Product_database product_database;



    public interface OnAdapterclickListner {
        void onItemClick( int position, ArrayList<HashMap<String,String>> cartlist);

    }



    Cart_database cart_database;

    public CartAdapter(Context context, ArrayList<HashMap<String, String>> cartlist,OnAdapterclickListner listner ) {

        this.context = context;
        this.cartlist = cartlist;
        this.listner =  listner;
        cart_database = new Cart_database(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_cart,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        HashMap<String,String> cmap = cartlist.get(position);
        Log.e("image_cart", "onBindViewHolder: "+cmap.get("cart_image"));
        holder.iv_cart.setImageResource(Integer.parseInt(cmap.get("cart_image")));
        holder.tv_cart_name.setText(cmap.get("cart_name"));
        holder.tv_cart_price.setText(cmap.get("cart_price"));
        holder.quan_cart.setText("Qty: ×"+cmap.get("cart_quantity"));

        holder.quan_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet(holder.quan_cart,cmap);

            }
        });


        holder.cart_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //cart_database.removefromcart(CART_COLUMN_KEY);
                listner.onItemClick(position,cartlist);

//                cart_database.removefromcart(String.valueOf(cartlist.get(position).get(CART_COLUMN_KEY)));
//                cartlist.remove(position);
//                notifyDataSetChanged();


                Toast.makeText(context, "item removed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openbottomsheet(TextView tv_qnt,HashMap<String,String> map) {

        BottomSheetDialog qaunt = new BottomSheetDialog(context,R.style.CustomBottomSheetDialog); // Style here
        qaunt.setContentView(R.layout.quant_cart_bottom);
        qaunt.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        qaunt.setContentView(R.layout.quant_cart_bottom);
        add = qaunt.findViewById(R.id.add_up_quan);
        up_quant = qaunt.findViewById(R.id.more_quan);
        up_quant.setNumber(map.get("cart_quantity"));
        qaunt.show();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("cart_quantity",String.valueOf(up_quant.getNumber()));
                Log.e("column_id",map.get("column_id"));
                cart_database.updateCart(map.get("column_id"),String.valueOf(up_quant.getNumber()));
                product_database = new Product_database(context);
                product_database.update_quantity(map.get("column_id"),String.valueOf(up_quant.getNumber()));
                notifyDataSetChanged();
                update_counter_badge();
                Log.d("cart_database", "onClick: "+cart_database.getcartitem());
                Log.e("up_quant",up_quant.getNumber());
                Log.e("tv_qnt",tv_qnt.getText().toString());
                qaunt.dismiss();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_cart;
        TextView tv_cart_name, tv_cart_price, cart_remove, quan_cart;

        LinearLayout lin_cart_wish, lin_cart_tt, lin_cart_btn,lin_prod;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cart = itemView.findViewById(R.id.cart_iv);
            tv_cart_name = itemView.findViewById(R.id.cart_tv);
            tv_cart_price = itemView.findViewById(R.id.cart_price);
            cart_remove = itemView.findViewById(R.id.cart_remove);
            lin_cart_wish = itemView.findViewById(R.id.lin_cart_wish);
            lin_cart_tt = itemView.findViewById(R.id.lin_cart_tt1);
            lin_cart_btn = itemView.findViewById(R.id.lin_cart_btn1);
            quan_cart = itemView.findViewById(R.id.cart_quantity);
            lin_prod = itemView.findViewById(R.id.lin_cart_click);


        }
    }
    private void update_counter_badge(){
        int qty1 =0;
        if (cart_database != null) {
            cartqty = cart_database.getcartitem();
            if (cartqty.size() > 0) {

                for (int i = 0; i < cartqty.size(); i++) {
                    qty1 += Integer.parseInt(cartqty.get(i).get(CART_QUANTITY_KEY));
                }
            }
        }
        MainActivity.setupBadge(qty1);
    }
        }

//

