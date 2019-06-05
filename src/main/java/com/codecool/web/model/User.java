package com.codecool.web.model;

public class User {
    private int id;
    private String name;
    private String email;
    private Role role;
    private Address address;

    public User(int id, String name, String email, Role role, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Address getAddress() {
        return address;
    }
}
