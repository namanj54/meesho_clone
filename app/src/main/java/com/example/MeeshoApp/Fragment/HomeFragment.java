package com.example.MeeshoApp.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;
import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;
import static com.example.MeeshoApp.common.constant.CITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.LOCALITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.PINCODE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.PRO_DELIEVERY;
import static com.example.MeeshoApp.common.constant.PRO_ID;
import static com.example.MeeshoApp.common.constant.PRO_IMAGE;
import static com.example.MeeshoApp.common.constant.PRO_NAME;
import static com.example.MeeshoApp.common.constant.PRO_OFFERS;
import static com.example.MeeshoApp.common.constant.PRO_PRICE;
import static com.example.MeeshoApp.common.constant.PRO_QUANTITY;
import static com.example.MeeshoApp.common.constant.PRO_RATING;
import static com.example.MeeshoApp.common.constant.PRO_SATUS;
import static com.example.MeeshoApp.common.constant.STATE_LIVE_KEY;
import static com.google.android.gms.common.internal.service.Common.API;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.androdocs.httprequest.HttpRequest;
import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Adapter.AdapterHome2;
import com.example.MeeshoApp.Adapter.LookingAdapter;
import com.example.MeeshoApp.Adapter.ProductAdapter;

import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.Model.HomeModel2;
import com.example.MeeshoApp.Model.LookingModel;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {
    RecyclerView rec_grid, rec_category,products;
    LocationRequest locationRequest;

    ArrayList<HashMap<String, String>> products_list = new ArrayList<>();
    LookingAdapter lookingAdapter;
    AdapterHome2 adapterHome2;

    ProductAdapter productAdapter;
    ArrayList<LookingModel> lookingModels = new ArrayList<>();
    ArrayList<HomeModel2> homeModel2s= new ArrayList<>();

    ArrayList<ProductModel> productModels= new ArrayList<>();

    TextView live_locate,weather_today;

    ImageView iv_location_icon;

    SessionManagement sessionManagement;
    Vibrator vibrator;

    SearchView searchView;

    Product_database product_database;

    ProductModel productModel;

    String API = "8118ed6ee68db2debfaaa5a44c832918";



    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        try {
            initallID(view);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_UP && keyCode == keyEvent.KEYCODE_BACK){

                    Dialog dialog = new Dialog(getActivity());
                    dialog.getWindow();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.setContentView(R.layout.dialog_logout);
                    Button yes_logout = dialog.findViewById(R.id.yes_logout);
                    Button no_logout = dialog.findViewById(R.id.no_logout);


                    yes_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            getActivity().finishAffinity();
                        }
                    });

                    no_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    return  true;

                }
                return false;
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        return view;
    }

    private void initallID(View view) throws JSONException {
        sessionManagement = new SessionManagement(getActivity());
        iv_location_icon = view.findViewById(R.id.live_locate);
        live_locate = view.findViewById(R.id.loaction_user);
        weather_today = view.findViewById(R.id.weather_today);
        product_database = new Product_database(getActivity());

//
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        iv_location_icon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 getCurrentLocation();
             }
         });
        check_live_data();


        rec_grid = view.findViewById(R.id.rec_grid);
        rec_grid.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getRecyclerview();

        rec_category = view.findViewById(R.id.rec_category);
        rec_category.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        getProdtRecyclerview();

        products = view.findViewById(R.id.products);
        products.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getproduRecyclerview();

        searchView =((MainActivity) getActivity()).search_view_id();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filter(newText);
                ProductFragment productFragment = new ProductFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,productFragment).addToBackStack(null).commit();
                return true;
            }
        });
    }
    private void filter(String text) {

        ArrayList<ProductModel> filteredlist = new ArrayList<ProductModel>();


        for (ProductModel item : productModels) {

            if (item.getProduct_name().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

        } else {

            productAdapter.filterList(filteredlist);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {
                    getCurrentLocation();

                }else {
                    turnOnGPS();
                }
            }
        }


    }
    public void vibrate_on_click(){
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getActivity())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getActivity())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                          List<Address> addresses = null;
                                        try {
                                    addresses = geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1);
                                    Log.e("nuencnc", "onSuccess: "+addresses.get(0).getSubLocality());
                                    Log.e("nuencnc", "onSuccess: "+addresses.get(0).getLocality());
                                    Log.e("nuencnc", "onSuccess: "+addresses.get(0).getPostalCode());
                                    Log.e("nuencnc", "onSuccess: "+addresses.get(0).getAdminArea());
                                    live_locate.setText("Delivering to "+addresses.get(0).getSubLocality()+","+addresses.get(0).getLocality()+","+addresses.get(0).getPostalCode()+","+addresses.get(0).getAdminArea());
                                    sessionManagement.insert_live_data(addresses.get(0).getSubLocality(),addresses.get(0).getLocality(),addresses.get(0).getPostalCode(),addresses.get(0).getAdminArea());
                                    set_live_data();
                                    Log.e("frtctc", "onSuccess: "+sessionManagement.getdata().get(LOCALITY_LIVE_KEY));
                                    Log.e("nuencnc", "onSuccess: "+addresses.get(0).getSubLocality());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            } else {
                vibrate_on_click();
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void turnOnGPS() {



        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getActivity())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(getActivity(), "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(getActivity(), 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {

            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }



    private void check_live_data() {
        if (sessionManagement.getdata().get(LOCALITY_LIVE_KEY) != null && sessionManagement.getdata().get(CITY_LIVE_KEY) != null && sessionManagement.getdata().get(PINCODE_LIVE_KEY) != null && sessionManagement.getdata().get(STATE_LIVE_KEY) != null) {
            set_live_data();
        }

    }
    private void set_live_data(){
        if (sessionManagement.getdata().get(LOCALITY_LIVE_KEY) != null && sessionManagement.getdata().get(CITY_LIVE_KEY) != null && sessionManagement.getdata().get(PINCODE_LIVE_KEY) != null && sessionManagement.getdata().get(STATE_LIVE_KEY) != null ){
            live_locate.setText("Delivering to "+sessionManagement.getdata().get(LOCALITY_LIVE_KEY).toString()+","+sessionManagement.getdata().get(CITY_LIVE_KEY).toString()+","+sessionManagement.getdata().get(PINCODE_LIVE_KEY).toString()+","+sessionManagement.getdata().get(STATE_LIVE_KEY).toString());
            new weatherTask().execute();
        }
    }
    class weatherTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        public String doInBackground(String... args) {
            String response =
                    HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + sessionManagement.getdata().get(CITY_LIVE_KEY)
                            + "&units=metric&appid=" + API);
            return response;
        }

        @Override
        public void onPostExecute(String result) {


            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject sys = jsonObj.getJSONObject("sys");
                JSONObject wind = jsonObj.getJSONObject("wind");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                Long updatedAt = jsonObj.getLong("dt");
                String updatedAtText = new SimpleDateFormat("dd/MM/yyyy"+
                        "hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
                String temp = main.getString("temp") + "°C";
                String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                String pressure = main.getString("pressure");
                String humidity = main.getString("humidity");

                Long sunrise = sys.getLong("sunrise");
                Long sunset = sys.getLong("sunset");
                String windSpeed = wind.getString("speed");
                String weatherDescription = weather.getString("description");

                weather_today.setText(temp+"  "+weatherDescription);


            } catch (JSONException e) {
            }

        }
    }
    private void getdata(){
        products_list.clear();
        Log.e("productModels", "getdata: "+productModels.size());

        for (int i=0;i<=productModels.size()-1;i++) {
            HashMap<String,String> P_map = new HashMap<>();
            Log.e("bhhb ", "getdata: "+productModels.get(i).getId() );
            P_map.put(PRO_ID,String.valueOf(productModels.get(i).getId()));
            P_map.put(PRO_IMAGE,String.valueOf(productModels.get(i).getIv_product()));
            P_map.put(PRO_NAME,productModels.get(i).getProduct_name());
            P_map.put(PRO_PRICE,productModels.get(i).getPrice());
            P_map.put(PRO_OFFERS,productModels.get(i).getOffers());
            P_map.put(PRO_DELIEVERY,productModels.get(i).getDeliver_status());
            P_map.put(PRO_RATING,productModels.get(i).getRating());
            P_map.put(PRO_SATUS,productModels.get(i).getStatus());
            P_map.put(PRO_QUANTITY, String.valueOf(productModels.get(i).getQty()));
            products_list.add(i,P_map);
            product_database.insert_products_item(products_list);
        }
        Log.e("vvvytyty", "data_insert: " + products_list.size());
        for (int i=0;i<products_list.size();i++) {
            Log.e("vvvytyty", "data_insert: " + products_list.get(i).get(PRO_ID));
        }
    }


    private void getproduRecyclerview() throws JSONException {

        Log.e("prooo", "getproduRecyclerview: "+product_database);
        if ( product_database.getproducts().size()==0) {
            Log.e("bhbbbx", "getproduRecyclerview: "+"MSnxNS" );
        productModels.clear();
        productModels.add(new ProductModel(R.drawable.mens,"Mens new Tshirt","463","463 with exciting offers","Free Delivery","3","1",11,0));
        productModels.add(new ProductModel(R.drawable.kids2,"Kids tshirt","463","463 with exciting offers","Free Delivery","3.9","0",12,0));
        productModels.add(new ProductModel(R.drawable.nike2,"Nike air jordan","463","463 with exciting offers","Free Delivery","4","0",13,0));
        productModels.add(new ProductModel(R.drawable.games,"GTA 5","463","463 with exciting offers","Free Delivery","5","0",14,0));
        productModels.add(new ProductModel(R.drawable.saree,"Saree collection","463","463 with exciting offers","Free Delivery","3.4","1",15,0));
        productModels.add(new ProductModel(R.drawable.women2,"Mens new Tshirt","463","463 with exciting offers","Free Delivery","5","0",16,0));
        productModels.add(new ProductModel(R.drawable.western,"Mens new Tshirt","463","463 with exciting offers","Free Delivery","3","2",17,0));
        productModels.add(new ProductModel(R.drawable.home2,"Mens new Tshirt","463","463 with exciting offers","Free Delivery","3.1","1",18,0));
        productModels.add(new ProductModel(R.drawable.salemeesho,"Mens new Tshirt","36","463 with exciting offers","Free Delivery","3.2","1",19,0));
        productModels.add(new ProductModel(R.drawable.women2,"Wemens new Tshirt","63","463 with exciting offers","Free Delivery","3.3","1",20,0));
        productModels.add(new ProductModel(R.drawable.women2,"Wemens new Tshirt","46","463 with exciting offers","Free Delivery","4.1","0",21,0));
        productModels.add(new ProductModel(R.drawable.women2,"Wemens new Tshirt","43","463 with exciting offers","Free Delivery","4.2","0",22,0));
        getdata();
        productAdapter = new ProductAdapter(getActivity(), productModels,new ProductAdapter.onAdapterclicklister() {
            @Override


            public void itemview(int position) {
                String pro_name= productModels.get(position).getProduct_name();
                Bundle p_bundle= new Bundle();
                ProductViewFragment fragment= new ProductViewFragment();
                Log.e("kokokk", "itemview: "+String.valueOf(productModels.get(position).getIv_product()));
                p_bundle.putString("pro_image", String.valueOf(productModels.get(position).getIv_product()));
                p_bundle.putString("pro_name",pro_name);
                p_bundle.putString("pros_quanntiyt", String.valueOf(productModels.get(position).getQty()));
                p_bundle.putString("getPrice",productModels.get(position).getPrice());
                p_bundle.putString("Offers",productModels.get(position).getOffers());
                p_bundle.putString("delivery",productModels.get(position).getDeliver_status());
                p_bundle.putString("rating",productModels.get(position).getRating());
                p_bundle.putString("status",productModels.get(position).getStatus());
                p_bundle.putString("id",String.valueOf(productModels.get(position).getId()));
                fragment.setArguments(p_bundle);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.conatiner,fragment).addToBackStack(null).commit();

            }
        });
        products.setAdapter(productAdapter);

        }

        else  {
            Log.e("bhbbbx", "getproduRecyclerview: "+"lllllll");
                products_list = product_database.getproducts();
            Log.e("vtyvv", "getproduRecyclerview: "+products_list.size() );
            ArrayList<HashMap<String, String>> jsonObject = products_list;
            Log.e("JSONIII", "getproduRecyclerview: " + jsonObject);
            JSONArray jsonArray = new JSONArray(jsonObject);
            for (int i=0;i<jsonObject.size();i++){
                productModel = new ProductModel();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                productModel.setId(jsonObject1.getInt("prod_id"));
                productModel.setIv_product(jsonObject1.getInt("product_image"));
                productModel.setProduct_name(jsonObject1.getString("product_name"));
                productModel.setPrice(jsonObject1.getString("product_price"));
                productModel.setOffers((String) jsonObject1.get("product_offer"));
                productModel.setDeliver_status(jsonObject1.getString("prod_del"));
                productModel.setRating(jsonObject1.getString("prod_rat"));
                productModel.setStatus(jsonObject1.getString("prod_status"));
                productModel.setQty(jsonObject1.getInt("product_quantity"));
                productModels.add(productModel);
            }
            productAdapter = new ProductAdapter(getActivity(), productModels,new ProductAdapter.onAdapterclicklister() {
                @Override


                public void itemview(int position) {
                    String pro_name= productModels.get(position).getProduct_name();
                    Bundle p_bundle= new Bundle();
                    ProductViewFragment fragment= new ProductViewFragment();
                    Log.e("kokokk", "itemview: "+String.valueOf(productModels.get(position).getIv_product()));
                    p_bundle.putString("pro_image", String.valueOf(productModels.get(position).getIv_product()));
                    p_bundle.putString("pro_name",pro_name);
                    p_bundle.putString("pros_quanntiyt", String.valueOf(productModels.get(position).getQty()));
                    p_bundle.putString("getPrice",productModels.get(position).getPrice());
                    p_bundle.putString("Offers",productModels.get(position).getOffers());
                    p_bundle.putString("delivery",productModels.get(position).getDeliver_status());
                    p_bundle.putString("rating",productModels.get(position).getRating());
                    p_bundle.putString("status",productModels.get(position).getStatus());
                    p_bundle.putString("id",String.valueOf(productModels.get(position).getId()));
                    fragment.setArguments(p_bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.conatiner,fragment).addToBackStack(null).commit();

                }
            });
            products.setAdapter(productAdapter);
            }


        }


        private void getProdtRecyclerview() {
            homeModel2s.clear();
            homeModel2s.add(new HomeModel2(R.drawable.categories1,"categories"));
            homeModel2s.add(new HomeModel2(R.drawable.suit,"Kurti & Suit"));
            homeModel2s.add(new HomeModel2(R.drawable.western,"Western"));
            homeModel2s.add(new HomeModel2(R.drawable.men2,"Mens"));
            homeModel2s.add(new HomeModel2(R.drawable.accsessories,"Accessories"));
            homeModel2s.add(new HomeModel2(R.drawable.jwellery,"Jwellery"));
            homeModel2s.add(new HomeModel2(R.drawable.games,"Games"));
            homeModel2s.add(new HomeModel2(R.drawable.saree,"Saree"));
            homeModel2s.add(new HomeModel2(R.drawable.po,"Electronics"));
            homeModel2s.add(new HomeModel2(R.drawable.nike2,"Footwear"));
            adapterHome2 = new AdapterHome2(getActivity(),homeModel2s);
            rec_category.setAdapter(adapterHome2);
        }

        private void getRecyclerview() {
            lookingModels.clear();
            lookingModels.add(new LookingModel(R.drawable.men2,R.drawable.baseline_face_6_24,"Men >","1"));
            lookingModels.add(new LookingModel(R.drawable.women2,R.drawable.baseline_face_3_24,"Women >","2"));
            lookingModels.add(new LookingModel(R.drawable.kids2,R.drawable.baseline_face_retouching_natural_24,"Kids >","3"));
            lookingModels.add(new LookingModel(R.drawable.home2,R.drawable.baseline_emoji_food_beverage_24,"Home >","4"));
            lookingModels.add(new LookingModel(R.drawable.po,R.drawable.baseline_camera_alt_24,"Electronics >","5"));
            lookingAdapter =new LookingAdapter(getActivity(), lookingModels, new LookingAdapter.OnAdapterclickListner() {
                @Override
                public void itemview(int position) {
                    String titel = String.valueOf(lookingModels.get(position).getImageview1());
                    MensFragment fragment =new MensFragment();
                    Bundle args = new Bundle();
                    args.putString("item_id",lookingModels.get(position).getId());

                    fragment.setArguments(args); getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conatiner,fragment).addToBackStack(null).commit();

                }

            });
            rec_grid.setAdapter(lookingAdapter);

        }

    }
