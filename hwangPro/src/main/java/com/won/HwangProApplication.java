package com.won;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.won.repository")
public class HwangProApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwangProApplication.class, args);
	}

}
