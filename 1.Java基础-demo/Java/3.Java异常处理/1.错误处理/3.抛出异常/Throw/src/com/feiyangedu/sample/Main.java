package com.feiyangedu.sample;

public class Main {

	public static void main(String[] args) {
		process1();
	}

	static void process1() {
		try {
			process2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("END");
		}
	}

	static void process2() {
		process3();
	}

	static void process3() {
		Integer.parseInt(null);
	}
}
