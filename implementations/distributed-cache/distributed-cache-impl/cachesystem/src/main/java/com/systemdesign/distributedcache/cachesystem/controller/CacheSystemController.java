package com.systemdesign.distributedcache.cachesystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController public class CacheSystemController {

    @GetMapping(value = "get/{key}") String get(@PathVariable String key) {
        return key + "found";
    }
}
