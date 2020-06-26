package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.dto.*;
import com.systemdesign.distributedcache.loadbalancer.exception.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service @RequiredArgsConstructor public class LoadBalancerServiceImpl implements LoadBalancerService {

    public static final String FAIL = "FAIL";
    private final FindNodeService findNodeService;
    private final CacheService cacheService;

    @Override public KeyValuePair get(String key) {
        String node = findNodeService.getNode(key);
        try {
            return new KeyValuePair(key, cacheService.getValue(key, node));
        }
        catch (CacheMissException cacheMissException) {
            cacheMissException.printStackTrace();
        }
        return null;
    }

    @Override public String put(KeyValuePair keyValuePair) {
        String node = findNodeService.getNode(keyValuePair.getKey());
        try {
            cacheService.putValue(keyValuePair, node);
        }
        catch (CacheMissException cacheMissException) {
            cacheMissException.printStackTrace();
        }
        return FAIL;
    }
}
