package com.agritech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agritech.R;
import com.agritech.model.ChatModel;

import java.util.ArrayList;

public class ChatRecyclerView extends RecyclerView.Adapter<ChatRecyclerView.MyHolder> {
    Context context;
    ArrayList<ChatModel> chatList = new ArrayList<>();

    public ChatRecyclerView(Context context, ArrayList<ChatModel> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyHolder(inflater.inflate(R.layout.chat_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.myMessage.setText(chatList.get(i).getMessage());
        myHolder.myTime.setText(chatList.get(i).getTimeSent());
        myHolder.mySender.setText(chatList.get(i).getSender());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView myMessage, myTime, mySender;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            myMessage = itemView.findViewById(R.id.my_message);
            mySender = itemView.findViewById(R.id.my_sender);
            myTime = itemView.findViewById(R.id.my_time);
        }
    }
}
