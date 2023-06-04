package com.example.MeeshoApp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.MeeshoApp.Adapter.MyorderAdapter;
import com.example.MeeshoApp.Databases.My_orderdatabse;
import com.example.MeeshoApp.R;
import java.util.ArrayList;
import java.util.HashMap;


public class My_orderFragment extends Fragment {

    ArrayList<HashMap<String, String>> myorder = new ArrayList<>();
    RecyclerView myorder_rec;

    My_orderdatabse my_orderdatabse;


    public My_orderFragment() {

    }


    public static My_orderFragment newInstance() {
        My_orderFragment fragment = new My_orderFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        my_orderdatabse = new My_orderdatabse(getActivity());
        myorder = my_orderdatabse.getorderdata();
        myorder_rec = view.findViewById(R.id.my_order_rec);
        myorder_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getorderrec();

    }

    private void getorderrec() {
        if (myorder.size() > 0) {
            MyorderAdapter myorderAdapter = new MyorderAdapter(getActivity(),myorder); {

            };
            myorder_rec.setAdapter(myorderAdapter);
            myorderAdapter.notifyDataSetChanged();

        }
        else {

        }
    }
}