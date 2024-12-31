package services;

import model.Product;
import model.Summary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public interface productService {

    Product addProducts(Product product);

    Product searchProduct(Integer productId);

    Boolean deleteProduct(Integer productId);

    Boolean modifyProduct(Integer productId, Product uProduct);
    Boolean outOfStockProduct(Integer productId);
    Boolean reStockProduct(Integer productId);
    Boolean reStockANDOutofStockProduct(Integer productId);

    List<Product> getAllProducts();

    List<String> getCategories();

    List<Summary> getSummary();

    List<Product> getFilteredProducts(String criteria);


}
