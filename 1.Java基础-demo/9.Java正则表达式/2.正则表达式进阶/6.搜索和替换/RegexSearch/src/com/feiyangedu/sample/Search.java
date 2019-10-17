package com.feiyangedu.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

	public static void main(String[] args) {
		String s = "The quick brown fox jumps over the lazy dog.";
		Pattern p = Pattern.compile("the", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		while (m.find()) {
			String sub = s.substring(m.start(), m.end());
			System.out.println(sub + ", start=" + m.start() + ", end=" + m.end());
		}
	}

}
