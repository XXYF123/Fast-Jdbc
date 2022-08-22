package com.xxyf.mapper;

import com.xxyf.note.Fast;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class MyClasssPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends FastBean> fastBeanClass = FastBean.class;


      public MyClasssPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
          super(registry,false);



    }
  
      /**
       * @Author haien
       * @Description 注册条件过滤器，将@Fast注解加入允许扫描的过滤器中，
       * 如果加入排除扫描的过滤器集合excludeFilter中，则不会扫描
       * @Date 2019/6/11
       * @Param []
       * @return void
       **/
      public void registerFilters(){
            addIncludeFilter(new AnnotationTypeFilter(Fast.class));

      }



    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
          Set<BeanDefinitionHolder> set= super.doScan(basePackages);
          System.out.println("set为："+set.size());
          proxpBean(set);
          return set;
      }

      private void proxpBean(Set<BeanDefinitionHolder> set){


          GenericBeanDefinition definition;
          for (BeanDefinitionHolder holder : set) {
              definition = (GenericBeanDefinition) holder.getBeanDefinition();
              definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(definition.getBeanClassName()));
//              Class<?> beanClass = definition.getBeanClass();
                //应该加入Fastbean的
              definition.setBeanClass(FastBean.class);
          }
      }

    public MyClasssPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public MyClasssPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public MyClasssPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    public void setBeanDefinitionDefaults(BeanDefinitionDefaults beanDefinitionDefaults) {
        super.setBeanDefinitionDefaults(beanDefinitionDefaults);
    }

    @Override
    public BeanDefinitionDefaults getBeanDefinitionDefaults() {
        return super.getBeanDefinitionDefaults();
    }

    @Override
    public void setAutowireCandidatePatterns(String... autowireCandidatePatterns) {
        super.setAutowireCandidatePatterns(autowireCandidatePatterns);
    }

    @Override
    public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
        super.setBeanNameGenerator(beanNameGenerator);
    }

    @Override
    public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
        super.setScopeMetadataResolver(scopeMetadataResolver);
    }

    @Override
    public void setScopedProxyMode(ScopedProxyMode scopedProxyMode) {
        super.setScopedProxyMode(scopedProxyMode);
    }

    @Override
    public void setIncludeAnnotationConfig(boolean includeAnnotationConfig) {
        super.setIncludeAnnotationConfig(includeAnnotationConfig);
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }

    @Override
    protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
        super.postProcessBeanDefinition(beanDefinition, beanName);
    }

    @Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        super.registerBeanDefinition(definitionHolder, registry);
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        return super.checkCandidate(beanName, beanDefinition);
    }

    @Override
    protected boolean isCompatible(BeanDefinition newDefinition, BeanDefinition existingDefinition) {
        return super.isCompatible(newDefinition, existingDefinition);
    }

    @Override
    public void setResourcePattern(String resourcePattern) {
        super.setResourcePattern(resourcePattern);
    }

    @Override
    public void addIncludeFilter(TypeFilter includeFilter) {
        super.addIncludeFilter(includeFilter);
    }

    @Override
    public void addExcludeFilter(TypeFilter excludeFilter) {
        super.addExcludeFilter(excludeFilter);
    }

    @Override
    public void resetFilters(boolean useDefaultFilters) {
        super.resetFilters(useDefaultFilters);
    }

    @Override
    protected void registerDefaultFilters() {
        super.registerDefaultFilters();
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        super.setResourceLoader(resourceLoader);
    }

    @Override
    public void setMetadataReaderFactory(MetadataReaderFactory metadataReaderFactory) {
        super.setMetadataReaderFactory(metadataReaderFactory);
    }

    @Override
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        return super.findCandidateComponents(basePackage);
    }

    @Override
    protected String resolveBasePackage(String basePackage) {
        return super.resolveBasePackage(basePackage);
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return super.isCandidateComponent(metadataReader);
    }
    //只扫描接口
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    public void clearCache() {
        super.clearCache();
    }
}
