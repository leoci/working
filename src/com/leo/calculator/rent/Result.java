package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.List;

public class Result {

	private final BigDecimal source;
	private final Condition condition;
	private List<Process> processList;
	private BigDecimal total;

	public Result(BigDecimal source, Condition condition) {
		this.source = source;
		this.condition = condition;
	}

	public void add(Process process) {
		processList.add(process);
		total = total.add(process.getSubtotal());
	}

	public BigDecimal getSource() {
		return source;
	}

	public Condition getCondition() {
		return condition;
	}

	public List<Process> getProcessList() {
		return processList;
	}

	public BigDecimal getTotal() {
		return total;
	}

}
