package com.systemdesign.distributedcache.loadbalancer.api;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CacheClient", url = "${cache.node.base_url}") public interface CacheClient {

    @GetMapping(value = ":{port}/get/{key}") String get(@PathVariable String port, @PathVariable String key);
}
