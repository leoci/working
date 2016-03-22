package com.leo.calculator.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.leo.calculator.rpn.RPNUtil.TestKey;

public class RPNExpressionTesterTest {

	@Test
	public void test() {
		assertThat(RPNExpressionTester.test("1 + 2 + 3 + 4", RPNUtil.TestKey.class), is(true));
		assertThat(RPNExpressionTester.test("( a + B ) * 3 + 4", RPNUtil.TestKey.class), is(false));
		assertThat(RPNExpressionTester.test("(A + B )* 3 + 4", RPNUtil.TestKey.class), is(true));
	}

}
