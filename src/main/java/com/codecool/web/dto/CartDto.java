package com.codecool.web.dto;

import com.codecool.web.model.CartItem;

import java.util.List;

public class CartDto {
    private List<CartItem> cartItems;
    private int totalPrice;

    public CartDto(List<CartItem> cartItems, int totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
