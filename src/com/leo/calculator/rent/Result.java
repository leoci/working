package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class Result {

	private final BigDecimal value;
	private final List<Process> processes;

}
