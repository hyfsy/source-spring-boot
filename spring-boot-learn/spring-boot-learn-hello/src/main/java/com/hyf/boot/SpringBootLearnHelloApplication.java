package com.hyf.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLearnHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnHelloApplication.class, args);
//		System.exit(0);
    }

    // @GetMapping("hello")
	// public void hello() {
	// 	System.out.println("hello");
	// }

}
