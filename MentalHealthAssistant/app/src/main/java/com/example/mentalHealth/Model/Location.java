package com.example.mentalHealth.Model;

public class Location {

    private String name;
    private String openingHours;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String addressline4;
    private String phoneNumber;
    private String eircode;
    private String County;

    public Location() {
    }

    public Location(String name, String openingHours, String addressline1, String addressline2, String addressline3, String addressline4, String phoneNumber, String eircode, String county) {
        this.name = name;
        this.openingHours = openingHours;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addressline3 = addressline3;
        this.addressline4 = addressline4;
        this.phoneNumber = phoneNumber;
        this.eircode = eircode;
        County = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public String getAddressline4() {
        return addressline4;
    }

    public void setAddressline4(String addressline4) {
        this.addressline4 = addressline4;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }
}
