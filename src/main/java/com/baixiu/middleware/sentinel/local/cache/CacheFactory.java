package com.baixiu.middleware.sentinel.local.cache;

import com.baixiu.middleware.sentinel.local.config.CaffeineProperties;
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
        builder.addPropertyValue ("caffeineProperties",caffeineProperties);
        builder.getBeanDefinition().setBeanClassName("com.baixiu.middleware.sentinel.local.cache.CaffeineCache");
        ((DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory())
                .registerBeanDefinition(caffeineProperties.getCaffeineBeanName(),builder.getBeanDefinition());
    }
    
}
