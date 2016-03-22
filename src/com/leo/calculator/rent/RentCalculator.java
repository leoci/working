package com.leo.calculator.rent;

import java.math.BigDecimal;

import com.leo.calculator.rent.CaseBuilder.Cases;

public class RentCalculator {

	private final Cases cases;

	public RentCalculator(Cases cases) {
		this.cases = cases;
	}

	public BigDecimal calculate(BigDecimal source) {
		if (cases.getMinimum().filter(c -> c.getWhen().test(source)).isPresent()) {
			return cases.getMinimum().get().getOperator().apply(source);
		}
		return cases.getCases().stream()
				.filter(c -> c.getWhen().test(source))
				.map(c -> c.getOperator().apply(source))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
