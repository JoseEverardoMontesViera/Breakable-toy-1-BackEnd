package services;

import model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public interface productService {

    void addProducts(Product product);

    Product searchProduct(Integer productId);

    Boolean deleteProduct(Integer productId);

    Boolean modifyProduct(Integer productId, String productName, String productCategory, Float productPrice, LocalDateTime productExpirationDate, Integer productQuantityStock);
    void outOfStockProduct(Integer productId);
    void reStockProduct(Integer productId, Integer productQuantityStock);

    List<Product> getAllProducts();

    List<Product> getFilteredProducts(Integer criteria);


}
