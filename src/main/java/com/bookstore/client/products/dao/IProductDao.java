package com.bookstore.client.products.dao;

import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProductDao {
    List<Product> selectProductByCategory(@Param("category") String category,@Param("pageModel") PageModel pageModel);

    int selectProductCountByCategory(String category);

    List<Product> selectProductByName(@Param("name") String name,@Param("pageModel") PageModel pageModel);

    int selectProductByNameCount(String name);

    Product selectProductById(String id);

    Notice selectNoticeRecent();

    List<Product> selectWeekHotProduct();
}
