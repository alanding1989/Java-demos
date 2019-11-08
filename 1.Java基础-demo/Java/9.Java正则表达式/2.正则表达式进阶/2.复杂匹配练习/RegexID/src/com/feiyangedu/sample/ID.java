package com.feiyangedu.sample;

public class ID {

	/**
	 * TODO: 验证身份证号是否是合法的身份证号
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidId(String s) {
		return s.matches("^\\d{18}$");
	}

}
