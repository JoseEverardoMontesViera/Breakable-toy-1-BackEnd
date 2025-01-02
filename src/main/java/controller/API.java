package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.GsonBuilder;
import lombok.*;
import model.Product;
import model.Summary;
import org.apache.coyote.Response;
import org.json.JSONArray;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.LocalDateTimeTypeAdapter;
import services.productServiceImp;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Product;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
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

    @GetMapping("/products/Categories")
    public List<String> getAllCategories(@RequestParam(required = false) String filter){
        return productServiceImp.getCategories();
    }

    @GetMapping("/products/summary")
    public List<Summary> getSummary(@RequestParam(required = false) String filter){
        return productServiceImp.getSummary();
    }

    @GetMapping("/products/search/{id}")
    public Product getProduct(@PathVariable Integer id){
        return productServiceImp.searchProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> postProduct(@RequestBody String newProduct){
        System.out.println(newProduct);
//        Product newProduct = new Product(0, productName, productCategory, productPrice, productExpirationDate, productQuantityStock);
        JSONObject json = new JSONObject(newProduct);
        Product addingProduct = new Product();
        addingProduct.setProductName(json.getString("productName"));
        JSONArray categories =json.getJSONArray("productCategory");
        ArrayList<String> categoriesArray = new ArrayList<String>();
        if (categories != null) {
            for (int i=0;i<categories.length();i++){
                categoriesArray.add(categories.getString(i));
            }
        }
        addingProduct.setProductCategory(categoriesArray);
        addingProduct.setProductPrice(json.getFloat("productPrice"));
        if(json.getString("productExpirationDate")!=null){
            addingProduct.setProductExpirationDate(json.getString("productExpirationDate").toString());
        }
        addingProduct.setProductQuantityStock(json.getInt("productQuantityStock"));
        productServiceImp.addProducts(addingProduct);

        return ResponseEntity.ok().body("The product has been added");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@Validated @PathVariable Integer id, @RequestBody String uProduct){
        if(productServiceImp.searchProduct(id) == null){
            System.out.println("No encontrado");
            return ResponseEntity.notFound().build();
        }
        else{
            JSONObject json = new JSONObject(uProduct);
            System.out.println(json);
            Product addingProduct = new Product();
            addingProduct.setProductName(json.getString("productName"));
            JSONArray categories =json.getJSONArray("productCategory");
            ArrayList<String> categoriesArray = new ArrayList<String>();
            if (categories != null) {
                for (int i=0;i<categories.length();i++){
                    categoriesArray.add(categories.getString(i));
                }
            }
            addingProduct.setProductCategory(categoriesArray);
            addingProduct.setProductPrice(json.getFloat("productPrice"));
            if(json.getString("productExpirationDate")!=null){
                addingProduct.setProductExpirationDate(json.getString("productExpirationDate").toString());
            }
            addingProduct.setProductQuantityStock(json.getInt("productQuantityStock"));
            productServiceImp.modifyProduct(id,addingProduct);
            return ResponseEntity.ok().body("Product with id: "+id+" has been modified.");
        }
    }


    @PostMapping("/products/{id}/outofstock")
    public ResponseEntity<?> outOfStockProduct(@Validated @PathVariable Integer id){
        if(productServiceImp.searchProduct(id) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            productServiceImp.outOfStockProduct(id);
            return ResponseEntity.ok().body("Product with id: "+id+" has been marked as out of stock.");
        }
    }


    @PutMapping("/products/{id}/instock")
    public ResponseEntity<?> inStockProduct(@Validated @PathVariable Integer id){
            Product product = productServiceImp.searchProduct(id);
            if(product == null){
                return ResponseEntity.notFound().build();
            }
            else{
                if (product.getProductQuantityStock()==0){
                    productServiceImp.reStockProduct(id);
                    return ResponseEntity.ok().body("Product with id: "+id+" has been restocked.");
                }
                else{
                    return ResponseEntity.ok().body("That product already has a stock");
                }
            }

    }@PutMapping("/products/{id}/inOrOutStock")
    public ResponseEntity<?> inOrOutStockProduct(@Validated @PathVariable Integer id){
            Product product = productServiceImp.searchProduct(id);
            if(product == null){
                return ResponseEntity.notFound().build();
            }
            else{
                if (product.getProductQuantityStock()==0){
                    productServiceImp.reStockANDOutofStockProduct(id);
                    return ResponseEntity.ok().body("Product with id: "+id+" has been restocked.");
                }
                else{
                    productServiceImp.reStockANDOutofStockProduct(id);
                    return ResponseEntity.ok().body("Product with id: "+id+" has been marked as out of stock.");
                }
            }

    }
    @DeleteMapping("/products/{id}/delete")
    public ResponseEntity<?> deleteProduct(@Validated @PathVariable Integer id){
        if(productServiceImp.searchProduct(id) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            productServiceImp.deleteProduct(id);
            return ResponseEntity.ok().body("Product with id: "+id+" has been ceased to exist.");
        }
    }
}
