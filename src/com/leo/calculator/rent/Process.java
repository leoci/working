package com.leo.calculator.rent;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Process {

	private final BigDecimal from;
	private final BigDecimal to;
	private final BigDecimal target;
	private final BigDecimal subtotal;
	private final BigDecimal fraction;

	private String memo;

}
