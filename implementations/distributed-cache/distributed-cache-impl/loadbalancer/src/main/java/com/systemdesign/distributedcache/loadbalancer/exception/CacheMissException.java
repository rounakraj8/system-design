package com.systemdesign.distributedcache.loadbalancer.exception;

public class CacheMissException extends RuntimeException {
    private static final String MSG = "Cache Miss!!";

    public CacheMissException() {
        super(MSG);
    }
}
