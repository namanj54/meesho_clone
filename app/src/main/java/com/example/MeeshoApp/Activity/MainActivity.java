package com.example.MeeshoApp.Activity;



import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;
import static com.example.MeeshoApp.common.constant.WISH_COLOUMN;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;


import com.example.MeeshoApp.Databases.Cart_database;
import com.example.MeeshoApp.Databases.Login_Database;
import com.example.MeeshoApp.Databases.Wishlist_database;
import com.example.MeeshoApp.Fragment.CartFragment;
import com.example.MeeshoApp.Fragment.CategoryFragment;
import com.example.MeeshoApp.Fragment.HomeFragment;
import com.example.MeeshoApp.Fragment.My_orderFragment;
import com.example.MeeshoApp.Fragment.ProductFragment;
import com.example.MeeshoApp.Fragment.WishlistFragment;
import com.example.MeeshoApp.Fragment.accountFragment;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.MyReceiver;
import com.example.MeeshoApp.common.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SearchView s;
    BottomNavigationView bottomNavigationView;

    TextView tv_username;
    public  static TextView Cart_item_count;

    public  static ImageView wish_count;
    SessionManagement sessionManagement;

    LinearLayout location;
    Login_Database loginDatabase;

    ImageView live_locate;

    ImageView wish,cart,speech;
    CircleImageView logo;

    String spokenText;

    private static final int SPEECH_REQUEST_CODE = 0;



    private BroadcastReceiver MyReceiver = null;
    MyReceiver  myReceiver;

    Cart_database cart_database;

  public static Wishlist_database wishlist_database;

    ArrayList<HashMap<String, String>> cartdata = new ArrayList<>();

    static ArrayList<HashMap<String, String>> wishdata = new ArrayList<>();

    int qty1 ;
   public static int col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
        broadcastIntent();
        initview();

    }

    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void initview() {
        live_locate = findViewById(R.id.live_locate);
        location = findViewById(R.id.location);
        speech = findViewById(R.id.speech_ass);

       wish = findViewById(R.id.wishlist);
       cart = findViewById(R.id.cart);
        s = findViewById(R.id.searchview);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        sessionManagement = new SessionManagement(this);
        tv_username = findViewById(R.id.userhome);
        loginDatabase = new Login_Database(this);
        logo = findViewById(R.id.userlogo);

        Cart_item_count = findViewById(R.id.cart_badge);
        wish_count = findViewById(R.id.wish_count);

        tv_username.setText(sessionManagement.getdata().get(USERNAME_KEY));
        HomeFragment fm=new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.conatiner,fm).commit();

         cart_database = new Cart_database(this);
         wishlist_database = new Wishlist_database(this);
        counter_bage_data();
        updatedview();
        setupBadge(qty1);
        wish_count();

        check_wish_data(col);

        s.setQueryHint("Search by Keyword or Product id");



        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WishlistFragment wf = new WishlistFragment();

                FragmentManager w= MainActivity.this.getSupportFragmentManager();
                w.beginTransaction().replace(R.id.conatiner,wf).addToBackStack(null).commit();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment cf = new CartFragment();


                FragmentManager fm= MainActivity.this.getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.conatiner,cf).addToBackStack(null).commit();

            }
        });
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySpeechRecognizer();
            }
        });
    }
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
             spokenText = results.get(0);
            s.setQuery(spokenText,true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void wish_count() {
        Log.e("njnn", "wish_count: "+wishlist_database.getWishList().size() );
        if (wishlist_database.getWishList().size()!= 0) {
            wishdata = wishlist_database.getWishList();
            if (wishdata.size() > 0) {
                 col =0;
                for (int i = 0; i < wishdata.size(); i++) {
                    col += Integer.parseInt(wishdata.get(i).get(WISH_COLOUMN));

                }
            }
        }
        else {
            wish_count.setVisibility(View.GONE);
        }

    }
    public  SearchView search_view_id() {
        s = findViewById(R.id.searchview);
        return s;
    }
    public static void check_wish_data(int col) {

        if (col>0) {
            {
                wish_count.setVisibility(View.VISIBLE);
            }
        } else {
            wish_count.setVisibility(View.GONE);
        }
    }

    private void counter_bage_data() {
        if (cart_database != null) {
            cartdata = cart_database.getcartitem();
            if (cartdata.size() > 0) {

                for (int i = 0; i < cartdata.size(); i++) {
                    qty1 += Integer.parseInt(cartdata.get(i).get(CART_QUANTITY_KEY));
                }
            }
        }
    }



    public static void setupBadge(int qty) {

            if (qty == 0) {
                {
                    Cart_item_count.setVisibility(View.GONE);
                }
            } else {
                 Cart_item_count.setText(String.valueOf(qty));
                    Cart_item_count.setVisibility(View.VISIBLE);
                }
            }

    public void updatedview() {
        if (loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)) != null) {
            byte[] imageBytes = Base64.decode(loginDatabase.getimage(sessionManagement.getdata().get(USERNAME_KEY)), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            logo.setImageBitmap(decodedImage);
            logo.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                HomeFragment frg_home = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.conatiner, frg_home).addToBackStack(null).commit();
                break;
            case R.id.categories:
                CategoryFragment categoryFragment = new CategoryFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.conatiner, categoryFragment).addToBackStack(null).commit();
                break;
            case R.id.orders:
                My_orderFragment my_orderFragment = new My_orderFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,my_orderFragment).addToBackStack(null).commit();
                break;
            case R.id.community:
                Uri uri = Uri.parse("https://meesho.com/community/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.acc:
                accountFragment frg_acc = new accountFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.conatiner, frg_acc).addToBackStack(null).commit();
                break;
        }
        return true;
    }
}