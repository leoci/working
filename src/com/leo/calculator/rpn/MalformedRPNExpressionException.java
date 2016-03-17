package com.leo.calculator.rpn;

public class MalformedRPNExpressionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String expression;

	public MalformedRPNExpressionException(String expression) {
		super("invalid expression for RPN calculation :" + expression);
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

}
