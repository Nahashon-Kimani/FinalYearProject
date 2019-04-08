package com.agritech.drawerFragment;

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

public class Processing extends Fragment {
    View view;
    RecyclerView processRecyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<ArticlesModel> articlesModelArrayList = new ArrayList<>();

    public Processing() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        processRecyclerView = view.findViewById(R.id.article_recycler_view);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Processing");

     mRef.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                 articlesModelArrayList.add(snapshot.getValue(ArticlesModel.class));
             }
             LinearLayoutManager manager = new LinearLayoutManager(getContext());
             manager.setOrientation(LinearLayoutManager.VERTICAL);
             processRecyclerView.setLayoutManager(manager);
             processRecyclerView.setAdapter(new ArticleRecyclerAdapter(getActivity(), articlesModelArrayList));
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {
             Toast.makeText(getActivity(), "Sorry an Error occurred"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });



        return view;
    }
}
