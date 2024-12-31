package com.example;

import controller.API;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import services.productServiceImp;

import java.util.ArrayList;
import java.util.List;

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


    public List<Product> inventory = new ArrayList<>();
    @BeforeEach
    void setUp() {
        ArrayList<String> arr =  new ArrayList<String>();
        arr.add("clothes");
        product.setProductId(1);
        product.setProductName("Shirt");
        product.setProductCategory(arr);
        product.setProductPrice(45.0F);
        product.setProductExpirationDate("");
        product.setProductQuantityStock(777);
        product.setProductUpdateDate("2024-12-20");
        product.setProductCreationDate("2024-12-20");
        inventory.add(product);



    }
    @Test
    public void saveProduct() throws Exception {
        ArrayList<String> arr =  new ArrayList<String>();
        arr.add("clothes");
        Product postProduct = new Product("Shirt",arr,45.0F,"",777);
        Mockito.doReturn(product).when(productService).addProducts(postProduct);
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "productName":"Shirt",
                          "productCategory":["Clothes"],
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
        ArrayList<String> arr =  new ArrayList<String>();
        arr.add("clothes");
        Product newProduct = new Product("Hat",arr,45.0F,"",777);
        newProduct.setProductId(1);
        Product modifiedProduct = new Product("Cap",arr,45.0F,"",777);
        Mockito.doReturn(true).when(productService).modifyProduct(1, modifiedProduct );
        Mockito.doReturn(newProduct).when(productService).searchProduct(1);
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "productName":"Cap",
                              "productCategory":["Clothes"],
                              "productPrice":45,
                              "productExpirationDate":"",
                              "productQuantityStock":777
                            }"""))
                .andExpect(status().isOk());}

        @Test
        public void markAsOutOfStock() throws Exception{
            ArrayList<String> arr =  new ArrayList<String>();
            arr.add("clothes");
            Product newProduct = new Product("Hat",arr,45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).outOfStockProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(post("/products/1/outofstock").contentType(MediaType.APPLICATION_JSON)
                            )
                    .andExpect(status().isOk());

        }

        @Test
        public void markAsRestocked() throws Exception{

            ArrayList<String> arr =  new ArrayList<String>();
            arr.add("clothes");
            Product newProduct = new Product("Hat",arr,45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).reStockProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(put("/products/1/instock").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

        @Test
        public void deleteProducts() throws Exception{
            ArrayList<String> arr =  new ArrayList<String>();
            arr.add("clothes");
            Product newProduct = new Product("Hat",arr,45.0F,"",777);
            newProduct.setProductId(1);
            Mockito.doReturn(true).when(productService).deleteProduct(1 );
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(delete("/products/1/delete").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }@Test
        public void searchProduct() throws Exception{
        ArrayList<String> arr =  new ArrayList<String>();
        arr.add("clothes");
            Product newProduct = new Product("Hat",arr,45.0F,"",777);
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

        @Test
        public void inOrOutStock() throws Exception{
            ArrayList<String> arr =  new ArrayList<String>();
            arr.add("clothes");
            Product newProduct = new Product("Hat",arr,45.0F,"",777);
            Mockito.doReturn(true).when(productService).reStockANDOutofStockProduct(1);
            Mockito.doReturn(newProduct).when(productService).searchProduct(1);
            mockMvc.perform(put("/products/1/inOrOutStock").contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

    }
