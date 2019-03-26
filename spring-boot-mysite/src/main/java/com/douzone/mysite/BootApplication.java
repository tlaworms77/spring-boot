package com.douzone.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.MVCConfig;

// 자동으로 안되는 위치일 때
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.douzone.mysite.controller")

//@EnableAspectJAutoProxy
// 자기 이하 패키지는 자동 스캐닝됨
@SpringBootApplication
public class BootApplication {

	@Configuration
	@EnableAspectJAutoProxy
	@ComponentScan({ "com.douzone.mysite.controller", "com.douzone.mysite.exception" })
	@Import(value = { MVCConfig.class })
	public class WebConfig {

	}

	@Configuration
	@EnableAspectJAutoProxy
	@ComponentScan(value = { "com.douzone.mysite.service", "com.douzone.mysite.repository",
			"com.douzone.mysite.aspect" })
	public class AppConfig {

	}

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
