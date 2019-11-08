package com.feiyangedu.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Pear");
		list.add("Orange");
		for (String s : list) {
			System.out.println(s);
		}


        // define array
        Integer[] li = {1, 2, 3, 4};

        // array to list
        List<Integer> arraylist = new ArrayList<>(Arrays.asList(1, 2));
        // List<Integer> arraylist = List.of(Arrays.asList(1, 2));
        Integer[] a = arraylist.toArray(new Integer[2]);
        // Integer[] a = arraylist.toArray(Integer[]::new);
	}

}
