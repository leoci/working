package com.leo.calculator.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.leo.calculator.Variables;

public class RPNCalculatorTest {
	
    private Variables<RPNUtil.TestKey> variables;

    @Before
    public void prepare() {
        variables = new Variables<>(RPNUtil.TestKey.class);
        variables.put(RPNUtil.TestKey.A, BigDecimal.valueOf(1.1));
        variables.put(RPNUtil.TestKey.B, BigDecimal.valueOf(1.1));
        variables.put(RPNUtil.TestKey.C, BigDecimal.valueOf(1.1));
        variables.put(RPNUtil.TestKey.D, BigDecimal.valueOf(100));
        variables.put(RPNUtil.TestKey.E, BigDecimal.valueOf(2.5));
    }

    @Test
    public void test() {
        RPNCalculator calculator;
        calculator = new RPNCalculator("A B + 3.3 +");
        assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(5.5)));
        calculator = new RPNCalculator("A 1 -");
        assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(0.1)));
        calculator = new RPNCalculator("A B + 3 *");
        assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(6.6)));
        calculator = new RPNCalculator("A 1.1 *");
        assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(1.21)));
        calculator = new RPNCalculator("A 1.1 /");
        assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(1.0)));
        calculator = new RPNCalculator("A B * 1.1 /");
        assertThat(calculator.calculate(variables), is(new BigDecimal("1.10")));
        calculator = new RPNCalculator("D E /");
        assertThat(calculator.calculate(variables), is(new BigDecimal("40")));
    }
	
	@Test
	public void combinedTest() {
		RPNCalculator calculator;
		
		String expression = RPNConverter.convert("A + B + 3.3").getExpression();
		assertThat(expression, is("A B + 3.3 +"));
		
		calculator = new RPNCalculator(expression);
		assertThat(calculator.calculate(variables), is(BigDecimal.valueOf(5.5)));
	}
}
