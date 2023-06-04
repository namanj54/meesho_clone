package com.example.MeeshoApp.Fragment;

import static android.content.ContentValues.TAG;

import static com.squareup.picasso.Picasso.Priority.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.MeeshoApp.Adapter.AdapterHome2;
import com.example.MeeshoApp.Adapter.MyorderAdapter;
import com.example.MeeshoApp.Adapter.NewsAdapter;
import com.example.MeeshoApp.Model.MyorderModel;
import com.example.MeeshoApp.Model.NewsArticle;
import com.example.MeeshoApp.R;
import com.example.MeeshoApp.common.RetrofitClient;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Priority;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    RecyclerView myorder_rec;
    ArrayList<NewsArticle> mArticleList = new ArrayList<>();
    private static  String API_KEY = "14303fdf2d1c443186330d02d5d967ad";

    public NewsFragment() {

    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        AndroidNetworking.initialize(getActivity());

        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        myorder_rec = view.findViewById(R.id.news_rec);
        myorder_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getSuperHeroes();
    }

    private void getSuperHeroes() {

            mArticleList.clear();


            AndroidNetworking.get("https://newsapi.org/v2/top-headlines")
                    .addQueryParameter("country", "in")
                    .addQueryParameter("apiKey",API_KEY)
                    .addHeaders("token", "1234")
                    .setTag("test")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener(){
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray articles=response.getJSONArray("articles");
                                for (int j=0;j<articles.length();j++)
                                {

                                    JSONObject article=articles.getJSONObject(j);

                                    NewsArticle currentArticle=new NewsArticle();

                                    String author=article.getString("author");
                                    String title=article.getString("title");
                                    String description=article.getString("description");
                                    String url=article.getString("url");
                                    String urlToImage=article.getString("urlToImage");
                                    String publishedAt=article.getString("publishedAt");
                                    String content=article.getString("content");

                                    currentArticle.setAuthor(author);
                                    currentArticle.setTitle(title);
                                    currentArticle.setDescription(description);
                                    currentArticle.setUrl(url);
                                    currentArticle.setUrlToImage(urlToImage);
                                    currentArticle.setPublishedAt(publishedAt);
                                    currentArticle.setContent(content);

                                    mArticleList.add(currentArticle);
                                }
                                NewsAdapter newsAdapter = new NewsAdapter(getActivity(),mArticleList);
                                myorder_rec.setAdapter(newsAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(TAG,"Error : "+e.getMessage());
                            }

                        }

                        @Override
                        public void onError(ANError anError) {

                        }

                    });
        }
    }


