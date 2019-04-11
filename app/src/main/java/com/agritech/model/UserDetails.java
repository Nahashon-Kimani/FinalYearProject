package com.agritech.model;

public class UserDetails {
    String name, location, username, dateCreated;

    public UserDetails() {
    }

    public UserDetails(String name, String location, String username, String dateCreated) {
        this.name = name;
        this.location = location;
        this.username = username;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
