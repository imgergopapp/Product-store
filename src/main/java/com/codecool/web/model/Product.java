package com.codecool.web.model;

public class Product {
    private int productId;
    private String productName;
    private String category;
    private String properties;
    private int price;
    private int inStock;
    private int salePercentage;
    private String pictureUrl;

    public Product(int productId, String productName, String category, String properties,
                   int price, int inStock, int salePercentage, String pictureUrl) {

        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.properties = properties;
        this.price = price;
        this.inStock = inStock;
        this.salePercentage = salePercentage;
        this.pictureUrl = pictureUrl;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getProperties() {
        return properties;
    }

    public int getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getSalePercentage() {
        return salePercentage;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
