package com.systemdesign.distributedcache.loadbalancer.api;

import com.systemdesign.distributedcache.loadbalancer.util.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component @RequiredArgsConstructor public class CacheClientRestTemplate {

    private final RestTemplateUtil restTemplate;

    public String get(String url) {
        return restTemplate.getObjectFromAPI(url, String.class);
    }

}
