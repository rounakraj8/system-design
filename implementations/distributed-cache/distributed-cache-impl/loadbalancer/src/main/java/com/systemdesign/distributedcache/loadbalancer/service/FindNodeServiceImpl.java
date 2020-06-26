package com.systemdesign.distributedcache.loadbalancer.service;

import com.systemdesign.distributedcache.loadbalancer.util.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Log4j2 @Service public class FindNodeServiceImpl implements FindNodeService {

    private static final int NO_OF_REPLICAS = 1;
    private static final MD5HashFunction hashFunction = new MD5HashFunction();
    private static ConsistentHash<String> stringConsistentHash;

    public FindNodeServiceImpl() {
        List<String> nodes = Arrays.asList("Node9000", "Node9010", "Node9020", "Node9030");
        stringConsistentHash = new ConsistentHash<>(hashFunction, NO_OF_REPLICAS, nodes);
    }

    @Override public String getNode(String key) {
        String node = stringConsistentHash.get(key);
        log.info("{} ==> {}", key, node);
        return node;
    }
}
