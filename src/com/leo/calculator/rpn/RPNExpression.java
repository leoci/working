package com.leo.calculator.rpn;

import lombok.Getter;

@Getter
public class RPNExpression {

	public static final String DELIM = " ";
	private final String expression;
	private final String[] tokens;

	/**
	 * @param expression
	 *            in RPN w/ variables (requires delimiter as " ")<br>
	 *            e.g. "A 3.3 +"
	 */
	public RPNExpression(String expression) {
		checkFormat(expression);
		this.expression = expression;
		this.tokens = expression.split(DELIM);
	}

	private void checkFormat(String expression) {
		// TODO formatチェック
		// throw new MalformedRPNExpressionException(expression);
	}

}
