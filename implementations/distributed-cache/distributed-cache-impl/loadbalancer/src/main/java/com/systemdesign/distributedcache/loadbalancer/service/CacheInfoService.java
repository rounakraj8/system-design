package com.systemdesign.distributedcache.loadbalancer.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.util.*;

@Service @RequiredArgsConstructor public class CacheInfoService {

    @Value("#{${cacheNodes}}") private final Map<String, String> cacheNodes;

    public Map<String, String> getCacheNodes() {
        return cacheNodes;
    }

    public String getCacheNodeValue(String key) {
        return cacheNodes.get(key);
    }

}
