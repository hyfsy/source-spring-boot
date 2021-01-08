package com.hyf.boot.actuator.endpoint;

import java.util.Date;

import org.springframework.boot.actuate.endpoint.annotation.EndpointConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author baB_hyf
 * @date 2020/10/23
 */
@Component
@EndpointConverter
public class MyDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		System.out.println("source date: " + source);
		return new Date();
	}
}
