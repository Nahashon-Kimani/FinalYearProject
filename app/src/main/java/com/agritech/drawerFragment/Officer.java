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

import com.agritech.R;
import com.agritech.adapter.OfficerRecyclerAdapter;
import com.agritech.model.OfficerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Officer extends Fragment {
    View view;
    RecyclerView officerRecyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<OfficerModel>officerList = new ArrayList<>();


    public Officer() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        officerRecyclerView = view.findViewById(R.id.article_recycler_view);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Contact Officer");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot :dataSnapshot.getChildren() ) {
                    officerList.add(snapshot.getValue(OfficerModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                officerRecyclerView.setLayoutManager(manager);
                officerRecyclerView.setAdapter(new OfficerRecyclerAdapter(getContext(), officerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}