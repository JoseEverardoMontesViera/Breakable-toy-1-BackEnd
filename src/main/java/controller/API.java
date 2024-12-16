package controller;

import lombok.*;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.productServiceImp;

import java.time.LocalDateTime;
import java.util.List;
import model.Product;

@RestController
@RequestMapping("/inventory")
public class API {

    @Autowired
    private productServiceImp productServiceImp;

    @GetMapping("/products/Hello")
    public String home(){
        return "Hola mundo";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(required = false) String filter){
        return productServiceImp.getAllProducts();
    }

    @PostMapping("/products")
    public void postProduct(@Validated @RequestBody Product product){
        productServiceImp.addProducts(product);

    }

    @PutMapping("/products/{id}")
    public void updateProduct(@Validated @RequestParam Integer productId, @RequestParam String productName, @RequestParam String productCategory, @RequestParam Float productPrice, @RequestParam LocalDateTime productExpirationDate, @RequestParam Integer productQuantityStock){
        productServiceImp.modifyProduct(productId,productName,productCategory,productPrice,productExpirationDate,productQuantityStock);
    }


    @PostMapping("/products/{id}/outofstock")
    public void outOfStockProduct(@Validated @RequestParam Integer productId){
        productServiceImp.outOfStockProduct(productId);
    }


    @PutMapping("/products/{id}/instock")
    public void inStockProduct(@Validated @RequestParam Integer productId, @RequestParam Integer productQuantityStock){
        if (productQuantityStock!=0){
            productServiceImp.reStockProduct(productId,productQuantityStock);
        }
        else{

        }
    }
    @DeleteMapping("/products/{id}/delete")
    public Boolean deleteProduct(@Validated @RequestParam Integer productId){
        return productServiceImp.deleteProduct(productId);
    }
}
