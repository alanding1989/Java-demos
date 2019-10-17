package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PITest {

	PI pi;

	@Before
	public void setUp() throws Exception {
		pi = new PI();
	}

	@Test(timeout = 500)
	public void test1k() {
		double r = pi.calculate(1000);
		assertEquals(3.14, r, 0.01);
	}

	@Test(timeout = 500)
	public void test1m() {
		double r = pi.calculate(1000000);
		assertEquals(3.1416, r, 0.0001);
	}

	@Test(timeout = 500)
	public void test100m() {
		double r = pi.calculate(100000000);
		assertEquals(3.14159, r, 0.00001);
	}

}
