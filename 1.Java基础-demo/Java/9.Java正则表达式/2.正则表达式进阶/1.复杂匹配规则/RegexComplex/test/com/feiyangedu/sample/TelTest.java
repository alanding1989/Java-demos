package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class TelTest {

	@Test
	public void testIsValidTel() {
		assertTrue(Tel.isValidTel("010-123456"));
		assertTrue(Tel.isValidTel("0123-12345678"));

		assertFalse(Tel.isValidTel("123-12345678"));
		assertFalse(Tel.isValidTel("123-0123456"));

		assertFalse(Tel.isValidTel("010-023456"));
		assertFalse(Tel.isValidTel("0123-01234567"));
	}

}
