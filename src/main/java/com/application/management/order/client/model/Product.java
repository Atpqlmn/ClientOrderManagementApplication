package com.application.management.order.client.model;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    /*
     * @NotNull annotations used as hints
     * */

    /**
     * the barcode will be generated in product services, as the id needs to be validated
     */
    @NotNull
    @Id
    private String productBarcode;
    @NotNull
    private String productName;
    @NotNull
    private BigDecimal productBasePrice;
    @NotNull
    private String productDescription;
    @NotNull
    private String productReleaseDate;

    Product() {
    }

    public Product(String productName, BigDecimal productBasePrice, String productDescription, String productReleaseDate) {
        this.productName = productName;
        this.productBasePrice = productBasePrice;
        this.productDescription = productDescription;
        this.productReleaseDate = productReleaseDate;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductBasePrice() {
        return productBasePrice;
    }

    public void setProductBasePrice(BigDecimal productBasePrice) {
        this.productBasePrice = productBasePrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductReleaseDate() {
        return productReleaseDate;
    }

    public void setProductReleaseDate(String productReleaseDate) {
        this.productReleaseDate = productReleaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productBarcode != null ? !productBarcode.equals(product.productBarcode) : product.productBarcode != null)
            return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null)
            return false;
        if (productBasePrice != null ? !productBasePrice.equals(product.productBasePrice) : product.productBasePrice != null)
            return false;
        if (productDescription != null ? !productDescription.equals(product.productDescription) : product.productDescription != null)
            return false;
        return productReleaseDate != null ? productReleaseDate.equals(product.productReleaseDate) : product.productReleaseDate == null;

    }

    @Override
    public int hashCode() {
        int result = productBarcode != null ? productBarcode.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productBasePrice != null ? productBasePrice.hashCode() : 0);
        result = 31 * result + (productDescription != null ? productDescription.hashCode() : 0);
        result = 31 * result + (productReleaseDate != null ? productReleaseDate.hashCode() : 0);
        return result;
    }
}

