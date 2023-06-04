package com.example.MeeshoApp.Fragment;

import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Adapter.CartAdapter;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.R;


import java.util.ArrayList;
import java.util.HashMap;


public class CartFragment extends Fragment {
 ArrayList<HashMap<String, String>> cartmap = new ArrayList<>();
 RecyclerView cart_rec;



  Cart_database cart_database;

  Product_database product_database;
  CartAdapter cartAdapter;
  LinearLayout lin_cart_wish, lin_cart_tt, lin_cart_btn;

    ImageView empty;
    TextView cart_item_price,cart_tt_price,cart_show_price;

    String qty,pro_id;

    Button con_btn;

    ArrayList<HashMap<String, String>> cartqty = new ArrayList<>();




    public CartFragment() {

    }


    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        intialid(view);
        return view;


    }


    private void intialid(View view) {


        cart_database = new Cart_database(getActivity());
        product_database = new Product_database(getActivity());
        empty = view.findViewById(R.id.emp);
        cart_item_price = view.findViewById(R.id.prod_price);
        lin_cart_wish = view.findViewById(R.id.lin_cart_wish);
        lin_cart_tt = view.findViewById(R.id.lin_cart_tt1);
        lin_cart_btn = view.findViewById(R.id.lin_cart_btn1);
        cart_tt_price = view.findViewById(R.id.order_total_price);
        cart_show_price = view.findViewById(R.id.order_show_price);
        con_btn = view.findViewById(R.id.buy_now);
        con_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressFragment addressFragment = new AddressFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,addressFragment).commit();

            }
        });

        cart_rec = view.findViewById(R.id.cart_rec);
        cart_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getprice();


    }

    private void getprice() {
        int price=0;
        Integer total=0;
        if (cart_database!=null) {
            cartmap = cart_database.getcartitem();
            if (cartmap.size() > 0) {

                for (int i = 0; i < cartmap.size(); i++) {
                    qty = cartmap.get(i).get(CART_QUANTITY_KEY);
                    pro_id = cartmap.get(i).get(CART_COLUMN_KEY);
                    String price1 = cartmap.get(i).get(CART_PRICE_KEY).replace("₹", "");
                     total = Integer.parseInt(price1) * Integer.parseInt(qty);
                    price = price + total;
                }
                cart_item_price.setText("₹" + String.valueOf(price));
                cart_tt_price.setText("₹" + String.valueOf(price));
                cart_show_price.setText("₹" + String.valueOf(price));
                Log.e("bhsbbha", "intialid: " + String.valueOf(price));

                getcartrecview();


            }
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
    private void getcartrecview() {

        Log.d("getdata", "getcartrecview:" + cartmap);
        Log.e("cart_zize", "getcartrecview: " + cartmap.size());

        if (cartmap.size() > 0) {
            cartAdapter = new CartAdapter(getActivity(), cartmap, new CartAdapter.OnAdapterclickListner() {
                @Override
                public void onItemClick(int position, ArrayList<HashMap<String, String>> cartlist) {
                    HashMap<String,String> cmap = cartlist.get(position);
                    String remove = cmap.get("cart_name");
                    Log.e("abxdhhsxbb", "onItemClick: "+remove);
                    cart_database.removefromcart(String.valueOf(cartlist.get(position).get(CART_COLUMN_KEY)));
                    product_database.update_quantity(String.valueOf(cartlist.get(position).get(CART_COLUMN_KEY)), "0");
                    cartAdapter.notifyDataSetChanged();
                    cartmap.remove(position);
                    update_counter_badge();
                    if (cartmap.isEmpty()){
                        cart_rec.setVisibility(View.GONE);
                                lin_cart_wish.setVisibility(View.GONE);
                                lin_cart_tt.setVisibility(View.GONE);
                                lin_cart_btn.setVisibility(View.GONE);
                                empty.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), "No items in cart", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        getprice();
                    }

                }
            });
            cart_rec.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
            cart_database.updateCart(pro_id,qty);
            cart_rec.setVisibility(View.VISIBLE);
            lin_cart_wish.setVisibility(View.VISIBLE);
            lin_cart_tt.setVisibility(View.VISIBLE);
            lin_cart_btn.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }



    }

}