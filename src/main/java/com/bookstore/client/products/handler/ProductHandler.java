package com.bookstore.client.products.handler;

import com.bookstore.client.products.service.IProductService;
import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/product")
public class ProductHandler {

    @Autowired
    IProductService productService;

    //按商品类别查询
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(@RequestParam(defaultValue = "1") int pageIndex, String category, Model model) {
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Product> products = productService.findProductByCategory(category,pageModel);

        int recordCount = productService.findProductCountByCategory(category);
        pageModel.setRocordCount(recordCount);
        for (Product p:products) {
            System.out.println(p);
        }
        model.addAttribute("products",products);
        model.addAttribute("category",category);
        model.addAttribute("pageModel",pageModel);
        return "/client/product_list.jsp";
    }

    //按商品名称查询
    @RequestMapping("/findProductByName")
    public String findProductByName(@RequestParam(defaultValue = "1") int pageIndex,String name,Model model) {
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Product> products = productService.findProductByName(name,pageModel);
        int recordCount = productService.findProductByNameCount(name);
        pageModel.setRocordCount(recordCount);
       /* for (Product p:products) {
            System.out.println(p);
        }*/
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("name",name);
        return "/client/product_search_list.jsp";
    }

    //按商品详细信息查询
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("p",product);
        return "/client/info.jsp";
    }

    //显示首页
    @RequestMapping("/showIndex")
    public String showIndex(Model model) {
        System.out.println("显示首页信息");
        Notice notice = productService.findNoticeRecent();
        model.addAttribute("n",notice);
        List<Product> products = productService.findWeekHotProduct();
        model.addAttribute("products",products);
        return "/client/index.jsp";
    }
}
