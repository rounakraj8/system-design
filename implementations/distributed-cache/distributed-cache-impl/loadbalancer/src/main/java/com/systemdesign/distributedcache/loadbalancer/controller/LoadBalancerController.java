package com.systemdesign.distributedcache.loadbalancer.controller;

import com.systemdesign.distributedcache.loadbalancer.dto.*;
import com.systemdesign.distributedcache.loadbalancer.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor public class LoadBalancerController {

    private final LoadBalancerService loadBalancerService;

    @GetMapping("/cache/{key}") KeyValuePair getCache(@PathVariable String key) {
        return loadBalancerService.get(key);
    }

    @PutMapping("/cache") String put(@RequestBody KeyValuePair keyValuePair) {
        return loadBalancerService.put(keyValuePair);
    }

}
