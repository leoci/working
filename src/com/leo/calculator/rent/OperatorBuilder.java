package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

public final class OperatorBuilder {

	private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(100);
	private static final int DEFAULT_SCALE = 2;
	private static final BigDecimal MAX = BigDecimal.valueOf(Double.MAX_VALUE);
	
	private OperatorBuilder() {
	}

	public static UnaryOperator<BigDecimal> fixed(long fixed) {
		return fixed(BigDecimal.valueOf(fixed));
	}

	public static UnaryOperator<BigDecimal> fixed(BigDecimal fixed) {
		return (s) -> fixed;
	}
	
	public static UnaryOperator<BigDecimal> fixedRate(long fixed, double rate) {
		return fixedRate(BigDecimal.valueOf(fixed), BigDecimal.valueOf(rate));
	}

	public static UnaryOperator<BigDecimal> fixedRate(BigDecimal fixed, BigDecimal rate) {
		return (s) -> fixed.multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN);
	}

	public static UnaryOperator<BigDecimal> rate(double rate) {
		return rate(BigDecimal.valueOf(rate));
	}

	public static UnaryOperator<BigDecimal> rate(BigDecimal rate) {
		return (s) -> s.multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN);
	}

	public static UnaryOperator<BigDecimal> rangeRate(double from, double rate) {
		return rangeRate(from, Double.MAX_VALUE, rate);
	}
	
	public static UnaryOperator<BigDecimal> rangeRate(double from, double to, double rate) {
		return rangeRate(BigDecimal.valueOf(from), BigDecimal.valueOf(to), BigDecimal.valueOf(rate));
	}

	public static UnaryOperator<BigDecimal> rangeRate(BigDecimal from, BigDecimal rate) {
		return rangeRate(from, MAX, rate);
	}
	
	public static UnaryOperator<BigDecimal> rangeRate(BigDecimal from, BigDecimal to, BigDecimal rate) {
		return (s) -> s.min(to).subtract(from).multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN);
	}
	
	public static UnaryOperator<BigDecimal> rangeRate(double rate) {
		return rangeRate(BigDecimal.valueOf(rate));
	}

	public static UnaryOperator<BigDecimal> rangeRate(BigDecimal rate) {
		return (s) -> s.multiply(rate);
	}

}
