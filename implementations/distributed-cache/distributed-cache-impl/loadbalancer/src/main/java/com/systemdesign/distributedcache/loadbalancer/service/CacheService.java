package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.dto.*;

public interface CacheService {
    String getValue(String key, String node);

    String putValue(KeyValuePair keyValuePair, String node);
}
