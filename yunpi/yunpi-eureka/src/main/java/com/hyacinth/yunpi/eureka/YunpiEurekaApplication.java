package com.hyacinth.yunpi.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class YunpiEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(YunpiEurekaApplication.class)
				.web(true).run(args);
	}
}
