package com.example.MeeshoApp.Fragment;


import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.CITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.LOCALITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.PINCODE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_NAME_KEY;
import static com.example.MeeshoApp.common.constant.PRO_ID;
import static com.example.MeeshoApp.common.constant.PRO_QUANTITY;
import static com.example.MeeshoApp.common.constant.STATE_LIVE_KEY;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductViewFragment extends Fragment {


    ProductModel productModel;
    EditText add;
    String pro_qty;
    HashMap<String,String> quantity_set = new HashMap<>();
    ArrayList<HashMap<String, String>> cart_set = new ArrayList<>();

   ImageView iv_prodview;
   LinearLayout status_rat;

   Cart_database cart_database;

   ElegantNumberButton quant_pv_btn;
   SessionManagement sessionManagement;

   Product_database product_database;
   Button prod_ad_btn,move_to_cart,buy_now;
   TextView tv_prodname,tv_prodprice,tv_prodoffers,tv_proddel,tv_prodrat,pin_check,pro_id,tv_location;
   String q_id,qty="",prodname=" ",prodprice="",prodoffers="",proddel="",prodrat="",prodimg="",prodsatus="";


    ArrayList<HashMap<String, String>> cartqty = new ArrayList<>();



    public ProductViewFragment() {

    }

    public static ProductViewFragment newInstance(String param1, String param2) {
        ProductViewFragment fragment = new ProductViewFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_view, container, false);
        getidview(view);
        intialid(view);
        return view;
    }

    private void getidview(View view) {
        prodimg=getArguments().getString("pro_image");
        prodname=getArguments().getString("pro_name");
        prodprice=getArguments().getString("getPrice");
        prodoffers=getArguments().getString("Offers");
        proddel=getArguments().getString("delivery");
        prodrat=getArguments().getString("rating");
        prodsatus=getArguments().getString("status");
        qty = getArguments().getString("pros_quanntiyt");
    }
    private void intialid(View view) {
        cart_database = new Cart_database(getActivity());
        product_database = new Product_database(getActivity());
        iv_prodview = view.findViewById(R.id.prodview1);
        iv_prodview.setImageResource(Integer.parseInt(prodimg));
        tv_prodname = view.findViewById(R.id.prodview_name);
        tv_prodname.setText(prodname);
        tv_prodprice = view.findViewById(R.id.prod_price);
        tv_prodprice.setText("₹"+prodprice);
        tv_prodoffers = view.findViewById(R.id.prod_offers);
        tv_prodoffers.setText("₹"+prodoffers);
        tv_prodrat = view.findViewById(R.id.prod_rating);
        tv_prodrat.setText(prodrat);
        tv_proddel = view.findViewById(R.id.prod_free);
        tv_proddel.setText(proddel);
        status_rat = view.findViewById(R.id.lin_status_prodview);
        pin_check = view.findViewById(R.id.prod_view_check);
        pro_id = view.findViewById(R.id.pro_id);
        quant_pv_btn = view.findViewById(R.id.addto_cart);
        buy_now = view.findViewById(R.id.buy_now);
        tv_location = view.findViewById(R.id.tv_location);
        sessionManagement = new SessionManagement(getActivity());
        quant_pv_btn.setNumber(qty);
        set_live_data();



        if (prodsatus.equalsIgnoreCase("0")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                status_rat.setBackgroundTintList(ColorStateList.valueOf(getActivity().getColor(R.color.green)));
            }
        }
        else if (prodsatus.equalsIgnoreCase("1")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                status_rat.setBackgroundTintList(ColorStateList.valueOf(getActivity().getColor(R.color.yellow)));
            }

        } else if (prodsatus.equalsIgnoreCase("2")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                status_rat.setBackgroundTintList(ColorStateList.valueOf(getActivity().getColor(R.color.red)));
            }

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                status_rat.setBackgroundColor(getActivity().getColor(R.color.green));
            }

        }

     quant_pv_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ShowBottomshettDailog();

         }
     });
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
                counter_update_quantity();
                CartFragment cartFragment = new CartFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,cartFragment).commit();
            }
        });



    }
    private void set_live_data(){
        if (sessionManagement.getdata().get(LOCALITY_LIVE_KEY) != null && sessionManagement.getdata().get(CITY_LIVE_KEY) != null && sessionManagement.getdata().get(PINCODE_LIVE_KEY) != null && sessionManagement.getdata().get(STATE_LIVE_KEY) != null ){
            tv_location.setText("Delivering to "+sessionManagement.getdata().get(LOCALITY_LIVE_KEY).toString()+","+sessionManagement.getdata().get(CITY_LIVE_KEY).toString()+","+sessionManagement.getdata().get(PINCODE_LIVE_KEY).toString()+","+sessionManagement.getdata().get(STATE_LIVE_KEY).toString());
        }
    }

    private void ShowBottomshettDailog() {
        BottomSheetDialog qaunt = new BottomSheetDialog(getActivity(),R.style.CustomBottomSheetDialog); // Style here
        qaunt.setContentView(R.layout.quantity_bottom_sheet);
        qaunt.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        qaunt.setContentView(R.layout.quantity_bottom_sheet);
        move_to_cart = qaunt.findViewById(R.id.mov_to_cart);
        prod_ad_btn = qaunt.findViewById(R.id.added);
        prod_ad_btn.setText("ADDED +"+quant_pv_btn.getNumber());
        qaunt.show();




        move_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
                setquantity();
                counter_update_quantity();
                qaunt.dismiss();
               CartFragment cartFragment = new CartFragment();
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,cartFragment).addToBackStack(null).commit();

            }


        });




    }

    public void counter_update_quantity() {
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

    private void setquantity() {
       String id =  String.valueOf(getArguments().getString("id"));
       cart_database.updte_pr_quantity(id,quant_pv_btn.getNumber());
        Log.e("xsss", "setquantity: "+id);
       product_database.update_quantity(id,quant_pv_btn.getNumber());
        Log.e("xzmsmxz", "setquantity: "+CART_COLUMN_KEY );
    }

    private TextView createNewTextView(String text) {
        TextView textView = new TextView(getActivity());
        textView.setText((CharSequence) prod_ad_btn);
        textView.setText("New text: " + PRODUCT_NAME_KEY);
        return textView;
        
    }


    private void showdialog() {
        Dialog quantity = new Dialog(getActivity());
        quantity.getWindow();
        quantity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        quantity.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        quantity.getWindow().setGravity(Gravity.END);
        quantity.show();
        quantity.setCancelable(false);
        quantity.setContentView(R.layout.dialog_logout);
        Button yes_logout = quantity.findViewById(R.id.yes_logout);
        Button no_logout = quantity.findViewById(R.id.no_logout);
        quantity.setCanceledOnTouchOutside(false);

    }
    @SuppressLint("SuspiciousIndentation")

    private void insertdata() {

        HashMap<String, String> cartdata  = new HashMap<>();
        Log.e("image", "insertdata: "+String.valueOf(getArguments().getString("pro_image")));
        cartdata.put(CART_COLUMN_KEY,String.valueOf(getArguments().getString("id")));
        cartdata.put(CART_IMAGE_KEY,String.valueOf(getArguments().getString("pro_image")));
        cartdata.put(CART_NAME_KEY,tv_prodname.getText().toString().trim());
        cartdata.put(CART_PRICE_KEY,tv_prodprice.getText().toString().trim());
        cartdata.put(CART_OFFERS_KEY,tv_prodoffers.getText().toString().trim());
        cartdata.put(CART_QUANTITY_KEY,String.valueOf(quant_pv_btn.getNumber()));
        cart_database.insertcartData(cartdata);
        Log.e("cartdata", "insertdata: "+cartdata);
        Toast.makeText(getActivity(), "Item added", Toast.LENGTH_SHORT).show();



    }


    }
