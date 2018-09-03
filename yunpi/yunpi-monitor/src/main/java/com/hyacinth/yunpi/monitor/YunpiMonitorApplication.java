package com.hyacinth.yunpi.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class YunpiMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunpiMonitorApplication.class, args);
	}

    @Value("${spring.profiles.active}")
    String profile;
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${jdbc.url}")
    String className;

    @RestController
    public class AdminTestController {
        @GetMapping("hello")
        public String sayHello() {
            log.info("profile={}",profile);
            log.info("applicationName={}",applicationName);
            log.info("className={}",className);
            return "hello...";
        }
    }
}
