package com.xxyf.mysqljdbc;

import com.xxyf.buil.FastBuild;
import com.xxyf.tools.Strif;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 5:36
 * @Version JDK 8
 */
public class FastJdbc implements Jdbc {
    private PreparedStatement preparedStatement = null;
    protected DataSource dataSource;
    protected FastBuild fast;
    protected Connection connection;
    protected boolean tm=true;

    private void setBuffer(String sql, ResultSet re, Object... i) {
        if (fast.getBuff().size() > fast.getBuffSize()) {
            colseBuffer();
        }
        String s = sql + Arrays.toString(i);
        fast.getBuff().put(s, re);
    }
    @Override
    public void colseBuffer() {
        fast.getBuff().clear();
    }
    //查询
    @Override
    public ResultSet sqlquery(String sql, Object... i) {
        if (fast.getBuff().containsKey(sql + Arrays.toString(i))) {
            return fast.getBuff().get(sql + Arrays.toString(i));
        }
        PreparedStatement pr = null;
        pr = execut(sql, i);
        ResultSet re = null;
        try {
            re = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setBuffer(sql, re, i);
        preparedStatement=pr;
        if (tm){
        try {
            if (connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return re;
    }

    //编译sql
    @Override
    public PreparedStatement execut(String sql, Object... i) {
        PreparedStatement pr = null;
        try {
            System.out.println(dataSource.getClass().getName());
            connection = dataSource.getConnection();
            pr = connection
                    .prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (i != null) {
            for (int i1 = 1; i1 <= i.length; i1++) {
                try {
                    pr.setObject(i1, i[i1 - 1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pr;
    }
    //更新
    @Override
    public int sqlupdata(String sql, Object... i) {
        PreparedStatement pr = execut(sql, i);

        int index = 0;
        try {
            index = pr.executeUpdate();
            colseBuffer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colseBuffer();
        preparedStatement=pr;
        if(tm){
        try {
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return index;
    }
    @Override
    public String inre(ResultSet resultSet) {
        String pojos = null;
        try {
            if (resultSet.next()) {
                pojos = resultSet.getString(1);
                resultSet.previous();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojos;
    }

    @Override
    public String[] inrearr(ResultSet resultSet) {
        String[] pojos = null;
        int index = 0;
        try {
            index = resultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()) {


                pojos = new String[index];
                for (int i = 1; i <= index; i++) {
                    pojos[i - 1] = resultSet.getString(i);
                }
                resultSet.previous();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojos;
    }
    @Override
    public String inre(String pojo, ResultSet resultSet) {
        if (!Strif.strnull(pojo)) {
            return inre(resultSet);
        }
        String pojos = null;
        try {
            if (resultSet.next()) {
                try {
                    pojos = resultSet.getString(pojo);
                } catch (SQLException ignored) {
                }

                resultSet.previous();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojos;
    }

    @Override
    public String[] inre(String[] pojo, ResultSet resultSet) {
        String[] pojos;
        if (Strif.arrnull(pojo)) {
            pojos = new String[pojo.length];
        } else {
            return inrearr(resultSet);
        }
        try {
            if (resultSet.next()) {
                for (int i = 0; i < pojos.length; i++) {
                    try {
                        pojos[i] = resultSet.getString(pojo[i]);
                    } catch (SQLException e) {
                        pojos[i] = null;
                    }

                }
                resultSet.previous();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojos;
    }


    @Override
    public void steAutoCommit(boolean t) throws SQLException {
        tm=false;
        connection.setAutoCommit(t);
    }
    @Override
    public void commit() throws SQLException {
        tm=true;
        connection.commit();
    }
    @Override
    public void rollback() throws SQLException {

        connection.rollback();
    }
    @Override
    public void close(){
        fast.setBuff(new HashMap<>());
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
