package com.hyf.boot.config;

import com.hyf.boot.properties.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
