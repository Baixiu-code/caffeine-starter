package com.baixiu.middleware.sentinel.local.config;

/**
 * cache properties
 * @author baixiu
 * @date 创建时间 2024/6/4 2:07 PM
 */
public class CaffeineProperties {


    /**
     * 支持多个 caffeineBean注入
     */
    private String caffeineBeanName;
    
    
    /**
     * caffeine biz key.el:flowLimitPin
     */
    private String caffeineBizKey;
    
    
    /**
     * 0.标识不需要进行 cache client 重新加载
     * 1.标识需要进行 cache client 重新加载
     */
    private Integer refreshClientFlag = 0;

    /**
     * 初始容量
     */
    private Integer initialCapacity = 1000;

    /**
     * 缓存最大容量
     */
    private Integer maximumSize = 1000;

    /**
     * 1：after write方式
     * 2：after Access方式
     * 3: 永不失效
     */
    private Integer expireFormat = 1;

    /**
     * 过期时间 毫秒
     */
    private Integer expireTime = 500;

    public Integer getRefreshClientFlag() {
        return refreshClientFlag;
    }

    public void setRefreshClientFlag(Integer refreshClientFlag) {
        this.refreshClientFlag = refreshClientFlag;
    }

    public Integer getInitialCapacity() {
        return initialCapacity;
    }

    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Integer maximumSize) {
        this.maximumSize = maximumSize;
    }

    public Integer getExpireFormat() {
        return expireFormat;
    }

    public void setExpireFormat(Integer expireFormat) {
        this.expireFormat = expireFormat;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getCaffeineBizKey() {
        return caffeineBizKey;
    }

    public void setCaffeineBizKey(String caffeineBizKey) {
        this.caffeineBizKey = caffeineBizKey;
    }

    public String getCaffeineBeanName() {
        return caffeineBeanName;
    }

    public void setCaffeineBeanName(String caffeineBeanName) {
        this.caffeineBeanName = caffeineBeanName;
    }
}
