package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory {
    private List<Product> inventoryList;
    public Inventory() {
        this.inventoryList = new ArrayList<>();
    }
    public List<Product> getAllProducts(){
        return inventoryList.stream().toList();
    }
    public void addProduct(Product product){
        inventoryList.add(product);
    }
    public Product searchProductById(Integer id){
        return (Product) inventoryList.stream().filter(product -> product.getProductId().equals(id));
    }
    public void deleteProduct(Integer id){
        inventoryList.remove(searchProductById(id));
    }
    public void editProduct(Integer productId, String productCategory,Float productPrice,Date productExpirationDate, Integer productQuantityStock, Date productCreationDate, Date productUpdateDate){
       Product product = searchProductById(productId);
       //Aquí va modificar producto
    }

}
