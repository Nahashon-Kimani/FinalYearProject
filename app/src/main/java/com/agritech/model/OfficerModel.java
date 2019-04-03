package com.agritech.model;

public class OfficerModel {
    private  String officerName, officerLocation, officerSubCounty, officerCounty, officerPhoneNo;
    public OfficerModel() {}

    public OfficerModel(String officerName, String officerLocation, String officerSubCounty, String officerCounty, String officerPhoneNo) {
        this.officerName = officerName;
        this.officerLocation = officerLocation;
        this.officerSubCounty = officerSubCounty;
        this.officerCounty = officerCounty;
        this.officerPhoneNo = officerPhoneNo;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getOfficerLocation() {
        return officerLocation;
    }

    public void setOfficerLocation(String officerLocation) {
        this.officerLocation = officerLocation;
    }

    public String getOfficerSubCounty() {
        return officerSubCounty;
    }

    public void setOfficerSubCounty(String officerSubCounty) {
        this.officerSubCounty = officerSubCounty;
    }

    public String getOfficerCounty() {
        return officerCounty;
    }

    public void setOfficerCounty(String officerCounty) {
        this.officerCounty = officerCounty;
    }

    public String getOfficerPhoneNo() {
        return officerPhoneNo;
    }

    public void setOfficerPhoneNo(String officerPhoneNo) {
        this.officerPhoneNo = officerPhoneNo;
    }
}
