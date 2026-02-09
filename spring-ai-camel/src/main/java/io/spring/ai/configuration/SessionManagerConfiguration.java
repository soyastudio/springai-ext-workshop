package io.spring.ai.configuration;

import io.spring.ai.session.Request;
import io.spring.ai.session.Session;
import io.spring.ai.session.SessionManager;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

@Configuration
@EnableCaching
@EnableScheduling
public class SessionManagerConfiguration {
    private static final String SESSION_CACHE_NAME = "SESSION";

    private ConcurrentMap<String, Session> nativeCache;

    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = new ConcurrentMapCacheManager(SESSION_CACHE_NAME);
        nativeCache = (ConcurrentMap<String, Session>) cacheManager.getCache(SESSION_CACHE_NAME).getNativeCache();
        return cacheManager;
    }

    @Bean
    public Cache sessionCache(CacheManager cacheManager) {
        return cacheManager.getCache(SESSION_CACHE_NAME);
    }

    @CacheEvict(value = "SESSION", allEntries = true)
    @Scheduled(fixedRate = 900000L)
    public void evict() {
        System.out.println("--------------- " + nativeCache.size());
    }

    @Bean
    SessionManager sessionManager(CacheManager cacheManager) {

        return new SessionManager() {
            final Cache cache = Objects.requireNonNull(cacheManager.getCache(SESSION_CACHE_NAME));

            @Override
            public Session create(Request request) {
                Session session = new Session(request);
                cache.put(session.getId(), session);
                return session;
            }

            @Override
            public Session fetch(String id) {
                return Objects.requireNonNull(cache.get(id, Session.class)).touch();
            }

            @Override
            public void update(Session session) {
                cache.put(session.getId(), session);
                session.touch();
            }
        };
    }


}
