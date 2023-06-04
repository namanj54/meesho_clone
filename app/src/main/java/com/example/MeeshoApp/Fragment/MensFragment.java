package com.example.MeeshoApp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MeeshoApp.Adapter.AdapterHome2;
import com.example.MeeshoApp.Adapter.BrandAdapter;
import com.example.MeeshoApp.Adapter.Men_catAdapter;
import com.example.MeeshoApp.Adapter.ProductAdapter;
import com.example.MeeshoApp.Adapter.SummerAdapter;
import com.example.MeeshoApp.Adapter.WeddingAdapter;
import com.example.MeeshoApp.Model.BrandModel;
import com.example.MeeshoApp.Model.HomeModel2;
import com.example.MeeshoApp.Model.MenModel;
import com.example.MeeshoApp.Model.ProductModel;
import com.example.MeeshoApp.Model.SummerModel;
import com.example.MeeshoApp.Model.WedModel;
import com.example.MeeshoApp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MensFragment#newInstance} factory method to
  create an instance of this fragment.
 */
public class MensFragment extends Fragment {


    Men_catAdapter men_catAdapter;
    WeddingAdapter weddingAdapter;

    ProductAdapter mencatAdapter;

    SummerAdapter summerAdapter;
    BrandAdapter brandAdapter;

    AdapterHome2 menpg_Adapter;


    RecyclerView men_cat, wed_cat, summer_rec, brand_rec, mens_prod, menpgcat_rec;
    ArrayList<MenModel> menModels = new ArrayList<>();

    ArrayList<WedModel> wedModels = new ArrayList<>();

    ArrayList<SummerModel> summerModels = new ArrayList<>();

    ArrayList<BrandModel> brandModels = new ArrayList<>();


    ArrayList<ProductModel> men_productmodel = new ArrayList<>();

    ArrayList<HomeModel2> men_cat_model = new ArrayList<>();
    String it_id="";

    public MensFragment() {

    }

    public static MensFragment newInstance(String param1, String param2) {
        MensFragment fragment = new MensFragment();
        Bundle args = new Bundle();
        ;

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

        View view = inflater.inflate(R.layout.fragment_mens, container, false);
        initallID(view);
        return view;
    }

    private void initallID(View view) {
        it_id=getArguments().getString("item_id");

        men_cat = view.findViewById(R.id.mens_cat);
        men_cat.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        getmencatview();

        wed_cat = view.findViewById(R.id.wed_rec);
        wed_cat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getwedcatview();

        summer_rec = view.findViewById(R.id.summer_rec);
        summer_rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getsummerrecview();


        brand_rec = view.findViewById(R.id.brands_rec);
        brand_rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getbrandsrecview();


        mens_prod = view.findViewById(R.id.mensprod);
        mens_prod.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getmenprodview();

        menpgcat_rec = view.findViewById(R.id.mencat_rec);
        menpgcat_rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getmenpg_catview();

    }


    private void getmenpg_catview() {
        men_cat_model.clear();
        men_cat_model.add(new HomeModel2(R.drawable.categories1, "categories"));
        men_cat_model.add(new HomeModel2(R.drawable.suit, "Kurti & Suit"));
        men_cat_model.add(new HomeModel2(R.drawable.western, "Western"));
        men_cat_model.add(new HomeModel2(R.drawable.men2, "Mens"));
        men_cat_model.add(new HomeModel2(R.drawable.accsessories, "Accessories"));
        men_cat_model.add(new HomeModel2(R.drawable.jwellery, "Jwellery"));
        men_cat_model.add(new HomeModel2(R.drawable.games, "Games"));
        men_cat_model.add(new HomeModel2(R.drawable.saree, "Saree"));
        men_cat_model.add(new HomeModel2(R.drawable.po, "Electronics"));
        men_cat_model.add(new HomeModel2(R.drawable.nike2, "Footwear"));

        menpg_Adapter = new AdapterHome2(getActivity(), men_cat_model);
            menpgcat_rec.setAdapter(menpg_Adapter);

    }

    private void getmenprodview() {
        men_productmodel.clear();

        if (it_id.equalsIgnoreCase("1")) {
            men_productmodel.add(new ProductModel(R.drawable.tshirt, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3", "1",1,0));
            men_productmodel.add(new ProductModel(R.drawable.checkshirt, "Mens new checkshirt", "463", "463 with exciting offers", "Free Delivery", "3.9", "0",2,0));
            men_productmodel.add(new ProductModel(R.drawable.nike2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4", "0",3,0));
            men_productmodel.add(new ProductModel(R.drawable.games, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "5", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.saree, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.4", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "5", "0",6,0));
            men_productmodel.add(new ProductModel(R.drawable.western, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3", "2",7,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));

        } else if (it_id.equalsIgnoreCase("2")) {
            men_productmodel.add(new ProductModel(R.drawable.women2, "weMens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",1,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "weMens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",1,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "weMens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",12,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));
        }
        else if (it_id.equalsIgnoreCase("3")) {
            men_productmodel.add(new ProductModel(R.drawable.women2, "kids new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",13,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",14,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",15,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));
        }
        else if (it_id.equalsIgnoreCase("4")) {
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",16,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",17,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",18,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));
        }
        else if (it_id.equalsIgnoreCase("5")) {
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",19,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",20,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",21,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));
        }
        else {
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",51,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",52,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",53,0));
            men_productmodel.add(new ProductModel(R.drawable.home2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.1", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.salemeesho, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.2", "1",8,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "3.3", "1",5,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.1", "0",4,0));
            men_productmodel.add(new ProductModel(R.drawable.women2, "Mens new Tshirt", "463", "463 with exciting offers", "Free Delivery", "4.2", "0",3,0));
        }


        mencatAdapter = new ProductAdapter(getActivity(), men_productmodel, new ProductAdapter.onAdapterclicklister() {


            @Override
            public void itemview(int position) {
                String pro_name= men_productmodel.get(position).getProduct_name();
                Bundle bundle= new Bundle();
                ProductViewFragment fragment= new ProductViewFragment();
                Log.e("kokokk", "itemview: "+String.valueOf(men_productmodel.get(position).getIv_product()));
                bundle.putString("pro_image", String.valueOf(men_productmodel.get(position).getIv_product()));
                bundle.putString("pro_name",pro_name);
                bundle.putString("getPrice",men_productmodel.get(position).getPrice());
                bundle.putString("Offers",men_productmodel.get(position).getOffers());
                bundle.putString("delivery",men_productmodel.get(position).getDeliver_status());
                bundle.putString("rating",men_productmodel.get(position).getRating());
                bundle.putString("status",men_productmodel.get(position).getStatus());
                bundle.putString("pros_quanntiyt", String.valueOf(men_productmodel.get(position).getQty()));
                bundle.putString("id",String.valueOf(men_productmodel.get(position).getId()));
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.conatiner,fragment).addToBackStack(null).commit();
            }
        });
        mens_prod.setAdapter(mencatAdapter);
    }
    private void getsummerrecview() {
        summerModels.clear();
        if (it_id.equalsIgnoreCase("1")) {
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
        } else if (it_id.equalsIgnoreCase("2")) {
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
        }
        else if (it_id.equalsIgnoreCase("3")) {
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
        }
        else if (it_id.equalsIgnoreCase("4")) {
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
        }
        else if (it_id.equalsIgnoreCase("5")) {
            summerModels.add(new SummerModel(R.drawable.nike2, "bye"));
        }


        summerAdapter = new SummerAdapter(getActivity(), summerModels, "other");
            summer_rec.setAdapter(summerAdapter);

    }

    private void getbrandsrecview() {
         brandModels.clear();
         if (it_id.equalsIgnoreCase("1")) {
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
         } else if (it_id.equalsIgnoreCase("2")) {
                 brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
                 brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
                 brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
                 brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
                 brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
                 brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
                 brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
                 brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));

             }else if (it_id.equalsIgnoreCase("2")) {
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));

         }else if (it_id.equalsIgnoreCase("2")) {
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));

         }else if (it_id.equalsIgnoreCase("2")) {
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
             brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
             brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
             brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
             brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
         }
         else {
                 brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
                 brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
                 brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
                 brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));
                 brandModels.add(new BrandModel(R.drawable.nikelogo, "Nike"));
                 brandModels.add(new BrandModel(R.drawable.addidas, "Addidas"));
                 brandModels.add(new BrandModel(R.drawable.fila, "Fila"));
                 brandModels.add(new BrandModel(R.drawable.reebok, "Reebok"));

             }
        brandAdapter = new BrandAdapter(getActivity(),brandModels,"other");
        brand_rec.setAdapter(brandAdapter);
    }
    private void getwedcatview() {
        wedModels.clear();
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));
        wedModels.add(new WedModel(R.drawable.men2,"hello"));

        weddingAdapter = new WeddingAdapter(getActivity(),wedModels,"other");
        wed_cat.setAdapter(weddingAdapter);

    }

    private void getmencatview() {
        menModels.clear();

        if (it_id.equalsIgnoreCase("1")) {
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
        } else if (it_id.equalsIgnoreCase("2")) {
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));

        }
        else if (it_id.equalsIgnoreCase("3")) {
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));

        }
        else if (it_id.equalsIgnoreCase("4")) {
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));

        }
        else if (it_id.equalsIgnoreCase("5")) {
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
            menModels.add(new MenModel(R.drawable.gift, "gifting"));
            menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
            menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));

        }
        else {
                menModels.add(new MenModel(R.drawable.gift, "gifting"));
                menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
                menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
                menModels.add(new MenModel(R.drawable.gift, "gifting"));
                menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
                menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));
                menModels.add(new MenModel(R.drawable.gift, "gifting"));
                menModels.add(new MenModel(R.drawable.checkshirt, "shirts"));
                menModels.add(new MenModel(R.drawable.tshirt, "tshirts"));

            }



        men_catAdapter = new Men_catAdapter(getActivity(),menModels,"other");
        men_cat.setAdapter(men_catAdapter);
    }
}