package com.xxyf.starter;

import com.xxyf.buil.FastBuild;
import com.xxyf.mapper.FastSteater;
import com.xxyf.mapper.Mapper;
import com.xxyf.mysqljdbc.Fast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @Author 小小怡飞
 * @Date 2022/8/18 6:44
 * @Version JDK 8
 */
@Configuration
@EnableConfigurationProperties(FastConfig.class)
@AutoConfigureBefore(Mapper.class) //先加载当前类

public class BootFast {


    public BootFast(){
        System.out.println("我是boot");


    }

    @Autowired
    FastConfig fastConfig;

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "fastBuild")
    public FastBuild fastBuild(@Autowired(required = false) DataSource dataSource,FastConfig fastConfig){
        System.out.println("你什么时候开始呀");
        return new FastBuild(fastConfig,dataSource);
    }
    @Bean(name = "fast")
    public Fast fast(FastBuild fastBuild){
        return fastBuild.build();
    }

    @ConditionalOnMissingBean(FastSteater.class)
    @Bean("mapper")
    public Mapper mapper(){
       return new Mapper();
    }


}
