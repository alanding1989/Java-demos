package com.feiyangedu.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {
		// 从classpath读取配置文件:
		try (InputStream input = Main.class.getResourceAsStream("/conf.properties")) {
			if (input != null) {
				System.out.println("Read /conf.properties...");
				Properties props = new Properties();
				props.load(input);
				System.out.println("name=" + props.getProperty("name"));
			}
		}
		// 从classpath读取txt文件:
		String data = "/com/feiyangedu/sample/data.txt";
		try (InputStream input = Main.class.getResourceAsStream(data)) {
			if (input != null) {
				System.out.println("Read " + data + "...");
				BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				System.out.println(reader.readLine());
			} else {
				System.out.println("Resource not found: " + data);
			}
		}
	}

}
