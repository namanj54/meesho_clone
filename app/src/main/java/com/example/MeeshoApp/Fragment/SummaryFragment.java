package com.example.MeeshoApp.Fragment;

import static com.example.MeeshoApp.common.constant.ADD_USER;
import static com.example.MeeshoApp.common.constant.ADD_USERCITY;
import static com.example.MeeshoApp.common.constant.ADD_USERHOUSENO;
import static com.example.MeeshoApp.common.constant.ADD_USERPHONE;
import static com.example.MeeshoApp.common.constant.ADD_USERPIN;
import static com.example.MeeshoApp.common.constant.ADD_USERSTATE;
import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.MY_ORDER_DATE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_ID;
import static com.example.MeeshoApp.common.constant.MY_ORDER_IMAGE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_NAME;
import static com.example.MeeshoApp.common.constant.MY_ORDER_PRICE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_Quantity;
import static com.example.MeeshoApp.common.constant.MY_ORDER_SOLDTO;
import static com.example.MeeshoApp.common.constant.MY_ORDER_SUPPLIER;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Adapter.CartAdapter;
import com.example.MeeshoApp.Databases.Address_database;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Databases.My_orderdatabse;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {

    ArrayList<HashMap<String, String>> summmap = new ArrayList<>();

    ArrayList<HashMap<String, String>> addmap = new ArrayList<>();
    RecyclerView sumry_rec;
    CartAdapter cartAdapter;
    Address_database address_database;
    TextView add,edit,summ_prod_price,summ_pro,final_price;
     String name,phone,hno,city,state,pin;

     String order_remove,order_name,orderimg,order_id;

     Button make_pay;
     int price;
     SessionManagement sessionManagement;

    Cart_database  cart_database;
    My_orderdatabse my_orderdatabse;

    NotificationCompat.Builder mBuilder;

    NotificationManager manager;

    Vibrator vibrator;
    public SummaryFragment() {

    }


    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
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
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        initid(view);
        return view;
    }

    private void initid(View view) {
       mBuilder = new NotificationCompat.Builder(getActivity());
        add = view.findViewById(R.id.summary_add);
        edit = view.findViewById(R.id.edit_summ);
        make_pay = view.findViewById(R.id.pay_con_btn);
        address_database = new Address_database(getActivity());
        addmap = address_database.getaddress();
        for (int i=0;i<addmap.size();i++){
             name = addmap.get(i).get(ADD_USER);
             phone = addmap.get(i).get(ADD_USERPHONE);
             hno = addmap.get(i).get(ADD_USERHOUSENO);
             state = addmap.get(i).get(ADD_USERSTATE);
             city = addmap.get(i).get(ADD_USERCITY);
             pin = addmap.get(i).get(ADD_USERPIN);
        }
        add.setText("Name "+name+","+"+91"+phone+","+hno+","+state+","+city+","+pin);

        cart_database = new Cart_database(getActivity());
        summmap = cart_database.getcartitem();
        my_orderdatabse = new My_orderdatabse(getActivity());
        make_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
                My_orderFragment my_orderFragment = new My_orderFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,my_orderFragment).commit();

            }
        });




        if (summmap.size() > 0) {

            for (int i = 0; i < summmap.size(); i++) {
                order_name = summmap.get(i).get(CART_NAME_KEY);
                orderimg = summmap.get(i).get(CART_IMAGE_KEY);
                order_id = summmap.get(i).get(CART_COLUMN_KEY);
                String qty = summmap.get(i).get(CART_QUANTITY_KEY);
                String price1 = summmap.get(i).get(CART_PRICE_KEY).replace("₹", "");
                Integer total = Integer.parseInt(price1) * Integer.parseInt(qty);
                price = price + total;
            }
        }

        sessionManagement = new SessionManagement(getActivity());

        summ_prod_price = view.findViewById(R.id.summ_prod_price);
        summ_prod_price.setText("₹"+String.valueOf(price));
        summ_pro = view.findViewById(R.id.summ_pro);
        summ_pro.setText("₹"+String.valueOf(price));
        final_price = view.findViewById(R.id.final_price);
        final_price.setText("₹"+String.valueOf(price));
        sumry_rec = view.findViewById(R.id.summ_cart);
        sumry_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getsummrec();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddressFragment addressFragment = new AddressFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,addressFragment).commit();

            }
        });
    }

    private void insertdata() {
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        HashMap<String, String> myorder  = new HashMap<>();
        myorder.put(MY_ORDER_ID,order_id.toString().trim());
        myorder.put(MY_ORDER_NAME,order_name.toString().trim());
        myorder.put(MY_ORDER_IMAGE,orderimg.toString().trim());
        myorder.put(MY_ORDER_PRICE,"");
        myorder.put(MY_ORDER_Quantity,"");
        myorder.put(MY_ORDER_SOLDTO,name.toString().trim());
        myorder.put(MY_ORDER_SUPPLIER,"Vr Traders");
        myorder.put(MY_ORDER_DATE,date.toString().trim());
        my_orderdatabse.myorderdata(myorder);
        cart_database.removefromcart(order_id);
        Log.e("cdacac", "insertdata: "+myorder);
        Toast.makeText(getActivity(), "Order placed", Toast.LENGTH_SHORT).show();
    }

    private void getsummrec() {

        if (summmap.size() > 0) {
            cartAdapter = new CartAdapter(getActivity(), summmap, new CartAdapter.OnAdapterclickListner() {
                @Override
                public void onItemClick(int position, ArrayList<HashMap<String, String>> cartlist) {

                }
            }) {

            };
            sumry_rec.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
        }

    }
}