package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CalculateMethod {

	FIXED((t, f) -> f.getFixed()), PERCENTAGE((t, f) -> t.multiply(f.getRate()));

	private final BiFunction<BigDecimal, Factor, BigDecimal> calculation;

	public BigDecimal apply(BigDecimal target, Factor factor) {
		return this.calculation.apply(target, factor);
	}

}
