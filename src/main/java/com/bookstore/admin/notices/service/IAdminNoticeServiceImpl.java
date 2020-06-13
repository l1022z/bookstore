package com.bookstore.admin.notices.service;

import com.bookstore.admin.notices.dao.IAdminNoticeDao;
import com.bookstore.commons.beans.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/8
 */
@Service
public class IAdminNoticeServiceImpl implements IAdminNoticeService{
    @Resource
    IAdminNoticeDao adminNoticeDao;
    @Override
    public List<Notice> listNotice() {
        return adminNoticeDao.selectNotice();
    }

    @Override
    public void addNotice(Notice notice) {
        adminNoticeDao.insertNotice(notice);
    }

    @Override
    public void removeNotice(String id) {
        adminNoticeDao.deleteNotice(id);
    }

    @Override
    public Notice FindNoticeById(String id) {
        return adminNoticeDao.selectNoticeById(id);
    }

    @Override
    public void EditNotice(String id,String title,String details) {
        adminNoticeDao.updateNotice(id,title,details);
    }
}
