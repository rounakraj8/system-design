package com.systemdesign.distributedcache.loadbalancer.util;

import java.security.*;

public class MD5HashFunction extends HashFunction {

    private static MessageDigest MD5_INSTANCE;

    public MD5HashFunction() {
        initMd5();
    }

    @Override public long hash(String key) {
        MD5_INSTANCE.reset();
        MD5_INSTANCE.update(key.getBytes());
        byte[] digest = MD5_INSTANCE.digest();

        long h = 0;
        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((int) digest[i]) & 0xFF;
        }
        return h;
    }

    private void initMd5() {
        try {
            MD5_INSTANCE = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
