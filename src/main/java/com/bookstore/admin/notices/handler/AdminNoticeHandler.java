package com.bookstore.admin.notices.handler;

import com.bookstore.admin.notices.service.IAdminNoticeService;
import com.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/8
 */
@RequestMapping("/admin/notices")
@Controller
public class AdminNoticeHandler {

    @Autowired
    IAdminNoticeService adminNoticeService;

    @RequestMapping("/ListNotice")
    public String ListNotice(Model model){
        List<Notice> notices = adminNoticeService.listNotice();
        model.addAttribute("notices",notices);
        for (Notice p:notices) {
            System.out.println(p);
        }
        return "/admin/notices/list.jsp";
    }

    @RequestMapping("/AddNotice")
    public String AddNotice(Notice notice){
        System.out.println("添加");
        adminNoticeService.addNotice(notice);
        return "redirect:/admin/notices/ListNotice";
    }

    @RequestMapping("/FindNoticeById")
    public String FindNoticeById(String id,Model model){
        System.out.println("查询信息");
        Notice notice = adminNoticeService.FindNoticeById(id);
        model.addAttribute("n",notice);
        return "/admin/notices/edit.jsp";
    }

    @RequestMapping("/EditNotice")
    public String EditNotice(String id,String title,String details){
        adminNoticeService.EditNotice(id,title,details);
        return "redirect:/admin/notices/ListNotice";
    }
    @RequestMapping("/DeleteNoticeServlet")
    public String DeleteNoticeServlet(String id){
        System.out.println("删除");
        adminNoticeService.removeNotice(id);
        return "redirect:/admin/notices/ListNotice";
    }
}
