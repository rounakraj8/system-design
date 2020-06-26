package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.api.*;
import com.systemdesign.distributedcache.loadbalancer.dto.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Service @Log4j2 @RequiredArgsConstructor public class CacheServiceImpl implements CacheService {

    private final CacheInfoService cacheInfoService;
    private final CacheClientRestTemplate cacheClient;

    @Override public String getValue(String key, String node) {
        String port = cacheInfoService.getCacheNodeValue(node);
        StringBuilder url = new StringBuilder().append("http://localhost:").
                append(port).append("/get/").append(key);
        log.info("Node->{}, URL->{}, Key->{}", node, url, key);
        try {
            return cacheClient.get(url.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public String putValue(KeyValuePair keyValuePair, String node) {
        return null;
    }
}
