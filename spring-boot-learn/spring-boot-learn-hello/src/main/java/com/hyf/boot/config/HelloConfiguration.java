package com.hyf.boot.config;

import com.hyf.boot.properties.HelloProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author baB_hyf
 * @date 2020/07/11
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloConfiguration implements ApplicationContextAware {

	@Autowired
	private HelloProperties helloProperties;

	@Autowired
	private TestService testService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		HelloProperties bean = applicationContext.getBean(HelloProperties.class);
		System.out.println(bean);
		testService.hello();
	}
}
