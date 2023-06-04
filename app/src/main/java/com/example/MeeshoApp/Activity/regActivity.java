package com.example.MeeshoApp.Activity;

import static com.example.MeeshoApp.common.Base_url.BASE_URL;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.Api;
import com.example.MeeshoApp.common.MyReceiver;
import com.example.MeeshoApp.common.SessionManagement;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class regActivity extends AppCompatActivity {

    EditText User_reg, User_pass, User_confirm;
    Button reg;
    
    ImageView reg_show_pass,reg_hide_pass,reg_cnf_pass,reg_cnf_hide_pass;

    Login_Database loginDatabase;

    SessionManagement sessionManagement;

    String phoneno;

    private BroadcastReceiver MyReceiver = null;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reg);

        myReceiver = new MyReceiver();

        broadcastIntent();

        initview();
        
        show_hide_pass();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String USER = User_reg.getText().toString();
                String PASS = User_pass.getText().toString();
                String CONFIRM = User_confirm.getText().toString();
                String email ="namajn89@gmail.com";
                String refercode ="JMS001";
                String pincode ="284003";
                String address ="DD Nagar";
                String  state = "45";
                String district = "67";
                String city = "90";
                boolean check = Validateinfo(USER, PASS, CONFIRM);
//                Boolean checkuser = loginDatabase.checkusername(USER);
                Boolean insert = loginDatabase.inesrtData(USER,PASS,phoneno);

                    if (insert == true && check == true ) {
//                        post_data(USER,USER,sessionManagement.getdata().get(NUMBER_KEY),email,state,district,city,pincode,address,refercode,PASS,CONFIRM);
                        Toast.makeText(regActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        Intent reg = new Intent(regActivity.this, LoginActivity.class);
                        startActivity(reg);
                        finish();
                    }

                else {
//                    Toast.makeText(regActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
    private void post_data(String firstName,String lastName,String mobile,String email,String stateId,String districtId,String cityId,String pincode,String address,String referCode,String password,String confirmPassword){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         Api api = retrofit.create(Api.class);
         Call<ResponseBody> call = api.registration(firstName, lastName, mobile, email, stateId, districtId, cityId, pincode, address, referCode, password, confirmPassword);
         call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(regActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                ResponseBody red = response.body();
                String hhh;
                try {
                    hhh = red.string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Log.e("ccdmcds", "onResponse: "+hhh);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void show_hide_pass() {
        reg_show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                reg_hide_pass.setVisibility(View.VISIBLE);
                reg_show_pass.setVisibility(View.GONE);
            }
        });

        reg_hide_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());


                reg_show_pass.setVisibility(View.VISIBLE);
                reg_hide_pass.setVisibility(View.GONE);
            }
        });
        reg_cnf_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User_confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                reg_cnf_hide_pass.setVisibility(View.VISIBLE);
                reg_cnf_pass.setVisibility(View.GONE);
            }
        });

        reg_cnf_hide_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                reg_cnf_pass.setVisibility(View.VISIBLE);
                reg_cnf_hide_pass.setVisibility(View.GONE);
            }
        });

    }

    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void initview() {
        reg_show_pass = findViewById(R.id.iv_show_pass);
        reg_hide_pass = findViewById(R.id.iv_dnot_show);
        reg_cnf_pass = findViewById(R.id.iv_show_pass_cnf);
        reg_cnf_hide_pass = findViewById(R.id.iv_dnot_show_cnf);
        sessionManagement = new SessionManagement(this);
        phoneno = sessionManagement.getdata().get(NUMBER_KEY);

        User_reg = findViewById(R.id.user_reg);
        User_pass = findViewById(R.id.pass_reg);
        User_confirm = findViewById(R.id.confirmpass);
        reg = findViewById(R.id.new_reg);
        loginDatabase = new Login_Database(this);
    }

    private boolean Validateinfo(String user, String pass, String confirm) {
        if (user.length() == 0) {
            User_reg.requestFocus();
            User_reg.setError("Enter username");
            return false;
        } else if (!user.matches("[a-zA-Z]+")) {
            User_reg.requestFocus();
            User_reg.setError("Enter only alphabets");
            return false;
        } else if (pass.length() == 0) {
            User_pass.requestFocus();
            User_pass.setError("Enter password");
            return false;
        } else if (pass.length() <= 5) {
            User_pass.requestFocus();
            User_pass.setError("Minimum 6 characters required");
            return false;
        } else if (confirm.length() == 0) {
            User_confirm.requestFocus();
            User_confirm.setError("Enter password");
            return false;
        } else if (confirm.length() <= 5) {
            User_confirm.requestFocus();
            User_confirm.setError("Minimum 6 characters required");
            return false;
        } else if (!confirm.matches(pass)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}


