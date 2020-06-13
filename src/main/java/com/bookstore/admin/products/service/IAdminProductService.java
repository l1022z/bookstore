package com.bookstore.admin.products.service;

import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;

import java.util.List;

public interface IAdminProductService {
    List<Product> findProduct();

    List<Product> findProductByManyCondition(Product product, Double minprice, Double maxprice);

    void addProduct(Product product);

    Product findProductById(String id);

    void editProduct(Product product);

    void removeProduct(String id);

    List<ProductList> findProductSalList(String year, String month);
}
