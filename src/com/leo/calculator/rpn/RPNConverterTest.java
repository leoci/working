package com.leo.calculator.rpn;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class RPNConverterTest {

	@Test
	public void test() {
		assertThat(
				RPNConverter.canConvert("1+ 2 + 3 + 4", RPNUtil.TestKey.class),
				is(true));

		assertThat(RPNConverter.convert("1+ 2 + 3 + 4").getExpression(),
				is("1 2 + 3 + 4 +"));
		assertThat(RPNConverter.convert("1 - 2").getExpression(), is("1 2 -"));
		assertThat(RPNConverter.convert("( 1 + 2 ) * ( 3 + 4 )").getExpression(), is("1 2 + 3 4 + *"));
		assertThat(RPNConverter.convert("( 1 + 2 ) * 3 + 4").getExpression(), is("1 2 + 3 * 4 +"));
		assertThat(RPNConverter.convert("1 * 2 * 3 * 4").getExpression(), is("1 2 * 3 * 4 *"));
		assertThat(RPNConverter.convert("1 + 2 * 3 + 4").getExpression(), is("1 2 3 * + 4 +"));
		assertThat(RPNConverter.convert("1 * 2 + 3 * 4").getExpression(), is("1 2 * 3 4 * +"));
		assertThat(RPNConverter.convert("3 + 4 - 2 / ( 1 - 5 )").getExpression(), is("3 4 + 2 1 5 - / -"));
		assertThat(RPNConverter.convert("3 + 4 * 2 / ( 1 - 5 )").getExpression(), is("3 4 2 * 1 5 - / +"));
	}

	@Test
	public void combinedTest() {
		String expression = RPNConverter.convert("1 + 2 + 3 + 4").getExpression();
		RPNCalculator calculator = new RPNCalculator(expression);
		assertThat(calculator.calculate(null), is(BigDecimal.valueOf(10)));
	}

}
