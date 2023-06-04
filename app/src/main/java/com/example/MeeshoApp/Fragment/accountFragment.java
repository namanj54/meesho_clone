package com.example.MeeshoApp.Fragment;



import static com.example.MeeshoApp.common.constant.ADD_USERPHONE;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MeeshoApp.Activity.LoginActivity;
import com.example.MeeshoApp.Adapter.AccountAdapter;
import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.Model.AccountModel;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link accountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class accountFragment extends Fragment {
 RecyclerView acc_rec;

 AccountAdapter accountAdapter;


 ArrayList<AccountModel> accountModels = new ArrayList<>();

 SessionManagement sessionManagement;

 TextView user_acc;
 ImageView Edit;
 CircleImageView acc_logo;

 Login_Database loginDatabase;

 LinearLayout call_help;
 Vibrator vibrator;

 private static int REQUEST_PHONE_CALL =100;


    public accountFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static accountFragment newInstance(String param1, String param2) {
        accountFragment fragment = new accountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initallid(view);
        return view;
    }
     private void initallid(View view){

         acc_rec = view.findViewById(R.id.acc_rec);
         acc_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
         getAccRecyclerview();
          loginDatabase = new Login_Database(getActivity());
         sessionManagement = new SessionManagement(getActivity());
         user_acc = view.findViewById(R.id.user_account);
         user_acc.setText(sessionManagement.getdata().get(USERNAME_KEY));
         acc_logo = view.findViewById(R.id.acc_logo);
         call_help = view.findViewById(R.id.calling_hlp);


         if( loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)) != null ){

             byte[] imageBytes = Base64.decode(loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)), Base64.DEFAULT);
             Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
             acc_logo.setImageBitmap(decodedImage);

         }
         else {

         }

       Edit = view.findViewById(R.id.edit);

       Edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               EditFragment fragment =new EditFragment();
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,fragment).addToBackStack(null).commit();

           }
       });

       call_help.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               checkPermission(Manifest.permission.CALL_PHONE,REQUEST_PHONE_CALL);

           }
       });

     }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_DENIED) {
           vibrate_on_click();
            ActivityCompat.requestPermissions(getActivity(), new String[] { permission }, requestCode);
        }
        else {
            call_help_center();
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode ==REQUEST_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call_help_center();
                Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
    }

     private void call_help_center(){
         String phonenumber = "8127003484";
         Intent  intent1 = new Intent(Intent.ACTION_DIAL);
         intent1.setData(Uri.parse("tel:"+phonenumber));
         startActivity(intent1);
     }

    public void vibrate_on_click(){
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(1);
        }
    }


    private void getAccRecyclerview() {
        accountModels.clear();
        accountModels.add(new AccountModel("Logout", R.drawable.baseline_logout_24));
        accountModels.add(new AccountModel("Your Account", R.drawable.baseline_person_24));
        accountModels.add(new AccountModel("Wishlist", R.drawable.like));
        accountModels.add(new AccountModel("Share", R.drawable.baseline_share_24));
        accountModels.add(new AccountModel("Daily news", R.drawable.news));
        accountModels.add(new AccountModel("Settings",R.drawable.baseline_category_24));
        accountModels.add(new AccountModel("Legal and Policies",R.drawable.baseline_category_24));

        accountAdapter = new AccountAdapter(getActivity(), accountModels, new AccountAdapter.OnAdapterclickListner() {
            @Override
            public void onItemClick(int position) {
                String titel = accountModels.get(position).getTextview8();

                switch (position){
                    case 0:
                        ShowLogoutDialog();
                        break;
                    case 1:
                        EditFragment editFragment = new EditFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,editFragment).commit();
                        break;
                    case 2:
                        WishlistFragment wishlistFragment = new WishlistFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,wishlistFragment).commit();
                        break;
                    case 3:
                     Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_REFERRER_NAME,sessionManagement.getdata().get(USERNAME_KEY));
                     intent.putExtra(Intent.EXTRA_SUBJECT,R.drawable.meesho);
                     intent.putExtra(Intent.EXTRA_TEXT,"Checkout my newly devloped Appliaction. \n https://www.meesho.com/"+getActivity().getPackageName());
                     startActivity(Intent.createChooser(intent,"Share via"));
                    case 4:
                        NewsFragment newsFragment = new NewsFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,newsFragment).commit();
                        break;


                }

            }

            @Override
            public void onDeleteClick(int position) {

            }

            @Override
            public void onEditClick(int position) {

            }
        });
        acc_rec.setAdapter(accountAdapter);

    }



    private void ShowLogoutDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        Button yes_logout = dialog.findViewById(R.id.yes_logout);
        Button no_logout = dialog.findViewById(R.id.no_logout);
        dialog.setCanceledOnTouchOutside(false);


        yes_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sessionManagement.logoutsession();
                Intent s = new Intent(getActivity(), LoginActivity.class);
                startActivity(s);
                getActivity().finish();
            }
        });

        no_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }






}