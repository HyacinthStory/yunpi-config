package com.hyacinth.yunpi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YunpiBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunpiBaseApplication.class, args);
	}
}
