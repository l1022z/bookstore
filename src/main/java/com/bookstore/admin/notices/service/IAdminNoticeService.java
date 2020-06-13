package com.bookstore.admin.notices.service;

import com.bookstore.commons.beans.Notice;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/8
 */
public interface IAdminNoticeService {
    List<Notice> listNotice();

    void addNotice(Notice notice);

    void removeNotice(String id);

    Notice FindNoticeById(String id);

    void EditNotice(String id,String title,String details);
}
