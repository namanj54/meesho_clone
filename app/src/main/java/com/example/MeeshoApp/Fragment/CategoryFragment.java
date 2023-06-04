package com.example.MeeshoApp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.MeeshoApp.Adapter.AdapterHome2;
import com.example.MeeshoApp.Adapter.Category2Adapter;
import com.example.MeeshoApp.Adapter.CategoryAdapter;
import com.example.MeeshoApp.Model.CAT2Model;
import com.example.MeeshoApp.Model.CATMODEL;
import com.example.MeeshoApp.Model.HomeModel2;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    RecyclerView cat_frg_rec,cat_frg_rec2;

    ArrayList<CATMODEL> cat_model = new ArrayList<>();

    ArrayList<CAT2Model> cat2Models = new ArrayList<>();
    CategoryAdapter categoryAdapter;


    public CategoryFragment() {

    }


    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        cat_frg_rec = view.findViewById(R.id.cat_name);
        cat_frg_rec.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        get_cat_rec_view();

        cat_frg_rec2 = view.findViewById(R.id.cat_rec_2);
        cat_frg_rec2.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        get_cat_rec_2_view();
    }

    private void get_cat_rec_2_view() {
        cat2Models.clear();
        cat2Models.add(new CAT2Model(R.drawable.fila,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.nike2,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.paysecure,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.addidas,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.app_icon,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.amazon,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.games,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.like,"cdndcn"));
        cat2Models.add(new CAT2Model(R.drawable.pytm,"cdndcn"));

       Category2Adapter category2Adapter = new Category2Adapter(getActivity(),cat2Models);
        cat_frg_rec2.setAdapter(category2Adapter);
    }

    private void get_cat_rec_view() {
        cat_model.clear();
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.kids2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.nike2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.nikelogo,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.kids2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.addidas,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.fila,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.free,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.checkshirt,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.app_icon,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.western,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.saree,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.kids2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.nike2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.nikelogo,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.kids2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.home2,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));
        cat_model.add(new CATMODEL(R.drawable.accsessories,"Womens"));


        categoryAdapter = new CategoryAdapter(getActivity(),cat_model);
        cat_frg_rec.setAdapter(categoryAdapter);
    }
}