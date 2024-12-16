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
    }

    @Override
    public Boolean deleteProduct(Integer productId) {
        return inventory.remove(searchProduct(productId));
    }

    @Override
    public Boolean modifyProduct(Integer productId, Product uProduct) {
        Product product = searchProduct(productId);
        Boolean modified = false;
        if(!Objects.equals(uProduct.getProductName(), product.getProductName())){
            product.setProductName(uProduct.getProductName());
            modified = true;
        }
        if(!Objects.equals(uProduct.getProductCategory(), product.getProductCategory())){
            product.setProductCategory(uProduct.getProductCategory());
            modified = true;
        }
        if(!Objects.equals(uProduct.getProductPrice(), product.getProductPrice())){
            product.setProductPrice(uProduct.getProductPrice());
            modified = true;
        }
        if(uProduct.getProductExpirationDate() != product.getProductExpirationDate()){
            product.setProductExpirationDate(uProduct.getProductExpirationDate());
            modified = true;
        }
        if(uProduct.getProductQuantityStock() != product.getProductQuantityStock()){
            product.setProductQuantityStock(uProduct.getProductQuantityStock());
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
    public List<Product> getFilteredProducts(String criteria) {
        return inventory.stream().filter(product -> Objects.equals(product.getProductCategory(), criteria)).toList();
    }

}
