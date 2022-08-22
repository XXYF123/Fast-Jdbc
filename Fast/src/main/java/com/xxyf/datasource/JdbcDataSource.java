package com.xxyf.datasource;

import com.xxyf.erro.Jdbcinfo;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Author 小小怡飞
 * @Date 2022/8/11 3:43
 * @Version JDK 8
 */
public class JdbcDataSource implements DataSource {

    private static String url;
    private static String driver;
    private static Properties properties;
    private static String username;
    private static String password;
    private InputStream in;
    private boolean init=true;
    private static int MaxSize;
    private static int MinSize;
    static int LoginTimeout;
    private LinkedList<Connection> cons;
    {
        try {

            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cons=new LinkedList<>();
        __init(0);
    }
    public void __init(int size){
        for (int i=size;i<getMaxSize();i++){
            try {
                cons.addLast(createCon());
            } catch (Jdbcinfo jdbcinfo) {
                jdbcinfo.printStackTrace();
            }
        }
    }
    public Connection createCon() throws Jdbcinfo {
        try {
            return new MysqlInfo().getConnection();
        } catch (Jdbcinfo jdbcinfo) {
            jdbcinfo.printStackTrace();
        }
        throw  new Jdbcinfo("数据库连接初始化失败");
    }

//    定义一个数据连接
    private class MysqlInfo extends XConnection{
        private boolean isw=true;
        private Connection connection = null;
        public MysqlInfo() throws Jdbcinfo {
            initConnection();
        }
        public void initConnection() {
            String s = getUrl();
            try {
                connection = DriverManager.getConnection(s, getUsername(),getPassword());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public Connection getConnection() {
            return new XConnection(connection,cons);
        }
        public void up(){
            this.isw=true;
        }
        public void off(){
            this.isw=false;
        }
    }
    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (cons.size()>getMaxSize()){
            cons.subList(getMaxSize(),cons.size()).clear();
        }
        if(cons.size()>getMinSize()){
            Connection connection = cons.removeLast();
            if (connection!=null){
                return connection;
            }else {
                try {
                    return createCon();
                } catch (Jdbcinfo jdbcinfo) {
                    jdbcinfo.printStackTrace();
                }
            }
        }else if(cons.size()==getMinSize()){
            try {
                Thread.sleep(getLoginTimeout());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(cons.size()==getMinSize()) {
                try {
                    return createCon();
                } catch (Jdbcinfo jdbcinfo) {
                    jdbcinfo.printStackTrace();
                }
            }else if(cons.size()>=getMinSize()){
                Connection connection = cons.removeLast();
                if (connection!=null){
                    return connection;
                }else {
                    try {
                        return createCon();
                    } catch (Jdbcinfo jdbcinfo) {
                        jdbcinfo.printStackTrace();
                    }
                }
            }else {
                __init(cons.size());
                return getConnection();
            }
        }
            __init(cons.size());
            return getConnection();
    }
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface)  {
        return false;
    }

    @Override
    public PrintWriter getLogWriter()  {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out)  {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {
        return LoginTimeout;
    }

    @Override
    public Logger getParentLogger()  {
        return null;
    }



    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    public InputStream getIn() {
        return in;
    }

    public boolean isInit() {
        return init;
    }

    public static int getMaxSize() {
        return MaxSize;
    }

    public static int getMinSize() {
        return MinSize;
    }

    public LinkedList<Connection> getCons() {
        return cons;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDriver() {
        return driver;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setDriver(String driver) {
        JdbcDataSource.driver = driver;
    }

    public static void setProperties(Properties properties) {
        JdbcDataSource.properties = properties;
    }

    public static void setMaxSize(int maxSize) {
        MaxSize = maxSize;
    }

    public static void setMinSize(int minSize) {
        MinSize = minSize;
    }

    public static void setUrl(String url) {
        JdbcDataSource.url = url;
    }

    public static void setUsername(String username) {
        JdbcDataSource.username = username;
    }

    public static void setPassword(String password) {
        JdbcDataSource.password = password;
    }
}
