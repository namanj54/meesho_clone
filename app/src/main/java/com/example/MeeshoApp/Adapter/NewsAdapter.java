package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Model.NewsArticle;
import com.example.MeeshoApp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static String TAG="ArticleAdapter";

    private ArrayList<NewsArticle> mArrayList;
    private Context mContext;

    public NewsAdapter(Context context,ArrayList<NewsArticle> list){
        // initializing the constructor
        this.mContext=context;
        this.mArrayList=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.items_news,parent,false);
        return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsArticle currentArticle=mArrayList.get(position);
        holder.title.setText(currentArticle.getTitle());
        holder.description.setText(currentArticle.getDescription());
        Picasso.get().load(currentArticle.getUrlToImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
            description=itemView.findViewById(R.id.description_id);
            image=itemView.findViewById(R.id.image_id);
        }
    }
}
