package com.xxyf.mysqljdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 6:29
 * @Version JDK 8
 */

public interface Jdbc {
    void colseBuffer();

    ResultSet sqlquery(String sql, Object... i);

    //编译sql
    PreparedStatement execut(String sql, Object... i);

    //更新
    int sqlupdata(String sql, Object... i);

    String inre(ResultSet resultSet);

    String[] inrearr(ResultSet resultSet);

    String inre(String pojo, ResultSet resultSet);

    String[] inre(String[] pojo, ResultSet resultSet);

    void steAutoCommit(boolean t) throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close();
}
