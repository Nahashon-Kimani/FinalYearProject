package com.agritech.model;

public class RegisterEvent {
    String user, event, registerDate;

    public RegisterEvent() {
    }

    public RegisterEvent(String user, String event, String registerDate) {
        this.user = user;
        this.event = event;
        this.registerDate = registerDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
