package com.leo.calculator.rent;

import java.math.BigDecimal;

public class Range {

	private final BigDecimal from;
	private final BigDecimal to;
	private final CalculateType calculateType;

	public Range(BigDecimal from, BigDecimal to, CalculateType calculateType) {
		super();
		this.from = from;
		this.to = to;
		this.calculateType = calculateType;
	}

	public BigDecimal getFrom() {
		return from;
	}

	public BigDecimal getTo() {
		return to;
	}

	public CalculateType getCalculateType() {
		return calculateType;
	}

}
