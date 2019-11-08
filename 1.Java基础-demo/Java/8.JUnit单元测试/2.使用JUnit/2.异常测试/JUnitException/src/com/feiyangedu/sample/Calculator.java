package com.feiyangedu.sample;

public class Calculator {

	public int calculate(String expression) {
		String[] ss = expression.split("\\+");
		int sum = 0;
		for (String s : ss) {
			sum = sum + Integer.parseInt(s.trim());
		}
		return sum;
	}

}
