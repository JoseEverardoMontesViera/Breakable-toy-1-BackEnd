package services;

import model.Product;
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

    List<Product> getAllProducts();

    List<Product> getFilteredProducts(String criteria);


}
