package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testCalcAdd2Numbers() {
		Calculator calc = new Calculator();
		int r = calc.calculate("1+2");
		assertEquals(3, r);
	}

	@Test
	public void testCalcAdd3Numbers() {
		Calculator calc = new Calculator();
		int r = calc.calculate("1+2+5");
		assertEquals(8, r);
	}

	@Test
	public void testCalcAddLargeNumbers() {
		Calculator calc = new Calculator();
		int r = calc.calculate("123+456");
		assertEquals(579, r);
	}

	@Test
	public void testCalcWithWhiteSpaces() {
		Calculator calc = new Calculator();
		int r = calc.calculate("1 + 5 + 10 ");
		assertEquals(16, r);
	}

}
