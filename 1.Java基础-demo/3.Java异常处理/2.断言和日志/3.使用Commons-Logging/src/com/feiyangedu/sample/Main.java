package com.feiyangedu.sample;

public class Main {

	public static void main(String[] args) {
		Person p = new Person("Xiao Ming");
		System.out.println(p.hello());
		try {
			new Person(null);
		} catch (Exception e) {
		}
	}

}
