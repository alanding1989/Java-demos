package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZeroCountTest {

	@Test
	public void testZeros() {
		assertEquals(0, ZeroCount.zeros("123456"));
		assertEquals(1, ZeroCount.zeros("123450"));
		assertEquals(2, ZeroCount.zeros("123400"));
		assertEquals(3, ZeroCount.zeros("123000"));
		assertEquals(4, ZeroCount.zeros("120000"));
		assertEquals(2, ZeroCount.zeros("100100"));
	}

}
