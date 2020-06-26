package com.systemdesign.distributedcache.loadbalancer.dto;

import lombok.*;

@Data @AllArgsConstructor public class KeyValuePair {
    String key;
    String value;
}
