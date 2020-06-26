package com.systemdesign.distributedcache.loadbalancer.api;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CacheClient", url = "${cache.node.base}") public interface CacheClient {

    @GetMapping( value = "get/{key}", produces = "text/plain") String get(
            @PathVariable String port, @PathVariable String key);
}
