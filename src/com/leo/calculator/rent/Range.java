package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import lombok.Data;

@Data
public class Range {

	private final Predicate<BigDecimal> when;
	private final BigDecimal from;
	private final BigDecimal to;
	private final BiFunction<BigDecimal, BigDecimal, Process> operation;
	private final BigDecimal factor;

}
