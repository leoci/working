package com.leo.calculator.rpn;

import lombok.Getter;

@Getter
public class RPNExpression {
	
	public static final String DELIM = " ";
	private final String expression;
	private final String[] tokens;
	
	public RPNExpression(String expression) {
		checkFormat(expression);
		this.expression = expression;
		this.tokens = expression.split(DELIM);
	}
	
	private void checkFormat(String expression) {
		//TODO formatチェック（めんどいな・・・）
		//throw new MalformedRPNExpressionException(expression);
	}
	
}
