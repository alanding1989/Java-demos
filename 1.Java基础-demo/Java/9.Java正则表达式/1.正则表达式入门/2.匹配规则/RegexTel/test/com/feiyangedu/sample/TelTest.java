package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class TelTest {

	@Test
	public void testIsValidTel() {
		assertTrue(Tel.isValidTel("010-123456"));
		assertTrue(Tel.isValidTel("010-12345678"));
		assertTrue(Tel.isValidTel("0123-123456"));
		assertTrue(Tel.isValidTel("0123-12345678"));
		assertTrue(Tel.isValidTel("123-12345678"));
		assertTrue(Tel.isValidTel("123-0123456"));

		assertFalse(Tel.isValidTel("010#12345678"));
		assertFalse(Tel.isValidTel("010X12345678"));
		assertFalse(Tel.isValidTel("01-12345678"));
		assertFalse(Tel.isValidTel("01234-12345678"));
		assertFalse(Tel.isValidTel("01A-12345678"));
		assertFalse(Tel.isValidTel("0123-12345"));
		assertFalse(Tel.isValidTel("0123-12345X"));
		assertFalse(Tel.isValidTel("0123-123456789"));
		assertFalse(Tel.isValidTel("0123-12345678X"));
	}

}
