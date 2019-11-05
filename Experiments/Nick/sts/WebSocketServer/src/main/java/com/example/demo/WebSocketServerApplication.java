package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Chat application using websockets. 
 * 
 * @author Nickolas Mitchell
 */
@SpringBootApplication
//@ComponentScan(basePackages="WebSocket") We may need this in our project. 
public class WebSocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServerApplication.class, args);
	}
}
