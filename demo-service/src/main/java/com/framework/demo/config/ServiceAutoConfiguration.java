package com.framework.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ServiceAutoConfiguration {

//    @Bean
//    @ConditionalOnBean(RedisConnectionFactory.class)
//    @ConditionalOnMissingBean
//     CacheManager jCacheCacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(600)) // 设置缓存过期时间
//                .disableCachingNullValues(); // 禁止缓存 null 值
//
//        RedisCacheManager build = RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }

    @Bean
    public CacheManager jCacheCacheManager() {
        // 获取 Caffeine 的 CachingProvider
        javax.cache.spi.CachingProvider cachingProvider = Caching.getCachingProvider("com.github.ben-manes.caffeine.jcache.spi.CaffeineCachingProvider");

        // 创建 Caffeine 缓存配置
        Configuration<Object, Object> caffeineConfig = new MutableConfiguration<>()
                .setTypes(Object.class, Object.class)
                .setStoreByValue(false) // 设置为 true 时，缓存的值会被拷贝而不是直接引用
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 10))); // 设置失效时间

        // 创建 Caffeine CacheManager
        return cachingProvider.getCacheManager();
    }


}
