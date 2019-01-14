package com.datangliang.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.datangliang.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.datangliang.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Isp.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.BankcardInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.EnterpriseInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.EnterpriseAuthRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.RealnameInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.DTLUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.UserLevel.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.UserRole.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.RecommendRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.SiteAuthRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Image.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Store.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Staff.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.SiteInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.RealnameAuthRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.BankcardAuthRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.StoreUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.StoreAuthRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.datangliang.app.domain.ProductRecord.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
