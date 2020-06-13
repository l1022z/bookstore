package com.bookstore.admin.products.service;

import com.bookstore.admin.products.dao.IAdminProductDao;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IAdminProductServiceImpl implements IAdminProductService {

    @Resource
    IAdminProductDao adminProductDao;

    @Override
    public List<Product> findProduct() {
        return adminProductDao.selectProduct();
    }

    @Override
    public List<Product> findProductByManyCondition(Product product, Double minprice, Double maxprice) {
        Map map = new HashMap();
        map.put("product",product);
        map.put("minprice",minprice);
        map.put("maxprice",maxprice);
        return adminProductDao.selectProductByManyCondition(map);
    }

    @Override
    public void addProduct(Product product) {
        adminProductDao.insertProduct(product);
    }

    @Override
    public Product findProductById(String id) {
        return adminProductDao.selectProductById(id);
    }

    @Override
    public void editProduct(Product product) {
        adminProductDao.updateProduct(product);
    }

    @Override
    public void removeProduct(String id) {
        adminProductDao.deleteProduct(id);
    }

    @Override
    public List<ProductList> findProductSalList(String year, String month) {
        return adminProductDao.selectProductSalList(year,month);
    }
}
