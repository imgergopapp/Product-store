package com.codecool.web.model;

public class Address {
    private String country;
    private String zipCode;
    private String city;
    private String street;

    public Address(String country, String zipCode, String city, String street) {
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
