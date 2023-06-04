package com.example.MeeshoApp.Activity;


import static com.example.MeeshoApp.common.constant.USERNAME_KEY;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.MyReceiver;
import com.example.MeeshoApp.common.SessionManagement;

public class SplashActivity extends AppCompatActivity {

    private Button button;
    Handler handler;
    Runnable runnable;

    TextView text;
      ProgressBar progressss;

      SessionManagement sessionManagement;
      MyReceiver myReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);



        text = findViewById(R.id.text);
        progressss = findViewById(R.id.progressss);
        progressss.setVisibility(View.VISIBLE);
        sessionManagement = new SessionManagement(SplashActivity.this);



        Log.e("hhhhh", "onCreate: "+ sessionManagement.getdata().get(USERNAME_KEY) );

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animate);
        text.startAnimation(animation);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {

                    if (sessionManagement.islog()==true) {
                        Intent f = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(f);
                        finish();
                    } else {
                        Intent dsp = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(dsp);
                        finish();
                    }

                }catch (Exception e)
                {e.printStackTrace();}
            }
        },5000);



        }


}
