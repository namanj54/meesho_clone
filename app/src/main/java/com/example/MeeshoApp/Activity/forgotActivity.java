package com.example.MeeshoApp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.R;


public class forgotActivity extends AppCompatActivity {

    EditText userup,passup,confirmpassup;

    Button Change;

    Login_Database loginDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);
        userup = findViewById(R.id.user_up);
        passup = findViewById(R.id.pass_up);
        confirmpassup = findViewById(R.id.confirmpass_up);
        Change = findViewById(R.id.new_reg);
        loginDatabase = new Login_Database(this);

       Change.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String USER = userup.getText().toString();
               String PASS = passup.getText().toString();
               String CONFIRM = confirmpassup.getText().toString();
               boolean check = validateinfo(USER,PASS,CONFIRM);
               boolean CHECKUSER = loginDatabase.checkusername(USER);
               boolean updatepassword = loginDatabase.updatepassword(USER,PASS);
               if (check == true && CHECKUSER == true && updatepassword == true){

                   Toast.makeText(forgotActivity.this, "password updated", Toast.LENGTH_SHORT).show();
                   Intent up = new Intent(forgotActivity.this,LoginActivity.class);
                   startActivity(up);

               }
               else {
                   Toast.makeText(forgotActivity.this, "User not registerd", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }

    private boolean validateinfo(String user, String pass, String confirm) {
        if (user.length() == 0) {
            userup.requestFocus();
            userup.setError("Enter username");
            return false;
        }
        else if (!user.matches("[a-zA-Z]+")) {
            userup.requestFocus();
            userup.setError("Enter only alphabets");
            return false;
        }
        else if (pass.length() == 0) {
            passup.requestFocus();
            passup.setError("Enter password");
            return false;
        }
        else if (pass.length() <= 5) {
            passup.requestFocus();
            passup.setError("Minimum 6 characters required");
            return false;
        }
        else if (confirm.length() == 0) {
            confirmpassup.requestFocus();
            confirmpassup.setError("Enter password");
            return false;
        }
        else if (confirm.length() <= 5) {
            confirmpassup.requestFocus();
            confirmpassup.setError("Minimum 6 characters required");
            return false;
        }
        else if (!confirm.matches(pass)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }
        return true;

    }
}