package com.agritech.model;

public class ChatModel {
    String sender, message, timeSent;

    public ChatModel() {}

    public ChatModel(String sender, String message, String timeSent) {
        this.sender = sender;
        this.message = message;
        this.timeSent = timeSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }
}
