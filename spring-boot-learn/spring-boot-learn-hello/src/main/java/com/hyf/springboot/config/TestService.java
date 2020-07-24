package com.hyf.springboot.config;

import com.hyf.springboot.properties.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author baB_hyf
 * @date 2020/07/11
 */
@Service
public class TestService {
	@Autowired
	private HelloProperties helloProperties;

	public void hello() {
		System.out.println(helloProperties);
	}
}
