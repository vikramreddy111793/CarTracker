package com.vbhoomidi;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vikramreddy on 7/14/2017.
 */
@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean(destroyMethod = "shutdown")
    public CacheManager ehCacheManager(){
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfig("vehicleslist",1));
        config.addCache(cacheConfig("vehiclesbyID",20));
        config.addCache(cacheConfig("alertsbyVIN",20));
        config.addCache(cacheConfig("readingsbyVIN",20));
        return CacheManager.newInstance(config);
    }

    @Bean
    public org.springframework.cache.CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    public KeyGenerator keyGenerator(){
        return new SimpleKeyGenerator();
    }

    @Bean
    public CacheResolver cacheResolver(){
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    public CacheErrorHandler errorHandler(){
        return new SimpleCacheErrorHandler();
    }

    private  CacheConfiguration cacheConfig(String name, long maxEntries){
        CacheConfiguration config = new CacheConfiguration();
        config.setName(name);
        config.setMaxEntriesLocalHeap(maxEntries);
        config.eternal(false);
        config.setMemoryStoreEvictionPolicy("LRU");
        return config;
    }

}
