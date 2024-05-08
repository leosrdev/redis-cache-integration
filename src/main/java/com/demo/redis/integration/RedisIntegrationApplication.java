package com.demo.redis.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class RedisIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisIntegrationApplication.class, args);
	}

}
