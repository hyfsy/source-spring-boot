package com.hyf.boot.actuator.health;

import java.util.Set;

import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.Include;

/**
 * @author baB_hyf
 * @date 2020/10/22
 */
public class MyHttpExchangeTracer extends HttpExchangeTracer {
	/**
	 * Creates a new {@code HttpExchangeTracer} that will use the given {@code includes} to determine the contents of its traces.
	 *
	 * @param includes the includes
	 */
	public MyHttpExchangeTracer(Set<Include> includes) {
		super(includes);
	}
}
