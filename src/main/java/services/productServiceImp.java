package services;
import model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import model.Product;

@Service
public class productServiceImp implements productService {

    public List<Product> inventory = new ArrayList<>();


    @Override
    public void addProducts(Product product) {
        if(inventory.isEmpty()){
            product.setProductId(1);
        }
        else{
            product.setProductId((inventory.get(inventory.size()-1).getProductId())+1);
        }
        product.setProductCreationDate(LocalDateTime.now());
        product.setProductUpdateDate(LocalDateTime.now());
        inventory.add(product);
    }

    @Override
    public Product searchProduct(Integer Id) {
        return inventory.stream().filter(product -> product.getProductId()==Id).toList().getFirst();
        //return inventory.get(Id);
    }

    @Override
    public Boolean deleteProduct(Integer productId) {
        return inventory.remove(searchProduct(productId));
    }

    @Override
    public Boolean modifyProduct(Integer productId, String productName, String productCategory, Float productPrice, LocalDateTime productExpirationDate, Integer productQuantityStock) {
        Product product = searchProduct(productId);
        Boolean modified = false;
        if(!Objects.equals(productName, product.getProductName())){
            product.setProductName(productName);
            modified = true;
        }
        if(!Objects.equals(productCategory, product.getProductCategory())){
            product.setProductCategory(productCategory);
            modified = true;
        }
        if(!Objects.equals(productPrice, product.getProductPrice())){
            product.setProductPrice(productPrice);
            modified = true;
        }
        if(productExpirationDate != product.getProductExpirationDate()){
            product.setProductExpirationDate(productExpirationDate);
            modified = true;
        }
        if(productName != product.getProductName()){
            product.setProductName(productName);
            modified = true;
        }
        if(productQuantityStock != product.getProductQuantityStock()){
            product.setProductQuantityStock(productQuantityStock);
            modified = true;
        }
        if(modified){
            product.setProductUpdateDate(LocalDateTime.now());
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void outOfStockProduct(Integer productId) {
        Product product = searchProduct(productId);
        product.setProductQuantityStock(0);
    }

    @Override
    public void reStockProduct(Integer productId, Integer productQuantityStock) {
        Product product = searchProduct(productId);
        product.setProductQuantityStock(productQuantityStock);
    }


    @Override
    public List<Product> getAllProducts() {
        return inventory.stream().toList();
    }

    @Override
    public List<Product> getFilteredProducts(Integer criteria) {
        return inventory;
        //return inventory.values().stream().filter(product -> Objects.equals(product.getProductCategory(), criteria.toString())).toList();
    }

}
