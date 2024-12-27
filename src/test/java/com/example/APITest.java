package com.example;

import controller.API;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import services.productService;
import services.productServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@EnableWebMvc
@WebMvcTest(API.class)
class APITest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private productServiceImp productService;

    private Product product = new Product();
    private Product product2 = new Product();
    Product productInventory = new Product("Cap","Clothes",45.0F,"",777);
    public List<Product> inventoryPlus = new ArrayList<>();

    public List<Product> inventory = new ArrayList<>();
    @BeforeEach
    void setUp() {
        product.setProductId(1);
        product.setProductName("Shirt");
        product.setProductCategory("Clothes");
        product.setProductPrice(45.0F);
        product.setProductExpirationDate("");
        product.setProductQuantityStock(777);
        product.setProductUpdateDate("2024-12-20");
        product.getProductCreationDate("2024-12-20");
        product2.setProductId(2);
        product2.setProductName("Computer");
        product2.setProductCategory("Computers");
        product2.setProductPrice(45.0F);
        product2.setProductExpirationDate("");
        product2.setProductQuantityStock(777);
        product2.setProductUpdateDate("2024-12-20");
        product2.getProductCreationDate("2024-12-20");
        inventory.add(product);
        inventory.add(product2);
        inventoryPlus.add(product);
        inventoryPlus.add(product2);
        productService.addProducts(productInventory);


    }
    @Test
    public void saveProduct() throws Exception {
        Product postProduct = new Product("Shirt","Clothes",45.0F,"",777);
        Mockito.doReturn(product).when(productService).addProducts(postProduct);
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "productName":"Shirt",
                          "productCategory":"Clothes",
                          "productPrice":45,
                          "productExpirationDate":"",
                          "productQuantityStock":777,
                        }"""))
                .andExpect(status().isOk());
    }
    @Test
    public void getAllProducts() throws Exception {
        Mockito.doReturn(inventory).when(productService).getAllProducts();
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void modifyProduct() throws Exception {
        Product newProduct = new Product("Hat","Clothes",45.0F,"",777);
        newProduct.setProductId(1);
        Product modifiedProduct = new Product("Cap","Clothes",45.0F,"",777);
        Mockito.doReturn(true).when(productService).modifyProduct(1, modifiedProduct );
        Mockito.doReturn(newProduct).when(productService).searchProduct(1);
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "productName":"Cap",
                              "productCategory":"Clothes",
                              "productPrice":45,
                              "productExpirationDate":"",
                              "productQuantityStock":777
                            }"""))
                .andExpect(status().isOk());}

        @Test
        public void markAsOutOfStock() throws Exception{
            Product newProduct = new Product("Hat","Clothes",45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).outOfStockProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(post("/products/1/outofstock").contentType(MediaType.APPLICATION_JSON)
                            )
                    .andExpect(status().isOk());

        }

        @Test
        public void markAsRestocked() throws Exception{
            Product newProduct = new Product("Hat","Clothes",45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).reStockProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(put("/products/1/instock").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

        @Test
        public void deleteProducts() throws Exception{
            Product newProduct = new Product("Hat","Clothes",45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).deleteProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(delete("/products/1/delete").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }@Test
        public void searchProduct() throws Exception{
            Product newProduct = new Product("Hat","Clothes",45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(get("/products/search/1").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }@Test
        public void getCategories() throws Exception{
            List<String> arry = new ArrayList<String>();
            Mockito.doReturn(arry).when(productService).getCategories();
            mockMvc.perform(get("/products/Categories").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

    }
