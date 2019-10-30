package com.melo.wellington.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.melo.wellington.application.util.Util;

@SpringBootApplication
@EnableEurekaClient
public class MyCarsApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(MyCarsApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//
//			String senhaApp = "06oGg#KFaBr14TN7@eZLBySst$KuUDxm";
//			String senhaUser = "tw060686";
//			
//			System.out.println("PASSWORD-APP	-	" + Util.generateBCrypt(senhaApp));
//			System.out.println("PASSWORD-USER	-	" + Util.generateBCrypt(senhaUser));
//
//		};
//	}

}
