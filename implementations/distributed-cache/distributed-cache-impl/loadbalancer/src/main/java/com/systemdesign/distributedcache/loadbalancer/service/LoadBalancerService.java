package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.dto.*;

public interface LoadBalancerService {
    KeyValuePair get(String key);

    String put(KeyValuePair keyValuePair);
}
