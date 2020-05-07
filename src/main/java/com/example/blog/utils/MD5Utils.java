package com.example.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     *
     * @param str 要加密的字符串
     * @return  加密之后的字符串
     */
    public static String code(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset = 0; offset < digest.length; offset++){
                i = digest[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32为加密
            return buffer.toString();
            //16位加密
            //return buffer.toString().substring(8,24);

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
