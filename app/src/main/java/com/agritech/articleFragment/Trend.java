package com.agritech.articleFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.adapter.ArticleRecyclerAdapter;
import com.agritech.model.ArticlesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Trend extends Fragment {
    View view;
    DatabaseReference mRef;
    ArrayList<ArticlesModel> list = new ArrayList<>();
    RecyclerView trendRecyclerView;
    public Trend() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        mRef = FirebaseDatabase.getInstance().getReference().child("Trend");
        trendRecyclerView = view.findViewById(R.id.article_recycler_view);
        /*ArticlesModel articlesModel = new ArticlesModel("Trending", "This is what is trending in the world of Agriculture",
                "Trends", "12/09/2019");
        mRef.push().setValue(articlesModel);*/

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue(ArticlesModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                trendRecyclerView.setLayoutManager(manager);
                trendRecyclerView.setAdapter(new ArticleRecyclerAdapter(getContext(), list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "An Error occurred"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
