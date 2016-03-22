package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RangeCalculateMethod {

	FULL((s, r) -> s), 
	RANGED((s, r) -> s.min(r.getTo()).subtract(r.getFrom())),
	FIX((s, r) -> s),
	;

	private final BiFunction<BigDecimal, Range, BigDecimal> operator;

	public BigDecimal apply(BigDecimal source, Range range) {
		return this.operator.apply(source, range);
	}

}
