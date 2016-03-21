package com.leo.calculator.rent;

import java.math.BigDecimal;

public class RentCalculator {

	public BigDecimal calculate(long id) {
		// TODO load config & varibles
		return calculate(BigDecimal.valueOf(2_200_000), null).getTotal();
	}

	public Result calculate(BigDecimal source, Condition condition) {
		Result result = new Result(source, condition);
		// TODO SCALE & ROUNDINGMODE
		BigDecimal previousUpperThreshold = BigDecimal.ZERO;
		for (Range range : condition) {
			BigDecimal rangeResult = range.getCalculateMethod().apply();
			Process process = new Process(from, to, target, subtotal, fraction);
		}
		return result;
	}

}
