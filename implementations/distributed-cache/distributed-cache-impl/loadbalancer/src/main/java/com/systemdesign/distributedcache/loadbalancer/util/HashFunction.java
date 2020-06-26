package com.systemdesign.distributedcache.loadbalancer.util;

public interface HashFunction {

    default int hash(Object object) {
        return object.hashCode();
    }
}
