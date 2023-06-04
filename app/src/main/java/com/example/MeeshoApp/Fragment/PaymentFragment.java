package com.example.MeeshoApp.Fragment;

import static com.example.MeeshoApp.Adapter.PaymentAdapter.selected;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;
import static com.shreyaspatil.EasyUpiPayment.model.PaymentApp.AMAZON_PAY;
import static com.shreyaspatil.EasyUpiPayment.model.PaymentApp.GOOGLE_PAY;
import static com.shreyaspatil.EasyUpiPayment.model.PaymentApp.PAYTM;
import static com.shreyaspatil.EasyUpiPayment.model.PaymentApp.PHONE_PE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeeshoApp.Adapter.PaymentAdapter;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Model.PaymentModel;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.PaymentApp;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class PaymentFragment extends Fragment implements PaymentStatusListener {
    ArrayList<HashMap<String, String>> cartmap = new ArrayList<>();
    RecyclerView pay_rec;
    ArrayList<PaymentModel> paymentModels = new ArrayList<>();

    ImageView arr_up, arr_down;

    TextView prod_tt, order_tt, pay_tt;
    Button cont_pay;

    Cart_database cart_database;

    double price=0;

    SessionManagement sessionManagement;


    String upi="8127003484@ybl";
    String payment_name="";

    String titel;

    String desc="order";

    String place_order="";

    boolean cod = false;

    RadioButton cod_btn;

    PaymentAdapter paymentAdapter;

    public PaymentFragment() {
        // Required empty public constructor
    }


    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
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

        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        initid(view);

        return view;
    }


    private void initid(View view) {
        sessionManagement = new SessionManagement(getActivity());
        payment_name = sessionManagement.getdata().get(USERNAME_KEY);
        cont_pay = view.findViewById(R.id.pay_con_btn);


        cart_database = new Cart_database(getActivity());
         cartmap = cart_database.getcartitem();
        if (cartmap.size() > 0) {
             int total=0;
            for (int i = 0; i < cartmap.size(); i++) {
                String qty = cartmap.get(i).get(CART_QUANTITY_KEY);
                String price1 = cartmap.get(i).get(CART_PRICE_KEY).replace("₹", "");
                 total = Integer.parseInt(price1) * Integer.parseInt(qty);
                price = price + total;
            }
        }
            arr_up = view.findViewById(R.id.up_arrow);
            arr_down = view.findViewById(R.id.drop_arrow);
            pay_tt = view.findViewById(R.id.order_show_price);
            pay_tt.setText("₹"+String.valueOf(price));
            order_tt = view.findViewById(R.id.order_total_price);
            order_tt.setText("₹"+String.valueOf(price));
            prod_tt = view.findViewById(R.id.prod_price);
            prod_tt.setText("₹"+String.valueOf(price));
            cod_btn = view.findViewById(R.id.cod_btn);


            arr_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arr_down.setVisibility(View.GONE);
                    arr_up.setVisibility(View.VISIBLE);
                    pay_rec.setVisibility(View.VISIBLE);
                }
            });
            arr_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arr_up.setVisibility(View.GONE);
                    arr_down.setVisibility(View.VISIBLE);
                    pay_rec.setVisibility(View.GONE);
                }
            });


            pay_rec = view.findViewById(R.id.pay_rec);
            pay_rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            getpayrec();
        cash_on_delievery();

        cont_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(place_order.isEmpty() && cod == false){
                        Toast.makeText(getActivity(), "Select payment option", Toast.LENGTH_SHORT).show();
                    }else {
                        SummaryFragment summaryFragment = new SummaryFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner, summaryFragment).commit();
                    }
                }
            });

        }

    private void cash_on_delievery() {

    cod_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cod = true;
            selected = -1;
            paymentAdapter.notifyDataSetChanged();
        }
    });

    }

    private void makePayment(String amount, String upi, String name, String desc, String transactionId,String payname) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(getActivity())
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making payment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transactionId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transactionId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(String.valueOf(amount))
                // on below line we are calling a build method to build this ui.
                .build();
//
        easyUpiPayment.setDefaultPaymentApp(PaymentApp.valueOf(payname));

        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this);
    }

        private void getpayrec () {
            paymentModels.clear();
            paymentModels.add(new PaymentModel(R.drawable.phonepay, "Phone Pe"));
            paymentModels.add(new PaymentModel(R.drawable.google, "Google Pay"));
            paymentModels.add(new PaymentModel(R.drawable.pytm, "paytm"));
            paymentModels.add(new PaymentModel(R.drawable.amazon, "amazon pay"));


             paymentAdapter = new PaymentAdapter(getActivity(), paymentModels, new PaymentAdapter.OnAdapterclickListner() {
                @Override
                public void onItemClick(int position) {

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
                    String transcId = df.format(c);


                          titel = paymentModels.get(position).getTv_pay_name();


                              switch (titel) {
                                  case "Phone Pe":
                                      place_order = "online";
                                      makePayment(String.valueOf(price), upi, payment_name, desc, transcId, String.valueOf(PHONE_PE));
                                      Toast.makeText(getActivity(), "directing to phone pe", Toast.LENGTH_SHORT).show();
                                      break;
                                  case "Google Pay":
                                      place_order = "online";
                                      makePayment(String.valueOf(price), upi, payment_name, desc, transcId, String.valueOf(GOOGLE_PAY));
                                      Toast.makeText(getActivity(), "directing to google pay", Toast.LENGTH_SHORT).show();
                                      break;
                                  case "paytm":
                                      place_order = "online";
                                      makePayment(String.valueOf(price), upi, payment_name, desc, transcId, String.valueOf(PAYTM));
                                      Toast.makeText(getActivity(), "directing to paytm", Toast.LENGTH_SHORT).show();
                                      break;
                                  case "amazon pay":
                                      place_order = "online";
                                      makePayment(String.valueOf(price), upi, payment_name, desc, transcId, String.valueOf(AMAZON_PAY));
                                      Toast.makeText(getActivity(), "directing to amazon pay", Toast.LENGTH_SHORT).show();
                                      break;
                              }
                           cod_btn.setChecked(false);
                           cod = false;

                }

                @Override
                public void onDeleteClick(int position) {

                }

                @Override
                public void onEditClick(int position) {

                }



            });
            pay_rec.setAdapter(paymentAdapter);


        }


    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        Toast.makeText(getActivity(), "completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSuccess() {
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {
        Toast.makeText(getActivity(), "good to go", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionFailed() {
        Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
        selected = -1;
        paymentAdapter.notifyDataSetChanged();
        place_order ="";
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(getActivity(), "cancelled", Toast.LENGTH_SHORT).show();
        selected = -1;
        paymentAdapter.notifyDataSetChanged();
        place_order ="";
    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(getActivity(), "App not found", Toast.LENGTH_SHORT).show();
        selected = -1;
        paymentAdapter.notifyDataSetChanged();
        place_order ="";
    }
}
