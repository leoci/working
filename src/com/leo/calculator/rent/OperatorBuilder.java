package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class OperatorBuilder {

	private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(100);
	private static final int DEFAULT_SCALE = 2;
	private static final BigDecimal MAX = BigDecimal.valueOf(Long.MAX_VALUE);

	private OperatorBuilder() {
	}

	public static Operator fixed(long fixed) {
		return fixed(BigDecimal.valueOf(fixed));
	}

	public static Operator fixed(BigDecimal fixed) {
		return (s) -> new Process(ProcessDiv.FIXED, s, fixed);
	}

	public static Operator fixedRate(long fixed, double rate) {
		return fixedRate(BigDecimal.valueOf(fixed), BigDecimal.valueOf(rate));
	}

	public static Operator fixedRate(BigDecimal fixed, BigDecimal rate) {
		return (s) -> new Process(ProcessDiv.RATE, fixed, rate, fixed.multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN));
	}

	public static Operator rate(double rate) {
		return rate(BigDecimal.valueOf(rate));
	}

	public static Operator rate(BigDecimal rate) {
		return (s) -> new Process(ProcessDiv.RATE, s, rate, s.multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN));
	}

	public static Operator rangeRateTo(double to, double rate) {
		return rangeRate(0d, to, rate);
	}

	public static Operator rangeRateTo(BigDecimal to, BigDecimal rate) {
		return rangeRate(BigDecimal.ZERO, to, rate);
	}

	public static Operator rangeRateFrom(double from, double rate) {
		return rangeRate(from, Double.MAX_VALUE, rate);
	}

	public static Operator rangeRateFrom(BigDecimal from, BigDecimal rate) {
		return rangeRate(from, MAX, rate);
	}

	public static Operator rangeRate(double from, double to, double rate) {
		return rangeRate(BigDecimal.valueOf(from), BigDecimal.valueOf(to), BigDecimal.valueOf(rate));
	}

	public static Operator rangeRate(BigDecimal from, BigDecimal to, BigDecimal rate) {
		return (s) -> {
			BigDecimal target = s.min(to).subtract(from);
			return new Process(ProcessDiv.RANGE_RATE, from, s.min(to), target, rate, 
					target.multiply(rate).divide(PERCENTAGE, DEFAULT_SCALE, RoundingMode.DOWN));
		};
	}

}
