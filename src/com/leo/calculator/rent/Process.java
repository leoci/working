package com.leo.calculator.rent;

import java.math.BigDecimal;

public class Process {

	private final Range range;
	private final BigDecimal target;
	private final BigDecimal subtotal;

	public Process(Range range, BigDecimal target, BigDecimal subtotal) {
		this.range = range;
		this.target = target;
		this.subtotal = subtotal;
	}

	public Range getRange() {
		return range;
	}

	public BigDecimal getTarget() {
		return target;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

}
