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

package org.springframework.boot.ansi;

import org.springframework.util.Assert;

import java.util.Locale;

/**
 * Generates ANSI encoded output, automatically attempting to detect if the terminal
 * supports ANSI.
 *
 * @author Phillip Webb
 */
public abstract class AnsiOutput {

	private static final String ENCODE_JOIN = ";";

	/**
	 * 1. 首要判断
	 */
	private static Enabled enabled = Enabled.DETECT;

	/**
	 * 2. 其次判断
	 */
	private static Boolean consoleAvailable;

	/**
	 * 缓存判断结果
	 */
	private static Boolean ansiCapable;

	/**
	 * 当前操作系统名称，window默认控制台不输出颜色
	 */
	private static final String OPERATING_SYSTEM_NAME = System.getProperty("os.name")
			.toLowerCase(Locale.ENGLISH);

	/**
	 * 颜色开头
	 */
	private static final String ENCODE_START = "\033[";

	/**
	 * 颜色结尾
	 */
	private static final String ENCODE_END = "m";

	/**
	 * 重置颜色的字符
	 */
	private static final String RESET = "0;" + AnsiColor.DEFAULT;

	/**
	 * Sets if ANSI output is enabled.
	 * @param enabled if ANSI is enabled, disabled or detected
	 */
	public static void setEnabled(Enabled enabled) {
		Assert.notNull(enabled, "Enabled must not be null");
		AnsiOutput.enabled = enabled;
	}

	/**
	 * Sets if the System.console() is known to be available.
	 * @param consoleAvailable if the console is known to be available or {@code null} to
	 * use standard detection logic.
	 */
	public static void setConsoleAvailable(Boolean consoleAvailable) {
		AnsiOutput.consoleAvailable = consoleAvailable;
	}

	static Enabled getEnabled() {
		return AnsiOutput.enabled;
	}

	/**
	 * Encode a single {@link AnsiElement} if output is enabled.
	 * @param element the element to encode
	 * @return the encoded element or an empty string
	 */
	public static String encode(AnsiElement element) {
		if (isEnabled()) {
			// 隐式调用了 element.toString() 方法
			return ENCODE_START + element + ENCODE_END;
		}
		return "";
	}

	/**
	 * Create a new ANSI string from the specified elements. Any {@link AnsiElement}s will
	 * be encoded as required.
	 * @param elements the elements to encode
	 * @return a string of the encoded elements
	 */
	public static String toString(Object... elements) {
		StringBuilder sb = new StringBuilder();
		// 判断是否输出彩色文字
		if (isEnabled()) {
			buildEnabled(sb, elements);
		}
		else {
			buildDisabled(sb, elements);
		}
		return sb.toString();
	}

	private static void buildEnabled(StringBuilder sb, Object[] elements) {
		boolean writingAnsi = false;
		boolean containsEncoding = false;
		for (Object element : elements) {
			// 颜色字符指定开头
			if (element instanceof AnsiElement) {
				containsEncoding = true;
				if (!writingAnsi) {
					sb.append(ENCODE_START);
					writingAnsi = true;
				}
				else {
					sb.append(ENCODE_JOIN);
				}
			}
			// 颜色字符指定结尾
			else {
				if (writingAnsi) {
					sb.append(ENCODE_END);
					writingAnsi = false;
				}
			}
			// 添加中间字符
			sb.append(element);
		}

		// 添加 重置颜色 字符
		if (containsEncoding) {
			sb.append(writingAnsi ? ENCODE_JOIN : ENCODE_START); // 开头
			sb.append(RESET); // 之间
			sb.append(ENCODE_END); // 结尾
		}
	}

	/**
	 * console添加颜色相关字符
	 */
	private static void buildDisabled(StringBuilder sb, Object[] elements) {
		for (Object element : elements) {
			// 不是AnsiElement对象才进行日志的字符添加
			if (!(element instanceof AnsiElement) && element != null) {
				sb.append(element);
			}
		}
	}

	/**
	 * 通过此方法判断是否需要颜色输出
	 */
	private static boolean isEnabled() {
		// 扫描模式
		if (enabled == Enabled.DETECT) {
			if (ansiCapable == null) {
				ansiCapable = detectIfAnsiCapable();
			}
			return ansiCapable;
		}
		// always则直接启用
		return enabled == Enabled.ALWAYS;
	}

	private static boolean detectIfAnsiCapable() {

		/*
		 * 只要是window系统就禁用
		 * 其他系统指定为true就启用
		 * 其他系统指定为null则判断System.console() 该方法不在IDE中才不会返回null （不是ide启动的项目就启用）
		 */

		try {
			if (Boolean.FALSE.equals(consoleAvailable)) {
				return false;
			}
			if ((consoleAvailable == null) && (System.console() == null)) {
				return false;
			}
			return !(OPERATING_SYSTEM_NAME.contains("win"));
		}
		catch (Throwable ex) {
			return false;
		}
	}

	/**
	 * Possible values to pass to {@link AnsiOutput#setEnabled}. Determines when to output
	 * ANSI escape sequences for coloring application output.
	 */
	public enum Enabled {

		/**
         * 自动探测，根据是否支持 ANSI 的功能，来判断是否要彩色输出
         *
         * 【默认值】
         *
		 * Try to detect whether ANSI coloring capabilities are available. The default
		 * value for {@link AnsiOutput}.
		 */
		DETECT,

		/**
         * 总是开启 ANSI 彩色输出
         *
		 * Enable ANSI-colored output.
		 */
		ALWAYS,

		/**
         * 禁用 ANSI 彩色输出
         *
		 * Disable ANSI-colored output.
		 */
		NEVER

	}

}
