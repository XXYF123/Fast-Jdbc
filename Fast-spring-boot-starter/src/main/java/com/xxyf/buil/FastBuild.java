package com.xxyf.buil;

import com.xxyf.erro.Jdbcinfo;
import com.xxyf.mysqljdbc.Fast;
import com.xxyf.mysqljdbc.FastCom;
import com.xxyf.starter.FastConfig;
import com.xxyf.tools.Strif;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author 小小怡飞
 * @Date 2022/8/4 1:20
 * @Version JDK 8
 */
public class FastBuild {
    private Properties properties;
    private Map<String, ResultSet> buff;
    private int buffSize;
    private int index;
    private boolean tm;
    private DataSource dataSource;
    public FastBuild(){

    }
    public FastBuild(InputStream in) throws Jdbcinfo {
        if (in==null){
            throw new Jdbcinfo("数据为空");
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        __init__();
        dataSource= new com.xxyf.datasource.Fast(properties).build();
    }
    public FastBuild(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public FastBuild(Properties in) throws Jdbcinfo {
        if (in==null){
            throw new Jdbcinfo("数据为空");
        }
        this.properties=in;
        __init__();
        dataSource= new com.xxyf.datasource.Fast(properties).build();
    }

    public FastBuild(Properties in,DataSource dataSource) throws Jdbcinfo {
        if (in==null){
            throw new Jdbcinfo("数据为空");
        }
        this.properties=in;
        __init__();
        this.dataSource=dataSource;
    }

    public FastBuild(InputStream in,DataSource dataSource) throws Jdbcinfo {
        if (in==null){
            throw new Jdbcinfo("数据为空");
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        __init__();
        this.dataSource=dataSource;
    }
    public FastBuild(FastConfig fastConfig,DataSource dataSource){
        if (fastConfig!=null){
        int buffSize = fastConfig.getBuffSize();
        boolean tm = fastConfig.isTm();

        if (buffSize>5000){
            this.buffSize=buffSize;
        }
        this.tm=tm;}
        this.dataSource=dataSource;
    }

    {
        buffSize=10000;
        buff= new HashMap<>();
        index=0;
        properties= new Properties();
    }
    private void __init__(){
        String buffSize = properties.getProperty("buffSize");
        if(Strif.sisInteger(buffSize)){
            int integer = Integer.parseInt(buffSize);
            if (integer>0){
                this.buffSize= integer;
            }
        }else {
            this.buffSize=1000;
        }
        String tm1 = properties.getProperty("tm");
        if(Strif.strnull(tm1)){

            if (tm1!="true"){
                this.tm=false;
            }
        }else {
            this.tm=true;
        }
    }
    public Fast build(){
        return new FastCom(this,dataSource,tm);
    }


    public Map<String, ResultSet> getBuff() {
        return buff;
    }

    public void setBuff(Map<String, ResultSet> buff) {
        this.buff = buff;
    }

    public int getBuffSize() {
        return buffSize;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isTm() {
        return tm;
    }

    public void setTm(boolean tm) {
        this.tm = tm;
    }
}
