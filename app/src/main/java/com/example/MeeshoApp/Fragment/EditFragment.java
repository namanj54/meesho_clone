package com.example.MeeshoApp.Fragment;

import static android.app.Activity.RESULT_OK;


import static com.example.MeeshoApp.common.constant.CITY_KEY;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;
import static com.example.MeeshoApp.common.constant.PASSWORD_KEY;
import static com.example.MeeshoApp.common.constant.PINCODE_KEY;
import static com.example.MeeshoApp.common.constant.STATE_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    private static final int REQUEST_GET_SINGLE_FILE = 1000000;


TextInputEditText name,phone,pin_code,city_,state_;
TextView add_photo;
Button save;
Spinner spinner;
ArrayList<String> spinner_list = new ArrayList<>();
Login_Database loginDatabase ;
SessionManagement sessionManagement;
CircleImageView pic;
String nu,sImage,mm;

    public EditFragment() {

    }



    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View view =  inflater.inflate(R.layout.fragment_edit, container, false);
        initview(view);
        Spinner_list();
        return view;
    }

    private void initview(View view) {
        pin_code = view.findViewById(R.id.pin);
        city_ = view.findViewById(R.id.city);
        state_ = view.findViewById(R.id.state);
        add_photo = view.findViewById(R.id.pic_edit);
        pic = view.findViewById(R.id.pic);
        name = view.findViewById(R.id.full);
        phone = view.findViewById(R.id.ph);
        save = view.findViewById(R.id.save);
        spinner = view.findViewById(R.id.spner);
        loginDatabase = new Login_Database(getActivity());
        sessionManagement = new SessionManagement(getActivity());
        name.setText(sessionManagement.getdata().get(USERNAME_KEY));
        phone.setText(loginDatabase.getnumber(sessionManagement.getdata().get(USERNAME_KEY)));
        nu = sessionManagement.getdata().get(USERNAME_KEY);
        mm = sessionManagement.getdata().get(PASSWORD_KEY);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User = name.getText().toString();
                String Pass = phone.getText().toString();
                String PINCODE = pin_code.getText().toString();
                String CITY = city_.getText().toString();
                String STATE = state_.getText().toString();
               boolean info = checkinfo(PINCODE,CITY,STATE);

                boolean check = validteinfo(User, Pass);
                boolean update = loginDatabase.updateData(User, nu);





                if (check == true && update == true  && info == true) {
                    sessionManagement.updatedata(CITY,PINCODE,STATE);
                    Toast.makeText(getActivity(), "Details updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "lol", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Log.e("profile_image", loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)));

        if( loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)) != null ){

            byte[] imageBytes = Base64.decode(loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            pic.setImageBitmap(decodedImage);

        }



        Log.d("pincc", "initview: "+sessionManagement.getdata().get(PINCODE_KEY).toString());
       if (!sessionManagement.getdata().get(PINCODE_KEY).toString() .isEmpty() && !sessionManagement.getdata().get(CITY_KEY).toString() .isEmpty() && !sessionManagement.getdata().get(STATE_KEY).toString() .isEmpty() ){
           pin_code.setText(sessionManagement.getdata().get(PINCODE_KEY).toString());
           city_.setText(sessionManagement.getdata().get(CITY_KEY).toString());
           state_.setText(sessionManagement.getdata().get(STATE_KEY).toString());
       }



        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfileImage();

            }

        });
    }

    public void chooseProfileImage() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        pic.setImageBitmap(null);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
    }

    @SuppressLint("Range")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_GET_SINGLE_FILE) {

                    Uri uri= data.getData();
                    // Initialize bitmap
                    try {
                        Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                        // initialize byte stream
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        // compress Bitmap
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        // Initialize byte array
                        byte[] bytes=stream.toByteArray();
                        // get base64 encoded string
                        sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
                        sessionManagement.create(sImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    final String x = getPathFromURI(uri);



                    if (x != null) {
                        File f = new File(x);
                        uri = Uri.fromFile(f);
                    }
                    // Set the image in ImageView
                    if (uri==null){
                        Toast.makeText(getActivity(), "Please select image", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        String ether = sessionManagement.getdata().get(USERNAME_KEY);
                        Log.e("user_key", "onActivityResult: "+ether );
                        pic.setImageURI(uri);
                        try {
                            loginDatabase.updateimage(sImage,ether);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ((MainActivity) getActivity()).updatedview();
                        Log.e("vgbhjm",loginDatabase.getimage(ether));
                        Log.e("user_rs", "onActivityResult: "+ether);
                        Picasso.get().load(uri).into(pic);
                    }
                }
            }
        }




    public String getPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;

    }
    private void Spinner_list(){
        spinner_list.add("Gender");
     spinner_list.add("male");
     spinner_list.add("female");
     spinner_list.add("others");
     ArrayAdapter<String> adapter_gender= new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinner_list);
        adapter_gender.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
     spinner.setAdapter(adapter_gender);
     spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             if(spinner.getSelectedItem().toString().trim().equalsIgnoreCase(getString(R.string.spiner_select))){
//                 ((TextView) spinner.getSelectedItem()).setTextColor(getResources().getColor(R.color.black));

             }
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });
    }
    private boolean checkinfo(String pincode, String city, String state) {
        if (pincode.length()==0) {
            pin_code.requestFocus();
            pin_code.setError("Enter Mobile  Number");
            return false;
        }
        if (city.length() == 0) {
            city_.requestFocus();
            city_.setError("Enter username");
            return false;
        }
        else if (!city.matches("[a-zA-Z]+")) {
            city_.requestFocus();
            city_.setError("Enter only alphabets");
            return false;
        }
        if (state.length() == 0) {
            state_.requestFocus();
            state_.setError("Enter username");
            return false;
        }
        else if (!state.matches("[a-zA-Z]+")) {
            state_.requestFocus();
            state_.setError("Enter only alphabets");
            return false;

        }

        return true;

    }
    private boolean validteinfo(String user, String pass) {

        if (user.length() == 0) {
            name.requestFocus();
            name.setError("Enter username");
            return false;
        }
        else if (!user.matches("[a-zA-Z]+")) {
            name.requestFocus();
            name.setError("Enter only alphabets");
            return false;

        }
        if (pass.length()==0) {
            phone.requestFocus();
            phone.setError("Enter Mobile  Number");
            return false;

        }
        else if (pass.length()<10){
            phone.requestFocus();
            phone.setError("Mandatory length 10");
            return false;
        }

        else if (!pass.matches("(0|91)?[6-9][0-9]{9}")) {
            phone.requestFocus();
            phone.setError("First number should be greater than 5");
            return false;
        }
        return true;

    }


}