package com.xxyf.mapper;

import com.xxyf.starter.FastConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小小怡飞
 * @Date 2022/8/19 1:52
 * @Version JDK 8
 */
@Configuration
public class Mapper implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    public Mapper(){

    }
    private static FastConfig fastConfig;
    private com.xxyf.mysqljdbc.Fast fast;
    private static ApplicationContext applicationContext;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {


        // 手动注册的Bean类型 ， 由于我要注册的其实是 接口的实现类
        // 所以采用FactoryBean方式创建Bean
        // 若仅仅需要对Bean注入值，可以直接指定想创建的Bean的class
        //AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(FastMapper.class.getName()));
//等把
        System.out.println("我是"+fast);
        System.out.println(ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        MyClasssPathBeanDefinitionScanner scanner = new MyClasssPathBeanDefinitionScanner(registry);
        scanner.registerFilters();
        //获取带有注释的方法
        //      Set<MethodMetadata> fastBuild = importingClassMetadata.getAnnotatedMethods("fastBuild");

//        String[] scans = annoAttrs.getStringArray("scan");

        scanner.doScan("");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public void setFastConfig(FastConfig fastConfig) {
        this.fastConfig = fastConfig;
    }

    public void setFast(com.xxyf.mysqljdbc.Fast fast) {
        this.fast = fast;
    }
}
