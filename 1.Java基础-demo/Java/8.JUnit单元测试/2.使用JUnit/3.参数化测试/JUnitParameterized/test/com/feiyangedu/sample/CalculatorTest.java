package com.feiyangedu.sample;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorTest {

	@Parameters
	public static Collection<?> data() {
		return Arrays
				.asList(new Object[][] { { "1+2", 3 }, { "1+2+5", 8 }, { "123+456", 579 }, { " 1 + 5 + 10 ", 16 } });
	}

	Calculator calc;

	@Parameter(0)
	public String input;

	@Parameter(1)
	public int expected;

	@Before
	public void setUp() {
		calc = new Calculator();
	}

	@Test
	public void testCalculate() {
		int r = calc.calculate(this.input);
		assertEquals(this.expected, r);
	}

}
