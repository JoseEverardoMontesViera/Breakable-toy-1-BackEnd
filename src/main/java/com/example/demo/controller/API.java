package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class API {
    public Product getProducts(){ 
        return null;
    }
}
