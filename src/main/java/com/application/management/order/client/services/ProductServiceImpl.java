package com.application.management.order.client.services;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.repositories.ProductRepository;
import com.application.management.order.client.util.CommonUtil;
import com.application.management.order.client.util.ProductAndOrderValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Business logic for products.
 */
@Service
public class ProductServiceImpl implements ProductService {

    // TODO: test services with invalid input

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addNewProduct(Product product, boolean setProductBarcode) throws CustomException {
        if(setProductBarcode){
            product.setProductBarcode(generateUniqueBarcode());
        }
        try {
            checkProduct(product);
            productRepository.save(product);
        } catch (CustomException e) {
            throw e;
        }
    }

    @Override
    public Product getProductByBarcode(String productBarcode) throws CustomException {
        Product product;
        if (ProductAndOrderValidation.isBarcodeValid(productBarcode)) {
            product = productRepository.findOne(productBarcode);
            if (product == null) {
                throw new CustomException("Product with this barcode does not exist");
            }
        } else {
            throw new CustomException("Barcode is not valid");
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) throws CustomException {
        try {
            checkProduct(product);
            if (!productRepository.exists(product.getProductBarcode())) {
                throw new CustomException("Product with this barcode does not exist");
            } else {
                productRepository.save(product);
            }
        } catch (CustomException e) {
            throw e;
        }
    }

    /**
     * Generates a unique barcode for a new product. Barcode consists of 10 digits
     */
    private String generateUniqueBarcode() {
        String barcode;
        while (true) {
            barcode = CommonUtil.generateBarcode(10);
            if (!productRepository.exists(barcode)) {
                return barcode;
            }
        }
    }

    private void checkProduct(Product product) throws CustomException {
        if (product == null) {
            throw new CustomException("Product is null");
        }

        String barcode = product.getProductBarcode();
        String releaseDate = product.getProductReleaseDate();
        BigDecimal price = product.getProductBasePrice();
        String name = product.getProductName();
        String description = product.getProductDescription();

        if (!ProductAndOrderValidation.isBarcodeValid(barcode)) {
            throw new CustomException("Product barcode is not valid " + barcode);
        } else if (!ProductAndOrderValidation.isDateValid(releaseDate)) {
            throw new CustomException("Product date is not valid " + releaseDate);
        } else if (!ProductAndOrderValidation.isPriceValid(price)) {
            throw new CustomException("Product price is not valid " + price);
        } else if (!CommonUtil.validateString(name)) {
            throw new CustomException("Product name is not valid " + name);
        } else if (!CommonUtil.validateString(description)) {
            throw new CustomException("Product description is not valid " + name);
        }
    }
}
