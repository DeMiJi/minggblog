package com.mingg.minggblog.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    //改用Spring的DigestUtils实现md5码加密
    public static void main(String[] args) {
        String tbUser = "123456";
        String psd = DigestUtils.md5DigestAsHex(tbUser.getBytes());
    }
}
