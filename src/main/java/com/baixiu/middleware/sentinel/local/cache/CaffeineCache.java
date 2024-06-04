package com.baixiu.middleware.sentinel.local.cache;

import com.baixiu.middleware.sentinel.local.config.CaffeineProperties;
import com.github.benmanes.caffeine.cache.*;
import java.util.concurrent.TimeUnit;

/**
 * @author baixiu
 * @date 创建时间 2024/6/4 3:30 PM
 */
public class CaffeineCache <K,V> implements Cache{

    /**
     * loading cache .get 为 null .会自动调用 cache loader 
     * 需要主动实现 cache loader
     */
    private LoadingCache<K,V> loadingCache;
    
    private CaffeineProperties caffeineProperties;

    private RemovalListener removalListener;
    
    private CacheLoader cacheLoader;


    /**
     * put to cache
     * @param key key
     * @param value value
     * @throws Exception
     */
    public void putToLoadCache(K key,V value)throws Exception{
        initCacheBean();
        if(loadingCache==null){
            throw new RuntimeException ("cache bean is not init ");
        }
        loadingCache.put(key,value);
    }

    /**
     * get local cache by key .if null to reload from class loader
     * @param key key 
     * @return cache value 
     */
    public V getByLoadCache(K key){
        initCacheBean();
        return loadingCache.get(key);
    }

    private void initCacheBean() {
        if(caffeineProperties==null){
            throw new RuntimeException ("cache bean config is null");
        }
        if(loadingCache==null && caffeineProperties.getRefreshClientFlag()==0){
            
            Caffeine<Object, Object> caffeine=Caffeine.newBuilder ().initialCapacity (caffeineProperties.getInitialCapacity ())
                    .maximumSize (caffeineProperties.getMaximumSize ());
            
            //缓存项在给定时间内没有被写入（创建或覆盖），则回收
            if(caffeineProperties.getExpireFormat()==1){
                caffeine.expireAfterWrite (caffeineProperties.getExpireTime(), TimeUnit.MILLISECONDS);
            }
            
            //没有被访问或者被写入（创建或覆盖），则回收
            if(caffeineProperties.getExpireFormat()==2){
                caffeine.expireAfterAccess (caffeineProperties.getExpireTime(), TimeUnit.MILLISECONDS);
            }
            
            //不过期
            if(caffeineProperties.getExpireFormat()==3){
                caffeine.expireAfter(new Expiry<String, String> () {
                    @Override
                    public long expireAfterCreate(String key, String value, long currentTime) {
                        return Long.MAX_VALUE;
                    }

                    @Override
                    public long expireAfterUpdate(String key, String value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(String key, String value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                });
            }
            
            //设置 cache loader
            if(cacheLoader!=null){
                this.loadingCache=caffeine.build(cacheLoader);                                        
            }
            
            //设置 cache remove listener
            if(removalListener!=null){
                caffeine.removalListener(removalListener);
            }
            
            
        }
      
                
    }

    public CaffeineProperties getCaffeineProperties() {
        return caffeineProperties;
    }

    public void setCaffeineProperties(CaffeineProperties caffeineProperties) {
        this.caffeineProperties = caffeineProperties;
    }

    public RemovalListener getRemovalListener() {
        return removalListener;
    }

    public void setRemovalListener(RemovalListener removalListener) {
        this.removalListener = removalListener;
    }

    public CacheLoader getCacheLoader() {
        return cacheLoader;
    }

    public void setCacheLoader(CacheLoader cacheLoader) {
        this.cacheLoader = cacheLoader;
    }
}
