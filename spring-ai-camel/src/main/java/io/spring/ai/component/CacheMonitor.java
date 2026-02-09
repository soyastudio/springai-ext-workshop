package io.spring.ai.component;

import io.spring.ai.session.Session;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;

@Component
public class CacheMonitor extends RouteBuilder {

    @Autowired
    private Cache cache;

    @Override
    public void configure() throws Exception {

        from("timer://myTimer?period=1000")
                .process(this::process);
    }

    void process(Exchange exchange) {
        ConcurrentMap<String, Session> nativeCache = (ConcurrentMap<String, Session>) cache.getNativeCache();
        //System.out.println("----------- " + nativeCache.size());
    }


}
