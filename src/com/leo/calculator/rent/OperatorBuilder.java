package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public final class OperatorBuilder {

	private OperatorBuilder() {
	}

	public static UnaryOperator<BigDecimal> fixed(long fixed) {
		return fixed(BigDecimal.valueOf(fixed));
	}

	public static UnaryOperator<BigDecimal> fixed(BigDecimal fixed) {
		return (s) -> fixed;
	}

	public static UnaryOperator<BigDecimal> rate(double rate) {
		return rate(BigDecimal.valueOf(rate));
	}

	public static UnaryOperator<BigDecimal> rate(BigDecimal rate) {
		return (s) -> s.multiply(rate);
	}

}
