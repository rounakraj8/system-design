package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.api.*;
import com.systemdesign.distributedcache.loadbalancer.dto.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.util.*;

@Service @RequiredArgsConstructor public class CacheServiceImpl implements CacheService {

    @Value("#{${cacheNodes}}") private final Map<String, String> cacheNodes;

    private final CacheClient cacheClient;

    @Override public String getValue(String key, String node) {
        return cacheClient.get(cacheNodes.get(node), key);
    }

    @Override public String putValue(KeyValuePair keyValuePair, String node) {
        return null;
    }
}
