package com.agritech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.EventModel;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();

    public EventRecyclerAdapter(Context context, ArrayList<EventModel> eventModelArrayList) {
        this.context = context;
        this.eventModelArrayList = eventModelArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.event_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.eventTitle.setText(eventModelArrayList.get(i).getEventTitle());
        myHolder.eventDate.setText(eventModelArrayList.get(i).getEventDate());
        myHolder.eventLocation.setText(eventModelArrayList.get(i).getEventLocation());
        myHolder.eventEntryFee.setText(eventModelArrayList.get(i).getEventFee());
        myHolder.eventStatus.setText(eventModelArrayList.get(i).getEventStatus());
        myHolder.shareEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share Event", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView eventTitle, eventDate, eventLocation, eventEntryFee, eventStatus;
        ImageView shareEvent;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventDate = itemView.findViewById(R.id.event_date);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventEntryFee = itemView.findViewById(R.id.event_entry_fee);
            eventStatus = itemView.findViewById(R.id.event_status);
            shareEvent = itemView.findViewById(R.id.share_event);
        }
    }
}
