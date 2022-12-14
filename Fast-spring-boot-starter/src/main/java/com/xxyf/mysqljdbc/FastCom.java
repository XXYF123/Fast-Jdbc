package com.xxyf.mysqljdbc;

import com.xxyf.buil.FastBuild;
import com.xxyf.mysqlzr.FastProxp;
import com.xxyf.mysqlzr.Mzr;
import com.xxyf.tools.Strif;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 6:19
 * @Version JDK 8
 */
public class FastCom extends FastJdbc implements FastOne,Jdbc, Fast {
    public FastCom(FastBuild fast, DataSource dataSource, boolean tm){
        this.fast=fast;
        this.dataSource=dataSource;
        this.tm=tm;
    }

    //查询指定单列
    @Override
    public String indiv(String sql, Object... param) {
        return indiv(null, sql, param);
    }

    @Override
    public String[] indivall(String sql, Object... param) {
        return indivall(null, sql, param);
    }

    @Override
    public String[] indivarr(String sql, Object... param) {
        return indivarr(null, sql, param);
    }

    @Override
    public List<String[]> indivarrall(String sql, Object... param) {
        return indivarrall(null, sql, param);
    }

    //查询指定单列
    @Override
    public String indiv(String pojo, String sql, Object... param) {
        if (Strif.strnull(pojo)) {
            if (pojo.matches("^.*\\?.*$")) {
                return indiv(null, pojo, sql);
            }
        }
        ResultSet resultSet = sqlquery(sql, param);
        return inre(pojo, resultSet);
    }

    //查询全部指定单列
    @Override
    public String[] indivall(String pojo, String sql, Object... param) {
        if (Strif.strnull(pojo)) {
            if (pojo.matches("^.*\\?.*$")) {
                return indivall(null, pojo, sql);
            }
        }
        ResultSet resultSet = sqlquery(sql, param);
        List<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                resultSet.previous();

                list.add(inre(pojo, resultSet));

                resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[0]);
    }

    //查询指定列
    @Override
    public String[] indivarr(String[] pojo, String sql, Object... param) {

        return inre(pojo, sqlquery(sql, param));
    }

    //查询全部指定列
    @Override
    public List<String[]> indivarrall(String[] pojo, String sql, Object... param) {
        ResultSet resultSet = sqlquery(sql, param);
        List<String[]> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                resultSet.previous();
                list.add(inre(pojo, resultSet));
                resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public <T> T into(Class<T> pojo, String sql, Object... param) {
        ResultSet resultSet = sqlquery(sql, param);
        return into(pojo, resultSet, null);
    }

    @Override
    public <T> T into(Class<T> pojo, String sql, Map<String, String> maps, Object... param) {
        ResultSet resultSet = sqlquery(sql, param);
        return into(pojo, resultSet, maps);
    }

    @Override
    public <T> T into(Class<T> pojo, ResultSet resultSet) {
        return into(pojo, resultSet, null);
    }

    @Override
    public <T> T into(Class<T> pojo, ResultSet resultSet, Map<String, String> map) {
        T t1 = null;
        try {
            if (resultSet.next()) {

                //获取pojo类
//        Class<?> clt = t.getClass();
                //获取re类反射
                Class<? extends ResultSet> clr = resultSet.getClass();
                //第pojo类一会实例化用
                try {
                    t1 = (T) pojo.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                //获取t的字段于方法
                Object[][] file = Mzr.getFile(pojo);
                if (map != null) {
                    if (map.size() != 0) {
                        for (int x = 0; x < file[1].length; x++) {
                            if (map.get((String) file[1][x]) != null) {
                                file[1][x] = map.get((String) file[1][x]);
                            }
                        }
                    }
                }
                for (int i = 0; i < file[0].length; i++) {
                    try {
                        //获取pojo类方法
                        Method m = (Method) file[3][i];
                        //获取数据库的方法对应属性名获取
                        Method m1 = null;
                        try {
                            m1 = clr.getMethod("get" + (String) file[0][i], String.class);
                        } catch (NoSuchMethodException e) {
                            m1 = clr.getMethod("getString", String.class);
                        }

                        //获取字段名
                        String s = (String) file[1][i];
                        char[] c = s.toCharArray();
                        //首字母小写
                        c[0] += 32;
                        s = String.valueOf(c);
                        Object invoke = null;
                        //
                        try {
                            invoke = m1.invoke(resultSet, s);
                            m.invoke(t1, invoke);
                        } catch (IllegalAccessException | InvocationTargetException ignored) {
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                resultSet.previous();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t1;
    }

    @Override
    public <T> List<T> listinto(Class<T> t, String sql, Object... param) {
        return listinto(t, sql, null, param);
    }

    @Override
    public <T> List<T> listinto(Class<T> t, String sql, Map<String, String> maps, Object... param) {
        ResultSet resultSet = sqlquery(sql, param);
        List<T> list = new LinkedList<>();
        try {
            while (resultSet.next()) {
                resultSet.previous();
                list.add(into(t, resultSet, maps));
                resultSet.next();
            }
            resultSet.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    @Override
    public synchronized <T>  T mzrimp(Class<T> mzr){
        FastProxp<T> proxp1 = new FastProxp<>(mzr,this);
        return (T) proxp1.getPyxy1();
    }
}
