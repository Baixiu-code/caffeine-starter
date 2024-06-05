package com.baixiu.middleware.cache.local.config;

import com.baixiu.middleware.cache.local.cache.CacheFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenfanglin1
 * @date 创建时间 2024/6/4 4:25 PM
 */
@Configuration
@EnableConfigurationProperties(value = CaffeineBeanProperties.class)
@ComponentScan("com.baixiu.middleware.sentinel.local")
public class CaffeineAutoConfig implements ApplicationContextAware {

    @Autowired
    private CaffeineBeanProperties caffeineBeanProperties;
    @Autowired
    private CacheFactory cacheFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(caffeineBeanProperties!=null){
            try {
                for (CaffeineProperties caffeineProperty : caffeineBeanProperties.getCaffeineProperties ()) {
                    cacheFactory.createCacheBean (caffeineProperty,applicationContext);
                }
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }
}
