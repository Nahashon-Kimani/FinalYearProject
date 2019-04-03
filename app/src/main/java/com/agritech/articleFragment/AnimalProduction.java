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

public class AnimalProduction extends Fragment {
    View view;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<ArticlesModel> modelArrayList = new ArrayList<>();
    RecyclerView animalRecyclerView;

    public AnimalProduction() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        animalRecyclerView = view.findViewById(R.id.article_recycler_view);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Animal Production");

       /* ArticlesModel model = new ArticlesModel("Title", "Description of the topic under the title","Animal Production", "1/9/2019");
        mRef.push().setValue(model);*/

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    modelArrayList.add(snapshot.getValue(ArticlesModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                animalRecyclerView.setLayoutManager(manager);
                animalRecyclerView.setAdapter(new ArticleRecyclerAdapter(getContext(), modelArrayList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "An Error occurred" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
