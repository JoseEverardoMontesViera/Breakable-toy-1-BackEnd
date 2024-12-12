package com.example.demo.model;

import java.util.Date;

public class Product {
    private Integer productId;
    private String productCategory;
    private Float productPrice;
    private Date productExpirationDate;
    private Integer productQuantityStock;
    private Date productCreationDate;
    private Date productUpdateDate;

    public Product(Integer productId, String productCategory, Float productPrice, Date productExpirationDate, Integer productQuantityStock, Date productCreationDate, Date productUpdateDate) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productExpirationDate = productExpirationDate;
        this.productQuantityStock = productQuantityStock;
        this.productCreationDate = productCreationDate;
        this.productUpdateDate = productUpdateDate;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductExpirationDate() {
        return productExpirationDate;
    }

    public void setProductExpirationDate(Date productExpirationDate) {
        this.productExpirationDate = productExpirationDate;
    }

    public Integer getProductQuantityStock() {
        return productQuantityStock;
    }

    public void setProductQuantityStock(Integer productQuantityStock) {
        this.productQuantityStock = productQuantityStock;
    }

    public Date getProductCreationDate() {
        return productCreationDate;
    }

    public void setProductCreationDate(Date productCreationDate) {
        this.productCreationDate = productCreationDate;
    }

    public Date getProductUpdateDate() {
        return productUpdateDate;
    }

    public void setProductUpdateDate(Date productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }


}
