package com.hyf.springboot;

import com.hyf.springboot.properties.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringBootLearnHelloApplication
{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnHelloApplication.class, args);
//		System.exit(0);
    }

    // @GetMapping("hello")
	// public void hello() {
	// 	System.out.println("hello");
	// }

}
