package com.agritech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agritech.R;
import com.agritech.model.ArticlesModel;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<ArticlesModel> articlesList = new ArrayList<>();

    public ArticleRecyclerAdapter(Context context, ArrayList<ArticlesModel> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.crop_production_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.articleTitle.setText(articlesList.get(i).getArticleTitle());
        myHolder.articleDesc.setText(articlesList.get(i).getArticleDesc());

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView shareArticle;
        TextView articleTitle, articleDesc;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            shareArticle = itemView.findViewById(R.id.share_article);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleDesc = itemView.findViewById(R.id.article_desc);

        }
    }
}
