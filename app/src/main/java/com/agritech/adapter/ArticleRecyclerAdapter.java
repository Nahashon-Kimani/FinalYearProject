package com.agritech.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.activity.ReadArticle;
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
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.articleTitle.setText(articlesList.get(i).getArticleTitle());
        myHolder.articleDesc.setText(articlesList.get(i).getArticleDesc());

        //Share the article
        myHolder.shareArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, articlesList.get(i).getArticleTitle() + "\n"
                                + articlesList.get(i).getArticleDesc());
                intent.setType("text/plain");
                v.getContext().startActivity(Intent.createChooser(intent, "Share" + articlesList.get(i).getArticleTitle()));
            }
        });

        //open a new Activity to read the whole article
        myHolder.articleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadArticle.class);
                intent.putExtra("title", articlesList.get(i).getArticleTitle());
                intent.putExtra("desc", articlesList.get(i).getArticleDesc());
                Toast.makeText(context, articlesList.get(i).getArticleTitle() + articlesList.get(i).getArticleDesc(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView shareArticle;
        TextView articleTitle, articleDesc;
        CardView articleCardView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            shareArticle = itemView.findViewById(R.id.share_article);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleDesc = itemView.findViewById(R.id.article_desc);
            articleCardView = itemView.findViewById(R.id.article_card_view);
        }
    }
}
