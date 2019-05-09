package com.agritech.model;

public class EventModel {
    private String eventTitle, eventDate, eventLocation, eventFee, eventStatus, eventInputDate, inputBy;

    public EventModel() {    }

    public EventModel(String eventTitle, String eventDate, String eventLocation,
                      String eventFee, String eventStatus, String eventInputDate, String inputBy) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventFee = eventFee;
        this.eventStatus = eventStatus;
        this.eventInputDate = eventInputDate;
        this.inputBy = inputBy;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventFee() {
        return eventFee;
    }

    public void setEventFee(String eventFee) {
        this.eventFee = eventFee;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventInputDate() {
        return eventInputDate;
    }

    public void setEventInputDate(String eventInputDate) {
        this.eventInputDate = eventInputDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }
}