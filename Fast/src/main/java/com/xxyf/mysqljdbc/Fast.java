package com.xxyf.mysqljdbc;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 16:26
 * @Version JDK 8
 */

public interface Fast extends FastOne {
    //查询指定单列
    String indiv(String sql, Object... param);

    String[] indivall(String sql, Object... param);

    String[] indivarr(String sql, Object... param);

    List<String[]> indivarrall(String sql, Object... param);

    //查询指定单列
    String indiv(String pojo, String sql, Object... param);

    //查询全部指定单列
    String[] indivall(String pojo, String sql, Object... param);

    //查询指定列
    String[] indivarr(String[] pojo, String sql, Object... param);

    //查询全部指定列
    List<String[]> indivarrall(String[] pojo, String sql, Object... param);

    <T> T into(Class<T> pojo, String sql, Object... param);

    <T> T into(Class<T> pojo, String sql, Map<String, String> maps, Object... param);

    <T> T into(Class<T> pojo, ResultSet resultSet);

    <T> T into(Class<T> pojo, ResultSet resultSet, Map<String, String> map);

    <T> List<T> listinto(Class<T> t, String sql, Object... param);

    <T> List<T> listinto(Class<T> t, String sql, Map<String, String> maps, Object... param);
}
