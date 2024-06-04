package com.baixiu.middleware.sentinel.local.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

/**
 * caffeine bean name ->match config 
 * @author baixiu
 * @date 创建时间 2024/6/4 4:22 PM
 */
@ConfigurationProperties(prefix = "caffeine-starter")
public class CaffeineBeanProperties {
    
    private List<CaffeineProperties> caffeineProperties;

    public List<CaffeineProperties> getCaffeineProperties() {
        return caffeineProperties;
    }

    public void setCaffeineProperties(List<CaffeineProperties> caffeineProperties) {
        this.caffeineProperties = caffeineProperties;
    }
}
