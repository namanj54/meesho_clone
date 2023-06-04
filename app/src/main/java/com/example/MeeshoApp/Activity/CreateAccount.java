package com.example.MeeshoApp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;

import java.util.Random;

public class CreateAccount extends AppCompatActivity {
    EditText phono;
    TextView txt;

    String number="0123456789";
    Button cont;
    Login_Database loginDatabase;

    SessionManagement sessionManagement;

    public static String pincode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_account);
        initView();
    }
        private void initView() {
            phono = findViewById(R.id.phno);
            cont = findViewById(R.id.cont);
            loginDatabase = new Login_Database(this);
            sessionManagement = new SessionManagement(this);



            Random rnd = new Random();



            cont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phonno = phono.getText().toString();
                    boolean check =validateinfo(phonno);
                    boolean checknumber = loginDatabase.checknumber(phonno);

                    if (check==true && checknumber == false ){

                        sessionManagement.createno(phonno);
                        pincode="";
                        char[] OTP1 = new char[4];
                        for(int i=0;i<4;i++){
                            OTP1[i] = number.charAt(rnd.nextInt(number.length()));
                            pincode += OTP1[i];

                        }



                        Intent otp = new Intent(CreateAccount.this, OtpActivity.class);
                        otp.putExtra("otp",pincode);
                        startActivity(otp);
                        finish();


                    }
                    else {
                        Toast.makeText(CreateAccount.this, "Number already registered", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }




    private boolean validateinfo(String str_phone) {
        if (str_phone.length()==0) {
            phono.requestFocus();
            phono.setError("Enter Mobile  Number");
            return false;

        }
        else if (str_phone.length()<10){
            phono.requestFocus();
            phono.setError("Mandatory length 10");
            return false;
        }

        else if (!str_phone.matches("(0|91)?[6-9][0-9]{9}")) {
            phono.requestFocus();
            phono.setError("First number should be greater than 5");
            return false;
        }




        else {
            return true;
        }
    }

}