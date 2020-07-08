/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.simple;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.springframework.boot.ansi.*;
import org.springframework.boot.context.properties.bind.Binder;
import sample.simple.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleSimpleApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) {
		System.out.println(this.helloWorldService.getHelloMessage());
		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSimpleApplication.class, args);

//		seeMXBean();

//		prettyPrint();

//		testRun(args);

	}

	public static void testRun(String[] args) {
		SpringApplication springApplication = new SpringApplication(SampleSimpleApplication.class);
		// 可在此处进行应用的配置
		// springApplication.setXxx;
		springApplication.run(args);
	}

	public static void seeMXBean() {
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * font {@link AnsiElement}: https://en.wikipedia.org/wiki/ANSI_escape_code
	 * <p>
	 * color {@link AnsiColors}: http://en.wikipedia.org/wiki/Color_difference#CIE94
	 */
	public static void prettyPrint() {
		StringBuilder output = new StringBuilder();
		output
				.append("\033[33mtest first out\n").append("test second out\n")
				.append(AnsiOutput.encode(AnsiColor.BRIGHT_RED)).append("test third out\n")
				// 背景颜色是反的 ???
				.append(AnsiOutput.encode(AnsiBackground.BRIGHT_WHITE))
				.append(AnsiOutput.encode(AnsiColor.BRIGHT_CYAN))
				.append(AnsiOutput.encode(AnsiStyle.UNDERLINE))
				.append(AnsiOutput.encode(AnsiStyle.FAINT))
				.append("test forth out\n");
		System.out.println(output);
	}

	public void testBinder() {
//		Binder.get(environment).bind("spring.output.ansi.enabled", AnsiOutput.Enabled.class)
//				.ifBound(AnsiOutput::setEnabled);
	}

}
