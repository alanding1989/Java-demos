package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class QQTest {

	@Test
	public void testIsValidQQ() {
		assertTrue(QQ.isValidQQ("10000"));
		assertTrue(QQ.isValidQQ("99999"));
		assertTrue(QQ.isValidQQ("1234567890"));
		assertTrue(QQ.isValidQQ("9999999999"));

		assertFalse(QQ.isValidQQ("00001"));
		assertFalse(QQ.isValidQQ("099999"));
	}

}
