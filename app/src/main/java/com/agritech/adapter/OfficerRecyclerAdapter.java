package com.agritech.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.OfficerModel;

import java.util.ArrayList;

public class OfficerRecyclerAdapter extends RecyclerView.Adapter<OfficerRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<OfficerModel> officerModelList = new ArrayList<>();

    public OfficerRecyclerAdapter(Context context, ArrayList<OfficerModel> officerModelList) {
        this.context = context;
        this.officerModelList = officerModelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.officer_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.officerName.setText(officerModelList.get(i).getOfficerName());
        myHolder.officerLocation.setText(officerModelList.get(i).getOfficerLocation());
        myHolder.officerSubCunty.setText(officerModelList.get(i).getOfficerSubCounty());
        myHolder.officerCounty.setText(officerModelList.get(i).getOfficerCounty());
        myHolder.officerTelNo.setText(officerModelList.get(i).getOfficerPhoneNo());

        myHolder.callOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(officerModelList.get(i).getOfficerPhoneNo()));
                v.getContext().startActivity(intent);*/
                v.getContext().startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.fromParts("tel", officerModelList.get(i).getOfficerPhoneNo(), null)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return officerModelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView officerName, officerLocation, officerSubCunty, officerCounty, officerTelNo;
        CardView callOfficer;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            callOfficer = itemView.findViewById(R.id.call_officer);
            officerName = itemView.findViewById(R.id.officer_name);
            officerLocation = itemView.findViewById(R.id.officer_location);
            officerSubCunty = itemView.findViewById(R.id.officer_sub_county);
            officerCounty = itemView.findViewById(R.id.officer_county);
            officerTelNo = itemView.findViewById(R.id.phone_no);

        }
    }
}
