package com.spring.boot.distributed;

import com.spring.boot.distributed.config.DBConfig1;
import com.spring.boot.distributed.config.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.spring.boot.distributed.config", "com.spring.boot.distributed.controller", "com.spring.boot.distributed.datasource", "com.spring.boot.distributed.test1", "com.spring.boot.distributed.test2"})
@EnableAutoConfiguration
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
@SpringBootApplication
public class DistributedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedApplication.class, args);
	}
}
