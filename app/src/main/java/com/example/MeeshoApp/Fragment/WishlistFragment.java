package com.example.MeeshoApp.Fragment;

import static com.example.MeeshoApp.common.constant.USERNAME_KEY;
import static com.example.MeeshoApp.common.constant.WISH_COLOUMN;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Activity.MainActivity;
import com.example.MeeshoApp.Adapter.WishlistAdapter;
import com.example.MeeshoApp.Databases.Wishlist_database;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.SessionManagement;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {
    ArrayList<HashMap<String,String>> map= new ArrayList<>();
  RecyclerView wish_recycler;

String username;
Wishlist_database wishlist_database;

 WishlistAdapter wishlistAdapter;

 ImageView iv_empwish;


 SessionManagement sessionManagement;




// int col;




    public WishlistFragment() {

    }


    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
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
        View view =  inflater.inflate(R.layout.fragment_wishlist, container, false);
        initid(view);
        return view;
    }

    private void initid(View view) {


        iv_empwish = view.findViewById(R.id.iv_empwish1);

        wishlist_database = new Wishlist_database(getActivity());
        sessionManagement = new SessionManagement(getActivity());




        wish_recycler = view.findViewById(R.id.wish_rec);
        wish_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
         getwishview();
         wish_visiblity();


         username = sessionManagement.getdata().get(USERNAME_KEY);



    }

//    public void wish_count() {
//        if (wishlist_database!= null) {
//
//            wishdata = wishlist_database.getWishList();
//            if (wishdata.size() > 0) {
//
//                for (int i = 0; i < wishdata.size(); i++) {
//                    col += Integer.parseInt(wishdata.get(i).get(WISH_COLOUMN));
//                }
//            }
//        }
//
//    }

    public void wish_visiblity(){

        MainActivity.wish_count();
    }

    private void getwishview() {


        map=wishlist_database.getWishList();

        if(map.size()>0){
           wishlistAdapter= new WishlistAdapter(getActivity(), map, new WishlistAdapter.OnAdapterclickListner() {
               @Override
               public void onItemClick(int position, ArrayList<HashMap<String, String>> wishlistModels) {
                   HashMap<String,String> cmap = wishlistModels.get(position);
                   String remove = cmap.get("wish_name");
                   wishlist_database.removewish(String.valueOf(wishlistModels.get(position).get(WISH_COLOUMN)));
                   wishlistAdapter.notifyDataSetChanged();
                   map.remove(position);
                   wish_visiblity();
                   if (map.isEmpty()) {
                       wish_recycler.setVisibility(View.GONE);
                       iv_empwish.setVisibility(View.VISIBLE);
                   }
               }
           });
           wishlistAdapter.notifyDataSetChanged();
            wish_recycler.setVisibility(View.VISIBLE);
            wish_recycler.setAdapter(wishlistAdapter);


        }
        else {

            iv_empwish.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "no items", Toast.LENGTH_SHORT).show();
        }





    }
}