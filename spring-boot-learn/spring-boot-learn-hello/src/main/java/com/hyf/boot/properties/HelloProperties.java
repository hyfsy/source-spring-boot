package com.hyf.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baB_hyf
 * @date 2020/07/11
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
