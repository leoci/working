package com.leo.calculator.rpn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

import com.leo.calculator.VariableKey;
import com.leo.calculator.Variables;

/**
 * Calculate formula in Reverse Polish Notation.<br>
 * Each token is split by blank space. <br>
 * e.g. A1 + A2 -> A1 A2 +
 */
public class RPNCalculator {

	enum Operator {
		ADDITIVE("+", (p1, p2) -> p1.add(p2)), SUBTRACTION("-", (p1, p2) -> p1
				.subtract(p2)), MULTIPLICATION("*", (p1, p2) -> p1.multiply(p2)), DIVISION(
				"/", (p1, p2) -> p1
						.divide(p2, Math.max(p1.scale(), p2.scale()))), ;

		private final String token;
		private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;

		private Operator(String token,
				BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) {
			this.token = token;
			this.operation = operation;
		}

		BigDecimal apply(BigDecimal p1, BigDecimal p2) {
			return this.operation.apply(p1, p2);
		}

		static Operator tokenOf(String token) {
			return Arrays.stream(values()).filter(o -> o.token.equals(token))
					.findFirst().orElseThrow(IllegalArgumentException::new);
		}

		static boolean isOperator(String token) {
			return token.matches("[-+*/]");
		}
	};

	public <K extends Enum<K> & VariableKey> BigDecimal calculate(
			String expression, Variables<K> param) {
		StringTokenizer tokenizer = new StringTokenizer(expression, " ");

		Stack<BigDecimal> stack = new Stack<>();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (Operator.isOperator(token)) {
				BigDecimal p2 = stack.pop(), p1 = stack.pop();
				stack.push(Operator.tokenOf(token).apply(p1, p2));
			} else {
				if (NumberUtil.isNumber(token)) {
					stack.push(new BigDecimal(token));
				} else {
					stack.push(param.get(token));
				}
			}
		}
		return stack.pop();
	}
}
