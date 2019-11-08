package com.feiyangedu.sample;

public class QQ {

	public static boolean isValidQQ(String s) {
		return s.matches("^\\d{5,10}$");
	}

}
