package com.xxyf.mapper;

import com.xxyf.buil.FastBuild;
import com.xxyf.mysqljdbc.Fast;
import com.xxyf.starter.FastConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author 小小怡飞
 * @Date 2022/8/19 16:48
 * @Version JDK 8
 */

public class FastBean<T> implements FactoryBean<T> , ApplicationContextAware, InitializingBean {
    private String name;
    private Class<T> fastInterface;
    private FastBuild fastBuild;
    private Fast fast;
    private FastConfig fastConfig;
    private static ApplicationContext applicationContext;
    public FastBean(Class<T> f){
        fastInterface= (Class<T>) f;

    }

    //是否返回单例
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
    //返回bean
    @Override
    public T getObject() throws Exception {
        System.out.println("我在"+applicationContext);


        if (getFast()==null){
             try {
                 FastBuild bean = applicationContext.getBean(FastBuild.class);
                 System.out.println("成功");
                 System.out.println("你"+bean);
                 System.out.println(fastInterface+"我空嘛");
                 return bean.build().mzrimp(fastInterface);
             }catch (BeansException e){
                 try {
                     FastBuild fastBuild = (FastBuild) applicationContext.getBean("fastBuild");
                     System.out.println("成功");
                     return fastBuild.build().mzrimp(fastInterface);
                 }catch (BeansException e1){

                     try{
                         FastBuild bean = applicationContext.getBean("fastBuild",FastBuild.class);
                         return bean.build().mzrimp(fastInterface);
                     }catch (BeansException e2){

                         System.out.println("失败");
                     }
                 }


             }
        }
        System.out.println("我是空指针"+getFast());


        return null;
    }
    //返回bean的反射

    @Override
    public Class<?> getObjectType() {
        return this.fastInterface;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<T> getFastInterface() {
        return fastInterface;
    }

    public void setFastInterface(Class<T> fastInterface) {
        this.fastInterface = fastInterface;
    }

    public FastBuild getFastBuild() {
        return fastBuild;
    }

    public void setFastBuild(FastBuild fastBuild) {
        this.fastBuild = fastBuild;
    }

    public Fast getFast() {
        return fast;
    }

    public void setFast(Fast fast) {
        this.fast = fast;
    }

    public FastConfig getFastConfig() {
        return fastConfig;
    }

    public void setFastConfig(FastConfig fastConfig) {
        this.fastConfig = fastConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
