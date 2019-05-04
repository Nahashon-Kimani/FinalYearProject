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

import com.agritech.R;
import com.agritech.adapter.ArticleRecyclerAdapter;
import com.agritech.model.ArticlesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnimalProduction extends Fragment {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    View view;
    RecyclerView cropRecyclerView;
    ArrayList<ArticlesModel> modelArrayList = new ArrayList<>();
    ArticlesModel articlesModel;

    public AnimalProduction() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        cropRecyclerView = view.findViewById(R.id.article_recycler_view);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Animal Production");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren() ) {
                    modelArrayList.add(snapshot.getValue(ArticlesModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                cropRecyclerView.setLayoutManager(manager);
                cropRecyclerView.setAdapter(new ArticleRecyclerAdapter(getActivity(), modelArrayList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
