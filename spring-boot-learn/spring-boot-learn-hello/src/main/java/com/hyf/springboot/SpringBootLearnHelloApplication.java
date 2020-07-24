package com.hyf.springboot;

import com.hyf.springboot.properties.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLearnHelloApplication
{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnHelloApplication.class, args);
//		System.exit(0);
    }

}
