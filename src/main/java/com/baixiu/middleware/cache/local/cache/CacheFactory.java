package com.baixiu.middleware.cache.local.cache;

import com.baixiu.middleware.cache.local.config.CaffeineProperties;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author baixiu
 * @date 创建时间 2024/6/4 4:30 PM
 */
@Component
public class CacheFactory {
    
    public void createCacheBean(CaffeineProperties caffeineProperties, ApplicationContext applicationContext){
        //bean definition builder 
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition (CaffeineCache.class);
        //主动设置配置
        builder.addPropertyValue ("caffeineProperties",caffeineProperties);
        //主动设置 cache loader
        builder.addPropertyValue ("cacheLoader",applicationContext.getBean (caffeineProperties.getCacheLoaderName()));
        builder.getBeanDefinition().setBeanClassName("com.baixiu.middleware.cache.local.cache.CaffeineCache");
        //bean definition 主动注册
        ((DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory())
                .registerBeanDefinition(caffeineProperties.getCaffeineBeanName(),builder.getBeanDefinition());
    }
    
}
