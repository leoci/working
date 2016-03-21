package com.leo.calculator.rent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class RentCalculatorTest {

	private static final Object[][] FIXED = { { 0L, 999_999_999_999L, 300_000L }
	};

	private enum Case {
		FIXED;
	}

	@Test
	public void test() {
		RentCalculator calculator = new RentCalculator();
		assertThat(calculator.calculate(BigDecimal.valueOf(0), ), is(0));
	}

}
