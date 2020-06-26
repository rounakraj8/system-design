package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.util.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Log4j2 @Service public class FindNodeServiceImpl implements FindNodeService {

    private static final int NO_OF_REPLICAS = 1;
    private static final MD5HashFunction hashFunction = new MD5HashFunction();

    private final CacheInfoService cacheInfoService;

    private ConsistentHash<String> stringConsistentHash;

    public FindNodeServiceImpl(CacheInfoService cacheInfoService) {
        this.cacheInfoService = cacheInfoService;
        stringConsistentHash = new ConsistentHash<>(hashFunction, NO_OF_REPLICAS,
                cacheInfoService.getCacheNodes().keySet());
    }

    @Override public String getNode(String key) {
        String node = stringConsistentHash.get(key);
        log.info("{} ==> {}", key, node);
        return node;
    }
}
