package com.leo.calculator.rent;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Range {

	private final BigDecimal from;
	private final BigDecimal to;
	private final CalculateMethod calculateMethod;
	private final Factor factor;

}
