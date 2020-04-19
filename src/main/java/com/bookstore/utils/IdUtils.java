package com.bookstore.utils;

import java.util.UUID;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/4/19
 */
public class IdUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
