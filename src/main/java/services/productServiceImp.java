package services;
import model.Product;
import model.Summary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class productServiceImp implements productService {

    public List<Product> inventory = new ArrayList<>();

    DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd");

    @Override
    public Product addProducts(Product product) {
        if(inventory.isEmpty()){
            product.setProductId(1);
        }
        else{
            product.setProductId((inventory.getLast().getProductId())+1);
        }
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.format(formatter);
        product.setProductCreationDate(dateTimeString);
        product.setProductUpdateDate(dateTimeString);
        inventory.add(product);
        return product;
    }

    @Override
    public Product searchProduct(Integer Id) {
        return inventory.stream().filter(product -> Objects.equals(product.getProductId(), Id)).toList().getFirst();
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
        if(!Objects.equals(uProduct.getProductQuantityStock(), product.getProductQuantityStock())){
            product.setProductQuantityStock(uProduct.getProductQuantityStock());
            modified = true;
        }
        if(modified){
            LocalDateTime now = LocalDateTime.now();
            String dateTimeString = now.format(formatter);
            product.setProductUpdateDate(dateTimeString);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean outOfStockProduct(Integer productId) {
        Product product = searchProduct(productId);
        product.setProductQuantityStock(0);
        return true;
    }

    @Override
    public Boolean reStockProduct(Integer productId) {
        Product product = searchProduct(productId);
        product.setProductQuantityStock(10);
        return true;
    }


    @Override
    public List<Product> getAllProducts() {
        return inventory.stream().toList();
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<String>();
        inventory.forEach(product -> {
            if(categories.contains(product.getProductCategory())){

            }
            else{
                categories.add(product.getProductCategory());
            }
        });
        return categories;
    }



    @Override
    public List<Product> getFilteredProducts(String criteria) {
        return inventory.stream().filter(product -> Objects.equals(product.getProductCategory(), criteria)).toList();
    }
    @Override
    public List<Summary> getSummary() {
        List<String> categories = new ArrayList<String>();
        List<Summary> summary = new ArrayList<Summary>();
        inventory.forEach(product -> {
            if(categories.contains(product.getProductCategory())){

            }
            else{
                categories.add(product.getProductCategory());
            }
        });
        categories.forEach(category-> {
            AtomicReference<Float> totalProducts= new AtomicReference<>(0F);
            AtomicReference<Float> totalValue= new AtomicReference<>(0F);
            Float AveragePrice=0F;
            inventory.forEach(product -> {
                if(product.getProductCategory().equals(category)){
                    totalProducts.updateAndGet(v -> v + 1F);
                    totalValue.updateAndGet(v -> v + product.getProductPrice().floatValue());
                }
            });
            AveragePrice = totalValue.get()/totalProducts.get();
            Summary categorySummary = new Summary(category,totalProducts.get(),totalValue.get(),AveragePrice);
            summary.add(categorySummary);

        });
        return summary;
    }

}
