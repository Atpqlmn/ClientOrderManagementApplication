package com.application.management.order.client.controllers;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(String productName,
                           BigDecimal productBasePrice,
                           String productDescription,
                           String productReleaseDate) throws Exception {
        Product product = new Product(productName, productBasePrice, productDescription,
                productReleaseDate);
        try{
            productService.addNewProduct(product, true);
        } catch (CustomException e){
            throw e;
        }
    }

    /**
     * retrieves client information from a database by using the client id
     */
    @RequestMapping(value = "/product/{productBarcode}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProductByBarcode(@PathVariable(value = "productBarcode")
                                                               String productBarcode) throws Exception {
        Product product;
        try {
            product = productService.getProductByBarcode(productBarcode);
        } catch (CustomException e) {
            throw e;
        }
        return ResponseEntity.ok(product);

    }


    @RequestMapping(value = "/product/edit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editProduct(Product product) throws Exception {
        try {
            productService.updateProduct(product);
        } catch (CustomException e) {
            throw e;
        }
    }
}
