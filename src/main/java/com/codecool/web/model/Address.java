package com.codecool.web.model;

public class Address {
    private String country;
    private String zip_code;
    private String city;
    private String street;

    public Address(String country, String zip_code, String city, String street) {
        this.country = country;
        this.zip_code = zip_code;
        this.city = city;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
