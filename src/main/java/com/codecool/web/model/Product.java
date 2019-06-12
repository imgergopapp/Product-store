package com.codecool.web.model;

public class Product {
    private int productId;
    private String productName;
    private String mainCategory;
    private String subCategory;
    private String properties;
    private String productCompany;
    private int price;
    private int inStock;
    private int salePercentage;
    private String pictureUrl;

    public Product(int productId, String productName, String mainCategory, String subCategory, String properties,
                   String productCompany, int price, int inStock, int salePercentage, String pictureUrl) {
        this.productId = productId;
        this.productName = productName;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.properties = properties;
        this.productCompany = productCompany;
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

    public String getMainCategory() {
        return mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getProperties() {
        return properties;
    }

    public String getProductCompany() {
        return productCompany;
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
