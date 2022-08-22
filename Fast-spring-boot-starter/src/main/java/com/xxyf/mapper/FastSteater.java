package com.xxyf.mapper;

import com.xxyf.buil.FastBuild;
import com.xxyf.mysqljdbc.Fast;
import com.xxyf.note.FastMapper;
import com.xxyf.tools.Strif;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;


/**
 * @Author 小小怡飞
 * @Date 2022/8/18 4:28
 * @Version JDK 8
 */

@Configuration
public class FastSteater implements ImportBeanDefinitionRegistrar,BeanFactoryAware{

    public FastSteater(){

        System.out.println("我是fast");
    }
    private Fast fast;
    private BeanFactory beanFactory;
    private ResourceLoader resourceLoader;
    private static BeanFactory applicationContext;

    private static FastBuild fastBuild;
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("怎么说");
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(FastMapper.class.getName()));
//等把
        String packageName = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        System.out.println("开始");
        System.out.println(packageName);
        System.out.println(ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        MyClasssPathBeanDefinitionScanner scanner = new MyClasssPathBeanDefinitionScanner(registry);
        scanner.registerFilters();
        //获取带有注释的方法
        //      Set<MethodMetadata> fastBuild = importingClassMetadata.getAnnotatedMethods("fastBuild");

        String[] scans = annoAttrs.getStringArray("value");
        if (Strif.arrnull(scans)){
            System.out.println("走了这里");
            scanner.doScan(scans);
        }else {
            System.out.println("走了这");
            scanner.doScan(packageName);
        }


    }

    public FastBuild getFastBuild() {
        return fastBuild;
    }

    public void setFastBuild(FastBuild fastBuild) {
        this.fastBuild = fastBuild;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        applicationContext=beanFactory;
    }

    public static BeanFactory getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
