package com.douzone.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 자동으로 안되는 위치일 때
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.douzone.mysite.controller")

//@EnableAspectJAutoProxy
// 자기 이하 패키지는 자동 스캐닝됨
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
