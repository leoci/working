package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.leo.calculator.rent.CaseBuilder.Cases;

public class RentCalculator {

	private final Cases cases;
	private final RoundingMode roundingMode;
	
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.DOWN;

	public RentCalculator(Cases cases) {
		this.cases = cases;
		this.roundingMode = DEFAULT_ROUNDING_MODE;
	}
	
	public RentCalculator(Cases cases, RoundingMode roundingMode) {
		this.cases = cases;
		this.roundingMode = roundingMode;
	}


	public BigDecimal calculate(long source) {
		return calculate(BigDecimal.valueOf(source));
	}
	
	public BigDecimal calculate(BigDecimal source) {
		BigDecimal res = BigDecimal.ZERO;
		if (cases.getMinimum().filter(c -> c.getWhen().test(source)).isPresent()) {
			res = cases.getMinimum().get().getOperator().apply(source);
		} else {
			res = cases.getCases().stream()
					.filter(c -> c.getWhen().test(source))
					.map(c -> c.getOperator().apply(source))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return end(res).setScale(0, this.roundingMode);
	}
	
	private BigDecimal end(BigDecimal res) {
		if (cases.getEnd().filter(c -> c.getWhen().test(res)).isPresent()) {
			return cases.getEnd().get().getOperator().apply(res);
		}
		return res;
	}
	
}
