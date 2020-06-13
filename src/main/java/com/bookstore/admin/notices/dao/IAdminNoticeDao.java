package com.bookstore.admin.notices.dao;

import com.bookstore.commons.beans.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/8
 */
public interface IAdminNoticeDao {
    List<Notice> selectNotice();

    void insertNotice(Notice notice);

    void deleteNotice(String id);

    Notice selectNoticeById( String id);

    void updateNotice(@Param("id") String id, @Param("title") String title,@Param("details") String details);
}
