package com.example.MeeshoApp.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.MyReceiver;


public class OtpActivity extends AppCompatActivity {

    EditText Otp1,Otp2,Otp3,Otp4;
    TextView Resend,Change;
    Button Submit;
    String v_pin="";

    String otp ="";

    private BroadcastReceiver MyReceiver = null;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_otp);

        myReceiver = new MyReceiver();

        broadcastIntent();

        initview();


        otp = getIntent().getStringExtra("otp");

            Handler h1 = new Handler();
            h1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (otp!=null&&!otp.isEmpty()&&otp.length()==4) {
                        Otp1.setText(String.valueOf(otp.charAt(0)));
                        Otp2.setText(String.valueOf(otp.charAt(1)));
                        Otp3.setText(String.valueOf(otp.charAt(2)));
                        Otp4.setText(String.valueOf(otp.charAt(3)));
                    }
                }
            },1000);
            String str1,str2,str3,str4;
            try {
                str1 = otp.substring(0, 1);
                str2 = otp.substring(1, 2);
                str3 = otp.substring(2, 3);
                str4 = otp.substring(3, 4);
            }catch (Exception e){
                e.printStackTrace();
            }
              Change.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent ch = new Intent(OtpActivity.this,CreateAccount.class);
                      startActivity(ch);
                      finish();
                  }
              });

       Resend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(OtpActivity.this, "your otp was"+v_pin, Toast.LENGTH_SHORT).show();
           }
       });



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = Otp1.getText().toString()+Otp2.getText().toString()+Otp3.getText().toString()+Otp4.getText().toString();

                if (code.equals(v_pin)){
                    Toast.makeText(OtpActivity.this, "Verified", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OtpActivity.this, regActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(OtpActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }


            }
        });
        numberotpmove();
    }
    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(MyReceiver);
//    }
    private void initview() {

        Change = findViewById(R.id.change);
        Resend = findViewById(R.id.resend);
        v_pin = CreateAccount.pincode;
        Submit = findViewById(R.id.submit);
        Otp1 = findViewById(R.id.otp1);
        Otp2 = findViewById(R.id.otp2);
        Otp3 = findViewById(R.id.otp3);
        Otp4 = findViewById(R.id.otp4);
    }

    private void numberotpmove() {
        Otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    Otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        Otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    Otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    Otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}