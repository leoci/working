package com.leo.calculator.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RPNCalculatorTest {

	@Test
	public void test() {
		RPNCalculator calculator = new RPNCalculator();

		Map<String, BigDecimal> param = new HashMap<>();
		param.put("A1", BigDecimal.valueOf(1.1));
		param.put("A2", BigDecimal.valueOf(1.1));
		param.put("A3", BigDecimal.valueOf(1.1));
		param.put("A4", BigDecimal.valueOf(1.1));
		param.put("A5", BigDecimal.valueOf(1.1));

		assertThat(calculator.calculate("A1 A2 + 3.3 +", param),
				is(BigDecimal.valueOf(5.5)));
		assertThat(calculator.calculate("A1 A2 + 3 *", param),
				is(BigDecimal.valueOf(6.6)));
	}

}
