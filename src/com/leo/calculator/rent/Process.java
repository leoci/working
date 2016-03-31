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
	private BigDecimal rawValue;
	private BigDecimal value;
	private BigDecimal fraction = BigDecimal.ZERO;

	private final ProcessDiv processDiv;
	private String note;

	public Process(ProcessDiv processDiv, BigDecimal target, BigDecimal value) {
		this.processDiv = processDiv;
		this.target = target;
		this.rawValue = value;
		this.value = value;
	}

	public Process(ProcessDiv processDiv, BigDecimal target, BigDecimal rate, 
			BigDecimal value) {
		this.processDiv = processDiv;
		this.target = target;
		this.rate = Optional.ofNullable(rate);
		this.rawValue = value;
		this.value = value;
	}

	public Process(ProcessDiv processDiv, BigDecimal from, BigDecimal to, BigDecimal target,
			BigDecimal rate, BigDecimal value) {
		this.processDiv = processDiv;
		this.from = Optional.ofNullable(from);
		this.to = Optional.ofNullable(to);
		this.target = target;
		this.rate = Optional.ofNullable(rate);
		this.rawValue = value;
		this.value = value;
	}

}
