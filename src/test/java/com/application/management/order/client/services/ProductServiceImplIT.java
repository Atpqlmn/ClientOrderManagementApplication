package com.application.management.order.client.services;

import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.repositories.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Integration tests for ProductService
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = ProductServiceImpl.class))
public class ProductServiceImplIT {

    private ProductServiceImpl productServiceImpl;
    private ProductRepository productRepository;

    @Autowired
    public void setProductServiceImpl(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Sets up data before tests
    @Before
    public void setUp() {

        String[] barcodes = new String[]{"1234567891", "1234967891"};
        Product product1 = new Product("Product Name", new BigDecimal("12.10"),
                "Product Description", "2010-10-20");
        product1.setProductBarcode(barcodes[0]);
        productRepository.save(product1);
    }

    //-------------------------------------------------------------------------
    /*
     * Have the same validation mechanism as updateProduct(...)
     * addNewProduct(...)
     * */
    @Test(expected = CustomException.class)
    public void addProductWithInvalidProductName() throws Exception {
        String barcode = "1234577891";
        Product product = new Product("", new BigDecimal("12.10"),
                "Product Description", "2010-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.addNewProduct(product, false);
    }

    @Test
    public void addProductWithValidInfo() throws Exception {
        String barcode = "1234577891";
        Product product = new Product("Some product", new BigDecimal("0.10"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.addNewProduct(product, false);
    }

    //-------------------------------------------------------------------------

    /*
     * updateProduct(...)
     */

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidBarcode() throws Exception {
        String barcodeNotExist = "1234567898";
        Product product = new Product("Some updates product", new BigDecimal("0.20"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcodeNotExist);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidBarcodeNull() throws Exception {
        String barcodeNotExist = null;
        Product product = new Product("Some updates product", new BigDecimal("0.20"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcodeNotExist);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidPrice() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some updates product", new BigDecimal("0.001"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidPriceNull() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some updates product", null,
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidName() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("", new BigDecimal("0.20"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidNameNull() throws Exception {
        String barcode = "1234567891";
        Product product = new Product(null, new BigDecimal("0.20"),
                "Product Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidDate() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some name", new BigDecimal("5"),
                "Product Description", "2014-30-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidDateNull() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some name", new BigDecimal("5"),
                "Product Description", null);
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidDescription() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some name", new BigDecimal("5"),
                "", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test(expected = CustomException.class)
    public void updateProductWithInvalidDescriptionNull() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some name", new BigDecimal("5"),
                null, "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    @Test
    public void updateProductWithInvalidInfo() throws Exception {
        String barcode = "1234567891";
        Product product = new Product("Some name", new BigDecimal("5"),
                "Description", "2014-10-20");
        product.setProductBarcode(barcode);
        productServiceImpl.updateProduct(product);
    }

    //-------------------------------------------------------------------------

    /*
     * getProductByBarcode(...)
     */
    @Test(expected = CustomException.class)
    public void getProductWithInvalidId() throws Exception {
        String barcodeNotExist = "1234567899";
        productServiceImpl.getProductByBarcode(barcodeNotExist);
    }

    @Test(expected = CustomException.class)
    public void getProductWithInvalidIdNull() throws Exception {
        String barcodeNotExist = null;
        productServiceImpl.getProductByBarcode(barcodeNotExist);
    }

    @Test
    public void getProductWithValidId() throws Exception {
        String barcodeExists = "1234567891";
        productServiceImpl.getProductByBarcode(barcodeExists);
    }


}
