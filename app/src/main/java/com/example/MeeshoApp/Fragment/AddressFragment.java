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
import static com.example.MeeshoApp.common.constant.CITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.LOCALITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;
import static com.example.MeeshoApp.common.constant.ORDER_ID;
import static com.example.MeeshoApp.common.constant.PINCODE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.STATE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.MeeshoApp.Databases.Address_database;
import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {

    Button save_add;
    TextInputEditText add_name,add_phone,add_house,add_pin,add_city,add_state;

    Address_database address_database;

    SessionManagement sessionManagement;




    public AddressFragment() {

    }


    public static AddressFragment newInstance() {
        AddressFragment fragment = new AddressFragment();
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

        View view= inflater.inflate(R.layout.fragment_address, container, false);
        initid(view);
        return view;
    }

    private void initid(View view) {
        add_name = view.findViewById(R.id.add_name);
        add_phone = view.findViewById(R.id.add_phn);
        add_house = view.findViewById(R.id.add_houseno);
        add_pin = view.findViewById(R.id.add_pin);
        add_city = view.findViewById(R.id.add_city);
        add_state = view.findViewById(R.id.add_state);
        save_add = view.findViewById(R.id.add_save);
        address_database = new Address_database(getActivity());
        sessionManagement = new SessionManagement(getActivity());

       initial_set_current_location();


        save_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addname = add_name.getText().toString();
                String addphone = add_phone.getText().toString();
                String addhouse = add_house.getText().toString();
                String addpin = add_pin.getText().toString();
                String addcity = add_city.getText().toString();
                String addstate = add_state.getText().toString();

                boolean check = validateinfo(addname,addphone,addhouse,addpin,addcity,addstate);

                if (check == true){
                    insert_user_add_data();
                    PaymentFragment paymentFragment = new PaymentFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,paymentFragment).addToBackStack(null).commit();

                }
            }
        });
    }

    private void initial_set_current_location(){
        if (sessionManagement.getdata().get(LOCALITY_LIVE_KEY) != null && sessionManagement.getdata().get(CITY_LIVE_KEY) != null && sessionManagement.getdata().get(PINCODE_LIVE_KEY) != null && sessionManagement.getdata().get(STATE_LIVE_KEY) != null) {
            add_name.setText(sessionManagement.getdata().get(USERNAME_KEY));
            add_phone.setText(sessionManagement.getdata().get(NUMBER_KEY));
            add_city.setText(sessionManagement.getdata().get(CITY_LIVE_KEY));
            add_pin.setText(sessionManagement.getdata().get(PINCODE_LIVE_KEY));
            add_state.setText(sessionManagement.getdata().get(STATE_LIVE_KEY));
        }

    }

    private void insert_user_add_data() {
            HashMap<String, String> add_data  = new HashMap<>();
            add_data.put(ADD_USER,add_name.getText().toString().trim());
            add_data.put(ADD_USERPHONE,add_phone.getText().toString().trim());
            add_data.put(ADD_USERHOUSENO,add_house.getText().toString().trim());
            add_data.put(ADD_USERPIN,add_pin.getText().toString().trim());
            add_data.put(ADD_USERCITY,add_city.getText().toString().trim());
            add_data.put(ADD_USERSTATE,add_state.getText().toString().trim());
            address_database.insertadddata(add_data);
            Log.e("addresssdetails", "addressdta: "+add_data);
            Toast.makeText(getActivity(), "Address saved", Toast.LENGTH_SHORT).show();
        }

    private boolean validateinfo(String addname, String addphone, String addhouse, String addpin, String addcity, String addstate) {

        if (addname.length() == 0) {
            add_name.requestFocus();
            add_name.setError("Enter name");
            return false;
        }
        else if (!addname.matches("[a-zA-Z]+")) {
            add_name.requestFocus();
            add_name.setError("Enter only alphabets");
            return false;

        }
        else if (addphone.length()==0) {
            add_phone.requestFocus();
            add_phone.setError("Enter Mobile  Number");
            return false;

        }
        else if (addphone.length()<10){
            add_phone.requestFocus();
            add_phone.setError("Mandatory length 10");
            return false;
        }

        else if (!addphone.matches("(0|91)?[6-9][0-9]{9}")) {
            add_phone.requestFocus();
            add_phone.setError("First number should be greater than 5");
            return false;
        }
       else if (addhouse.length()==0) {
            add_house.requestFocus();
            add_house.setError("Enter house no.");
            return false;
        }
        else if (addpin.length() == 0) {
            add_pin.requestFocus();
            add_pin.setError("Enter pin");
            return false;
        }
        else if (addpin.matches("[a-zA-Z]+")) {
            add_pin.requestFocus();
            add_pin.setError("Enter only number");
            return false;
        }
        else if (addcity.length() == 0) {
            add_city.requestFocus();
            add_city.setError("Enter city");
            return false;
        }
        else if (!addcity.matches("[a-zA-Z]+")) {
            add_city.requestFocus();
            add_city.setError("Enter only alphabets");
            return false;

        }
        else if (addstate.length() == 0) {
            add_state.requestFocus();
            add_state.setError("Enter state");
            return false;
        }
        else
   return true;

    }
}