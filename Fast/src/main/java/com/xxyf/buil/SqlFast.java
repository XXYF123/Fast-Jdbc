package com.xxyf.buil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 小小怡飞
 * @Date 2022/8/11 9:19
 * @Version JDK 8
 */
public class SqlFast {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private static Map<String, ResultSet> buffer;
    private static int index;
    protected static int buffsize;
    private DataSource dataSource;

    static {
        buffer = new HashMap<>();
        buffsize = 10000;
        index = 0;

    }
    public SqlFast(DataSource dataSource){

    }
    public SqlFast(InputStream in){

    }

}
