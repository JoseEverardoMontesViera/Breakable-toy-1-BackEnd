package model;

public class Summary {
    private String categoryName;
    private Float totalProducts;
    private Float totalValue;
    private Float averagePrice;

    public Summary() {
    }

    public Summary(String categoryName, Float totalProducts, Float totalValue, Float averagePrice) {
        this.categoryName = categoryName;
        this.totalProducts = totalProducts;
        this.totalValue = totalValue;
        this.averagePrice = averagePrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Float getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Float totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Float averagePrice) {
        this.averagePrice = averagePrice;
    }
}
