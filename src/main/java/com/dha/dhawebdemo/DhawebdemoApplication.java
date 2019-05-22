package com.dha.dhawebdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


//开启fifter servlet  interceptor
@ServletComponentScan
//开启异步注解
@EnableAsync
@SpringBootApplication
public class DhawebdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DhawebdemoApplication.class, args);
	}

}
