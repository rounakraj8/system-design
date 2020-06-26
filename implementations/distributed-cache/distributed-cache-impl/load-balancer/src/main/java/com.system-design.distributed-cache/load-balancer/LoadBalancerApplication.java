package com.system-design.distributed-cache.load-balancer;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class LoadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerApplication.class, args);
	}

}