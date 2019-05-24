package com.longjing.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 18746 on 2019/5/24.
 */
public class Util {
    public static String md5(String str) {
        MessageDigest messageDigest= null;
        StringBuffer buffer = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("md5");
            byte[] bytes=messageDigest.digest(str.getBytes());
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : bytes) {
                // 与运算
                int number = b & 0xff;// 加盐
                String s = Integer.toHexString(number);
                if (s.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 标准的md5加密后的结果
        return buffer.toString().toUpperCase();
    }

}
