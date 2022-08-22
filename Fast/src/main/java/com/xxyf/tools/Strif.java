package com.xxyf.tools;


import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author 小小怡飞
 * @Date 2022/7/8 23:16
 * @Version JDK 8
 */
public class Strif {

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    public static boolean sisInteger(String str){
        return strnull(str)&&isInteger(str);
    }
    public static boolean strnull(String str) {

        return str != null && str.length() != 0;
    }

    public static <T> boolean arrnull(T[] T) {
        return T != null && T.length != 0;
    }

    public static <T> boolean listnull(List<T> list) {
        return list != null && list.size() != 0;
    }
}
