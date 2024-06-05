# caffeine-starter
for local cache caffeine starter 
# 1.使用手册
## 1.1 配置 以 caffeine-starter key 为前缀的配置 key
配置文件.支持多个配置.
- caffeineBeanName:必选. 本地缓存名称.需要支持多个场景下时需要保持唯一.
- cacheLoaderName:必选. loadCache 需要配合 cacheLoader.支持 cacheLoader 重用.
- caffeineBizKey:必选.业务 key.防止业务场景下的 key 冲突.需要支持多个场景下时需要保持唯一.
- refreshClientFlag:可选:0(默认值)|1. 0:不强制刷新 cache.1:需要重新刷新 cache 配置.配置如下的 key 所示.
- initialCapacity :可选.1000(默认值) 初始化容量.
- maximumSize:可选.1000(默认值) max 容量.
- expireFormat:
-   * 1：after write方式
    * 2：after Access方式
    * 3: 永不失效
- expireTime:可选 .过期时间.默认毫秒.默认值 500
```properties

caffeine-starter.caffeineProperties[0].caffeineBeanName=limitCaffeineCache
caffeine-starter.caffeineProperties[0].cacheLoaderName=limitCacheLoader
caffeine-starter.caffeineProperties[0].caffeineBizKey=limit
caffeine-starter.caffeineProperties[0].refreshClientFlag=1
caffeine-starter.caffeineProperties[0].initialCapacity=10
caffeine-starter.caffeineProperties[0].maximumSize=1000
caffeine-starter.caffeineProperties[0].expireFormat=1
caffeine-starter.caffeineProperties[0].expireTime=500


caffeine-starter.caffeineProperties[1].caffeineBeanName=limitCaffeineCache2
caffeine-starter.caffeineProperties[1].cacheLoaderName=limitCacheLoader
caffeine-starter.caffeineProperties[1].caffeineBizKey=limit
caffeine-starter.caffeineProperties[1].refreshClientFlag=1
caffeine-starter.caffeineProperties[1].initialCapacity=10
caffeine-starter.caffeineProperties[1].maximumSize=1000
caffeine-starter.caffeineProperties[1].expireFormat=1
caffeine-starter.caffeineProperties[1].expireTime=500
```
## 1.2 声明 cacheLoader 实现
如下.

```java

@Component("limitCacheLoader")
public class CacheLoader implements com.github.benmanes.caffeine.cache.CacheLoader<String, AtomicLong> {

    @Override
    public @Nullable AtomicLong load(@NonNull String s) throws Exception {
        return new AtomicLong(0);
    }
}

```

注意点.需要保持 1.1 中的配置 cacheLoaderName 为本 bean 同名.否则加载出现问题

## 1.2 使用

```java
  CaffeineCache limitCaffeineCache=SpringContextUtil.getBeanByName ("limitCaffeineCache");
  AtomicLong count =(AtomicLong) limitCaffeineCache.getByLoadCache(key);
  count.incrementAndGet ();
  System.out.println ("isLimitByKey:"+key+"count:"+count.longValue());  
```
