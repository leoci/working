package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;

@Getter
public class Factor {

	private final BigDecimal fixed;
	private final BigDecimal rate;

	private final int scale;
	private final RoundingMode roundingMode;

	public Factor(BigDecimal fixed) {
		this.fixed = fixed;

		this.rate = BigDecimal.ZERO;
		this.scale = 0;
		this.roundingMode = RoundingMode.DOWN;
	}

	public Factor(BigDecimal rate, int scale, RoundingMode roundingMode) {
		this.rate = rate;
		this.scale = scale;
		this.roundingMode = roundingMode;

		this.fixed = BigDecimal.ZERO;
	}
}
