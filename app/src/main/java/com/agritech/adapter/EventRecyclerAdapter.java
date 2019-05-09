package com.agritech.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.activity.Register;
import com.agritech.model.EventModel;
import com.agritech.model.RegisterEvent;
import com.agritech.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyHolder> {
    Context context;
    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();
    String sender;

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
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.eventTitle.setText(eventModelArrayList.get(i).getEventTitle());
        myHolder.eventDate.setText(eventModelArrayList.get(i).getEventDate());
        myHolder.eventLocation.setText(eventModelArrayList.get(i).getEventLocation());
        myHolder.eventEntryFee.setText(eventModelArrayList.get(i).getEventFee());
        myHolder.eventStatus.setText(eventModelArrayList.get(i).getEventStatus());
        myHolder.shareEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT,
                                "You are invited to attend " + eventModelArrayList.get(i).getEventTitle() +
                                        "on \\s" + eventModelArrayList.get(i).getEventDate() + "event \\s" +
                                        "at\\s " + eventModelArrayList.get(i).getEventLocation() + ".");
                intent.setType("text/plain");
                v.getContext().startActivity(Intent.createChooser(intent, eventModelArrayList.get(i).getEventTitle()));

            }
        });


            myHolder.eventRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("Users").child(userID)
                                .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren()                                    ) {
                                    UserDetails details = snapshot.getValue(UserDetails.class);
                                     sender = details.getName().toUpperCase();
                                }

                                //eventTitleTextView.setText(noticeAdapter.getNoticeTitle());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                    RegisterEvent event = new RegisterEvent("", eventModelArrayList.get(i).getEventTitle(), currentDate);
                    FirebaseDatabase.getInstance().getReference("Events")
                            .child(eventModelArrayList.get(i).getEventTitle()).push().setValue(event)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(context, "You have registered Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }else{
                        Toast.makeText(context, "You need to Login to Register for the event", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Register.class);
                        context.startActivity(intent);
                    }

                }
            });

        myHolder.viewRegisteredPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        Button eventRegister, viewRegisteredPeople;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventDate = itemView.findViewById(R.id.event_date);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventEntryFee = itemView.findViewById(R.id.event_entry_fee);
            eventStatus = itemView.findViewById(R.id.event_status);
            shareEvent = itemView.findViewById(R.id.share_event);
            eventRegister = itemView.findViewById(R.id.event_register);
            viewRegisteredPeople = itemView.findViewById(R.id.event_register_people);
        }
    }
}
