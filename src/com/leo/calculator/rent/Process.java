package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class Process {

	private Optional<BigDecimal> from = Optional.empty();
	private Optional<BigDecimal> to = Optional.empty();
	private final BigDecimal target;
	private Optional<BigDecimal> rate = Optional.empty();
	private BigDecimal value;
	private BigDecimal fraction = BigDecimal.ZERO;

	private String note;

	public Process(BigDecimal target, BigDecimal value) {
		this.target = target;
		this.value = value;
	}

	public Process(BigDecimal target, BigDecimal rate, BigDecimal value) {
		this.target = target;
		this.rate = Optional.ofNullable(rate);
		this.value = value;
	}

	public Process(BigDecimal from, BigDecimal to, BigDecimal target,
			BigDecimal rate, BigDecimal value) {
		this.from = Optional.ofNullable(from);
		this.to = Optional.ofNullable(to);
		this.target = target;
		this.rate = Optional.ofNullable(rate);
		this.value = value;
	}

}
