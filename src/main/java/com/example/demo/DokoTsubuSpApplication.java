package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.test.Test;

@SpringBootApplication
public class DokoTsubuSpApplication {

	@Autowired
	Test test;
	
	public static void main(String[] args) {
		boolean debug = false;
		if(debug == false) {
			SpringApplication.run(DokoTsubuSpApplication.class, args);
		}else {
			SpringApplication.run(DokoTsubuSpApplication.class, args)
			.getBean(DokoTsubuSpApplication.class)
			.execute();
			
		}
	}
	
	public void execute() {
		test.execute();
	}
}
