package com.feiyangedu.sample;

public class Replace {

	public static void main(String[] args) {
		String s = "The   quick brown   fox  jumps    over the lazy dog.";
		String r = s.replaceAll("\\s+", " ");
		System.out.println(r);
	}

}
