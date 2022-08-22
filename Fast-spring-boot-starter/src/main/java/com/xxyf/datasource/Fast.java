package com.xxyf.datasource;

import com.xxyf.erro.Jdbcinfo;
import com.xxyf.tools.Strif;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author 小小怡飞
 * @Date 2022/8/11 22:46
 * @Version JDK 8
 */
public class Fast implements Fastins {
    protected static String url;
    protected static String driver;
    protected static Properties properties;
    protected static String username;
    protected static String password;
    protected int MaxSize;
    protected int MinSize;
    private final DataSource jdbcDataSource;
    private int LoginTimeout;
    public Fast(InputStream in) {
        try {
            in_per(in);
        } catch (Jdbcinfo jdbcinfo) {
            jdbcinfo.printStackTrace();
        }
      jdbcDataSource=aoto();
    }
    public Fast(Properties pro){
        properties=pro;
        __init__(pro);
        jdbcDataSource=aoto();
    }
    private JdbcDataSource aoto(){
        JdbcDataSource.LoginTimeout= LoginTimeout;
        JdbcDataSource.setPassword(getPassword());
        JdbcDataSource.setUrl(getUrl());
        JdbcDataSource.setUsername(getUsername());
        JdbcDataSource.setMaxSize(getMaxSize());
        JdbcDataSource.setMinSize(getMinSize());
        JdbcDataSource.setDriver(getDriver());
        return new JdbcDataSource();
    }
    private void in_per(InputStream in ) throws Jdbcinfo {
        if (in == null) {
            File file = new File("jdbc.properties");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter myWriter = new FileWriter("jdbc.properties");
                    myWriter.write("url=" + url + "\ndriver=" + driver + "\nusername=" + username + "\npassword=" + password);
                    myWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new Jdbcinfo("根目录下存在该文件请填写,url,driver,username,password");
            }
            throw new Jdbcinfo("文件不存在，默认文件保存为类目录下的jdbc.properties，现已经自动生成");
        }
        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        __init__(properties);
    }
    void __init__(Properties pro){
        url = properties.getProperty("url");
        driver = properties.getProperty("driver");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        String maxSize = properties.getProperty("MaxSize");
        String Log = properties.getProperty("LoginTimeout");

        if(Strif.sisInteger(Log)){
            int integer = Integer.parseInt(Log);
            if (integer>100){
                this.LoginTimeout= integer;
            }
        }else {
            LoginTimeout=1000;
        }
        if(Strif.sisInteger(maxSize)){
            int integer = Integer.parseInt(maxSize);
            if (integer>10){
                this.MaxSize= integer;
            }
        }else {
            MaxSize=50;
        }
        String minSize = properties.getProperty("MinSize");
        if(Strif.sisInteger(minSize)){
            int integer = Integer.parseInt(minSize);
            if (integer>0&&integer<MaxSize){
                this.MinSize= integer;
            }
        }else {
            MinSize=5;
        }
    }
    public DataSource build(){
        return jdbcDataSource;
    }

    public int getMaxSize() {
        return MaxSize;
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

    public int getMinSize() {
        return MinSize;
    }
}
