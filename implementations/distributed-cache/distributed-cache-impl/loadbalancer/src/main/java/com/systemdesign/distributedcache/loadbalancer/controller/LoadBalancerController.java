package com.systemdesign.distributedcache.loadbalancer.controller;

import com.systemdesign.distributedcache.loadbalancer.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController public class LoadBalancerController {

    @GetMapping("/cache/{key}") KeyValuePair get(@PathVariable String key) {
        return new KeyValuePair(key, key);
    }

    @PutMapping("/cache/{key}") String put(@PathVariable String key, @RequestBody KeyValuePair keyValuePair) {
        return keyValuePair.getKey();
    }

}
