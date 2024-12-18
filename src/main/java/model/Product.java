package model;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;


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
    private LocalDateTime productExpirationDate;
    @NotNull
    @NotBlank
    @Positive
    private Integer productQuantityStock;
    @Null
    private LocalDateTime productCreationDate;
    @Null
    private LocalDateTime productUpdateDate;
    public Product(Integer productId, String productName, String productCategory, Float productPrice, LocalDateTime productExpirationDate, Integer productQuantityStock, LocalDateTime productCreationDate, LocalDateTime productUpdateDate) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productExpirationDate = productExpirationDate;
        this.productQuantityStock = productQuantityStock;
        this.productCreationDate = productCreationDate;
        this.productUpdateDate = productUpdateDate;
    }

    public Product() {
    }


    public LocalDateTime getProductUpdateDate() {
        return productUpdateDate;
    }

    public LocalDateTime getProductCreationDate() {
        return productCreationDate;
    }

    public Integer getProductQuantityStock() {
        return productQuantityStock;
    }

    public LocalDateTime getProductExpirationDate() {
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

    public void setProductExpirationDate(LocalDateTime productExpirationDate) {
        this.productExpirationDate = productExpirationDate;
    }

    public void setProductQuantityStock(Integer productQuantityStock) {
        this.productQuantityStock = productQuantityStock;
    }

    public void setProductCreationDate(LocalDateTime productCreationDate) {
        this.productCreationDate = productCreationDate;
    }

    public void setProductUpdateDate(LocalDateTime productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }




}
