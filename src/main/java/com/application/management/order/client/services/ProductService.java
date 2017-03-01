package com.application.management.order.client.services;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Product;

public interface ProductService {

    /**
     * @return data type that implements the Iterable interface with all products in it
     */
    Iterable<Product> getAllProducts();

    /**
     * @param setProductBarcode if true, product barcode will be set again, if false it will be left
     *                          as it is. Use it in case you want to set the barcode manually
     * @throw CustomException if product was not added
     */
    void addNewProduct(Product product, boolean setProductBarcode) throws CustomException;

    /**
     * @return Product object if product was found by the barcode.
     * @throws CustomException if product was not found
     */
    Product getProductByBarcode(String productBarcode) throws CustomException;

    /**
     * @throws CustomException If product was not updated
     */
    void updateProduct(Product product) throws CustomException;

}
