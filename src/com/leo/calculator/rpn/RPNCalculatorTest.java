package com.leo.calculator.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.leo.calculator.SampleKey;
import com.leo.calculator.Variables;

public class RPNCalculatorTest {

	@Test
	public void test() {

		Variables<SampleKey> variables = new Variables<>(SampleKey.class);
		variables.put(SampleKey.A1, BigDecimal.valueOf(1.1));
		variables.put(SampleKey.A2, BigDecimal.valueOf(1.1));
		variables.put(SampleKey.A3, BigDecimal.valueOf(1.1));
		variables.put(SampleKey.A4, BigDecimal.valueOf(100));
		variables.put(SampleKey.A5, BigDecimal.valueOf(2.5));

		RPNCalculator calculator;
		calculator = new RPNCalculator("A1 A2 + 3.3 +");
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(5.5)));
		calculator = new RPNCalculator("A1 1 -");
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(0.1)));
		calculator = new RPNCalculator("A1 A2 + 3 *");
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(6.6)));
		calculator = new RPNCalculator("A1 1.1 *");
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(1.21)));
		calculator = new RPNCalculator("A1 1.1 /");
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(1.0)));
		calculator = new RPNCalculator("A1 A2 * 1.1 /");
		assertThat(calculator.calculate(variables), is(new BigDecimal("1.10")));
		calculator = new RPNCalculator("A4 A5 /");
		assertThat(calculator.calculate(variables), is(new BigDecimal("40")));
	}
}
