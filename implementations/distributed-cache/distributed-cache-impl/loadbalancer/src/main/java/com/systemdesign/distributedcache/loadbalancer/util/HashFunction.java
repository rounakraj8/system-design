package com.systemdesign.distributedcache.loadbalancer.util;

public class HashFunction {

    public long hash(String object) {
        return object.hashCode();
    }
}
