package com.leo.calculator.rpn;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.leo.calculator.VariableKey;
import com.leo.calculator.Variables;

/**
 * Calculate formula in Reverse Polish Notation.<br>
 * Each token is split by blank space. <br>
 * e.g. A1 + A2 - 3 -> A1 A2 + 3 -
 */
public class RPNCalculator {
	
	@RequiredArgsConstructor
	@Getter
	enum Operator {
		ADDITIVE("+", (p1, p2) -> p1.add(p2)), 
		SUBTRACTION("-", (p1, p2) -> p1.subtract(p2)), 
		MULTIPLICATION("*", (p1, p2) -> p1.multiply(p2)), 
		DIVISION("/", (p1, p2) -> p1.divide(p2, Math.max(p1.scale(), p2.scale()))), 
		;

		private final String token;
		private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;


		BigDecimal apply(BigDecimal p1, BigDecimal p2) {
			return this.operation.apply(p1, p2);
		}

		static Operator tokenOf(String token) {
			return Arrays.stream(values()).filter(o -> o.token.equals(token)).findFirst().orElseThrow(IllegalArgumentException::new);
		}

		static boolean isOperator(String token) {
			return token.matches("[-+*/]");
		}
		
	}

	private final RPNExpression expression;

	public RPNCalculator(String expression) {
		this.expression = new RPNExpression(expression);
	}

	public <K extends Enum<K> & VariableKey> BigDecimal calculate(Variables<K> param) {
		Deque<BigDecimal> stack = new ArrayDeque<>();
		for (String token : expression.getTokens()) {
			if (Operator.isOperator(token)) {
				BigDecimal p2 = stack.pop(), p1 = stack.pop();
				stack.push(Operator.tokenOf(token).apply(p1, p2));
			} else {
				if (NumberUtils.isNumber(token)) {
					stack.push(new BigDecimal(token));
				} else {
					stack.push(param.get(token));
				}
			}
		}
		return stack.pop();
	}
}
