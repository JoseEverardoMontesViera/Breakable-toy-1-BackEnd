package model;
import jakarta.validation.constraints.*;


public class Product {
    private Integer productId;
    @NotNull
    @NotBlank
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 120, message = "{validation.name.size.too_long}")
    private String productName;
    @NotNull
    @NotBlank
    private String productCategory;
    @NotNull
    @NotBlank
    @Positive
    private Float productPrice;
    @Null
    private String productExpirationDate;
    @NotNull
    @NotBlank
    @Positive
    private Integer productQuantityStock;
    @Null
    private String productCreationDate;
    @Null
    private String productUpdateDate;
    public Product( String productName, String productCategory, Float productPrice, String productExpirationDate, Integer productQuantityStock) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productExpirationDate = productExpirationDate;
        this.productQuantityStock = productQuantityStock;
    }

    public Product() {
    }


    public String getProductUpdateDate() {
        return productUpdateDate;
    }

    public String getProductCreationDate(String date) {
        return productCreationDate;
    }

    public Integer getProductQuantityStock() {
        return productQuantityStock;
    }

    public String getProductExpirationDate() {
        return productExpirationDate;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductExpirationDate(String productExpirationDate) {
        this.productExpirationDate = productExpirationDate;
    }

    public void setProductQuantityStock(Integer productQuantityStock) {
        this.productQuantityStock = productQuantityStock;
    }

    public void setProductCreationDate(String productCreationDate) {
        this.productCreationDate = productCreationDate;
    }

    public void setProductUpdateDate(String productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }




}
