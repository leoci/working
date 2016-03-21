package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class Result {

	private final BigDecimal source;
	private final Condition condition;
	private List<Process> processList;
	private BigDecimal total;

	public void add(Process process) {
		processList.add(process);
		total = total.add(process.getSubtotal());
	}

}
