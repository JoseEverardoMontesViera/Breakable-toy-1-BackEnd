package controller;

import lombok.*;
import model.Product;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.productServiceImp;

import java.time.LocalDateTime;
import java.util.List;
import model.Product;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/inventory")
public class API {

    @Autowired
    private productServiceImp productServiceImp;

    @GetMapping("/products/Hello")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok().body("Hola mundo!");
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(required = false) String filter){
        return productServiceImp.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<?> postProduct(@Validated @RequestBody Product product){
        productServiceImp.addProducts(product);
        return ResponseEntity.ok().body("The product has been added");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@Validated @RequestParam Integer productId, @RequestBody Product uProduct ){
        if(productServiceImp.searchProduct(productId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            productServiceImp.modifyProduct(productId,uProduct);
            return ResponseEntity.ok().body("Product with id: "+productId+" has been modified.");
        }
    }


    @PostMapping("/products/{id}/outofstock")
    public ResponseEntity<?> outOfStockProduct(@Validated @RequestParam Integer productId){
        if(productServiceImp.searchProduct(productId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            productServiceImp.outOfStockProduct(productId);
            return ResponseEntity.ok().body("Product with id: "+productId+" has been marked as out of stock.");
        }
    }


    @PutMapping("/products/{id}/instock")
    public ResponseEntity<?> inStockProduct(@Validated @RequestBody Integer productId, @RequestBody Integer productQuantityStock){
        if (productQuantityStock!=0){
            if(productServiceImp.searchProduct(productId) == null){
                return ResponseEntity.notFound().build();
            }
            else{
                productServiceImp.reStockProduct(productId,productQuantityStock);
                return ResponseEntity.ok().body("Product with id: "+productId+" has been restocked.");
            }
        }
        else{
            return ResponseEntity.badRequest().body("You can not restock 0 units on a product");
        }
    }
    @DeleteMapping("/products/{id}/delete")
    public ResponseEntity<?> deleteProduct(@Validated @RequestParam Integer productId){
        if(productServiceImp.searchProduct(productId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            productServiceImp.deleteProduct(productId);
            return ResponseEntity.ok().body("Product with id: "+productId+" has been ceased to exist.");
        }
    }
}
