package com.bookstore.admin.products.handler;

import com.bookstore.admin.products.service.IAdminProductService;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import com.bookstore.utils.IdUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Controller
@RequestMapping("/admin/products")
public class AdminProductHandler {

    @Autowired
    IAdminProductService adminProductService;

    //获取商品列表，也就是商品管理查询全部商品信息
    @RequestMapping("/listProduct")
    public String listProduct(Model model) {
        List<Product> products = adminProductService.findProduct();
        model.addAttribute("products",products);
       /* for (Product p:products) {
            System.out.println(p);
        }*/
        return "/admin/products/list.jsp";
    }

    //查询商品信息按照多个条件
    @RequestMapping("/findProductByManyCondition")
    public String findProductByManyCondition(Product product,Double minprice,Double maxprice,Model model) {
        List<Product> products = adminProductService.findProductByManyCondition(product,minprice,maxprice);
        model.addAttribute("products",products);
        model.addAttribute("product",product);
        model.addAttribute("minprice",minprice);
        model.addAttribute("maxprice",maxprice);
        return "/admin/products/list.jsp";
    }

    //添加商品
    @RequestMapping("/addProduct")
    public String addProduct(Product product, MultipartFile upload, HttpSession session) throws IOException {
        //保存图片
        String path1 = "C:\\Users\\Administrator\\Desktop\\bookstore\\src\\main\\webapp\\productImg";
        String path = session.getServletContext().getRealPath("/productImg");
        System.out.println("文件上传路径："+path);

        //文件上传路不存在则创建对应目录
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filename = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
        upload.transferTo(new File(path,filename));
        //文件拷贝
        FileUtils.copyFile(new File(path,filename),new File(path1,filename));
        //upload.transferTo(new File(path1,filename));

        product.setId(IdUtils.getUUID());
        product.setImgurl("/productImg/"+filename);
        adminProductService.addProduct(product);

        return "redirect:/admin/products/listProduct";
    }

    //查找要修改的商品原始信息
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model) {
        Product product = adminProductService.findProductById(id);
        model.addAttribute("p",product);
        return "/admin/products/edit.jsp";
    }

    //编辑商品信息
    @RequestMapping("/editProduct")
    public String editProduct(Product product,MultipartFile upload,HttpSession session) throws IOException {
        if (!upload.isEmpty()) {//如果上传了新的图片
            //查找商品的原始信息
            Product target = adminProductService.findProductById(product.getId());
            //删除原始图片
            File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());
            if (targetFile.exists()) {
                targetFile.delete();
            }

            //保存新的图片
            String path = session.getServletContext().getRealPath("/productImg");
            String filename = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
            upload.transferTo(new File(path,filename));
            //如果上传了新的图片，给product的imgurl赋新的图片的地址
            product.setImgurl("/productImg/"+filename);
        }
        //修改数据库信息
        adminProductService.editProduct(product);
        return "redirect:/admin/products/listProduct";
    }

    //删除商品
    @RequestMapping("/removeProduct")
    public String removeProduct(String id,HttpSession session) {
        Product target = adminProductService.findProductById(id);
        File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());
        if (targetFile.exists()) {
            targetFile.delete();
        }
        adminProductService.removeProduct(id);
        return "redirect:/admin/products/listProduct";
    }

    //销售榜单
    @RequestMapping("/download")
    public void download(String year, String month, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ProductList> plist = adminProductService.findProductSalList(year,month);
        for (ProductList pl:plist) {
            System.out.println(pl);
        }

        String filename = year + "年" + month + "月销售榜单";
        String sheetName = month + "月销售榜单";
        String titleName = year + "年" + month + "月销售榜单";
        String[] columnName = {"商品名称","商品销量"};

        String[][] dataList = new String[plist.size()][2];
        for (int i=0;i<plist.size();i++) {
            dataList[i][0] = plist.get(i).getName();
            dataList[i][1] = plist.get(i).getSalnum();
        }

        //创建excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建excel中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //创建sheet的第一行
        HSSFRow row1 = sheet.createRow(0);
        //创建第一行的第一个单元格
        HSSFCell cell = row1.createCell(0);
        //合并第一行的两个单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        //给第一行的第一个合并后的单元格赋值
        cell.setCellValue(titleName);

        //创建第二行
        HSSFRow row = sheet.createRow(1);
        for (int i=0;i<2;i++) {
            row.createCell(i).setCellValue(columnName[i]);
        }

        //创建数据行
        for (int i=0;i<dataList.length;i++) {
            row = sheet.createRow(i+2);
            for (int j=0;j<2;j++) {
                row.createCell(j).setCellValue(dataList[i][j]);
            }
        }

        String fileName = filename + ".xls";
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+processFileName(request,fileName));
        OutputStream out = response.getOutputStream();
        wb.write(out);
    }

    //IE,Chrom,Firefox文件中乱码问题
    public  String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames,"UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

}

