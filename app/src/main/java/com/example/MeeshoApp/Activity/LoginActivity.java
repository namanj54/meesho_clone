package com.example.MeeshoApp.Activity;


import static com.example.MeeshoApp.common.Base_url.BASE_URL;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.Api;
import com.example.MeeshoApp.common.SessionManagement;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    TextView forgot;
    TextView create;
    EditText user, pass;

    String User_session;

    Button login,show_pass;

    SessionManagement sessionManagement;

    Login_Database loginDatabase;

   ImageView pass_show,pass_hide;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        initview();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = user.getText().toString();
                String Pass = pass.getText().toString();
                boolean check = validteinfo(User, Pass);
                boolean checkuserpass = loginDatabase.checkusernamepassword(User,Pass);
                if (check == true && checkuserpass == true){
                    sessionManagement.createdata(User,Pass,true,"","","");
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                      startActivity(intent);
                      finish();

                }
                else {
                    Toast.makeText(LoginActivity.this, "Enter correct information", Toast.LENGTH_SHORT).show();
                }

            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent fg = new Intent(LoginActivity.this, forgotActivity.class);
                 startActivity(fg);

            }
        });
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dsp = new Intent(LoginActivity.this, CreateAccount.class);
                startActivity(dsp);
            }
        });

         pass_hide.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                 pass_show.setVisibility(View.VISIBLE);
                 pass_hide.setVisibility(View.GONE);
             }
         });

        pass_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                pass_hide.setVisibility(View.VISIBLE);
                pass_show.setVisibility(View.GONE);
            }
        });



    }
   private void check_data(String mobile,String Password){
       Log.e("bybbyb", "check_data: "+mobile );
       Log.e("byb", "check_data: "+Password );
           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
           Api api = retrofit.create(Api.class);
           Call<ResponseBody> call = api.login(mobile,Password);
           call.enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                   Toast.makeText(LoginActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

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





    private void initview() {
        forgot = findViewById(R.id.forgot);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.Login);
        pass_show = findViewById(R.id.iv_show_pass);
        pass_hide = findViewById(R.id.iv_dnot_show);

        loginDatabase = new Login_Database(this);
        sessionManagement = new SessionManagement(this);
       }


    private boolean validteinfo(String User, String Pass) {
        if (User.length() == 0) {
            user.requestFocus();
            user.setError("Enter username");
            return false;
        }
        else if (!User.matches("[a-zA-Z]+")) {
            user.requestFocus();
            user.setError("Enter only alphabets");
            return false;

        }
        else if (Pass.length() == 0) {
            pass.requestFocus();
            pass.setError("Enter password");
            return false;
        }
        else if (Pass.length() <= 5) {
            pass.requestFocus();
            pass.setError("Minimum 6 characters required");
            return false;
        }
        else {
            return true;
        }
    }
}



