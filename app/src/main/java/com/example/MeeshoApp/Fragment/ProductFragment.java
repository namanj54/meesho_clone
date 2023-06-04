package com.example.MeeshoApp.Fragment;

import static com.example.MeeshoApp.common.constant.PRO_DELIEVERY;
import static com.example.MeeshoApp.common.constant.PRO_ID;
import static com.example.MeeshoApp.common.constant.PRO_IMAGE;
import static com.example.MeeshoApp.common.constant.PRO_NAME;
import static com.example.MeeshoApp.common.constant.PRO_OFFERS;
import static com.example.MeeshoApp.common.constant.PRO_PRICE;
import static com.example.MeeshoApp.common.constant.PRO_QUANTITY;
import static com.example.MeeshoApp.common.constant.PRO_RATING;
import static com.example.MeeshoApp.common.constant.PRO_SATUS;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Adapter.ProductAdapter;
import com.example.MeeshoApp.Databases.Product_database;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductFragment extends Fragment {
    ProductModel productModel;
    ArrayList<HashMap<String, String>> prodmap = new ArrayList<>();

    RecyclerView rec_products_search;

    ArrayList<ProductModel> productModels = new ArrayList<>();
    ProductAdapter productAdapter;

    SearchView searchView;

    Product_database product_database;

    String prods_id;

    public ProductFragment() {

    }



    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        try {
            init_id(view);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    private void init_id(View view) throws JSONException {

        product_database = new Product_database(getActivity());
        searchView =((MainActivity) getActivity()).search_view_id();
        rec_products_search = view.findViewById(R.id.products_search);
        rec_products_search.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        data_post();

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
    private void data_post() throws JSONException {
        if (product_database != null) {
            prodmap = product_database.getproducts();
        }
        ArrayList<HashMap<String, String>> jsonObject = prodmap;
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
            productModels.add(productModel);
        }
        productAdapter = new ProductAdapter(getActivity(), productModels,new ProductAdapter.onAdapterclicklister() {
            @Override

            public void itemview(int position) {
                    Bundle p_bundle = new Bundle();
                    ProductViewFragment fragment = new ProductViewFragment();
                    p_bundle.putString("pro_image", prodmap.get(position).get(PRO_IMAGE));
                    p_bundle.putString("pro_name", prodmap.get(position).get(PRO_NAME));
                    p_bundle.putString("pros_quanntiyt", prodmap.get(position).get(PRO_QUANTITY));
                    p_bundle.putString("getPrice", prodmap.get(position).get(PRO_PRICE));
                    p_bundle.putString("Offers", prodmap.get(position).get(PRO_OFFERS));
                    p_bundle.putString("delivery", prodmap.get(position).get(PRO_DELIEVERY));
                    p_bundle.putString("rating", prodmap.get(position).get(PRO_RATING));
                    p_bundle.putString("status", prodmap.get(position).get(PRO_SATUS));
                    p_bundle.putString("id", prodmap.get(position).get(PRO_ID));
                    fragment.setArguments(p_bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.conatiner, fragment).addToBackStack(null).commit();
            }
        });
        rec_products_search.setAdapter(productAdapter);

    }
}