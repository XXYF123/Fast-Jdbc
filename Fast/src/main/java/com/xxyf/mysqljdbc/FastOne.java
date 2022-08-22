package com.xxyf.mysqljdbc;

import java.sql.SQLException;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 16:24
 * @Version JDK 8
 */
public interface FastOne {
    void steAutoCommit(boolean t) throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close();

    int sqlupdata(String sql, Object... i);

    <T> T mzrimp(Class<T> mzr);
}
