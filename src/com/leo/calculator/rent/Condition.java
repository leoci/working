package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Condition extends ArrayList<Range> {

	private static final long serialVersionUID = 1L;

	public static final BigDecimal MAX = BigDecimal.valueOf(999_999_999_999L);

	private final RentType rentType;

	public Condition(RentType rentType) {
		this.rentType = rentType;
	}

	public static Range full(CalculateType calculateType) {
		return new Range(BigDecimal.ZERO, MAX, calculateType);
	}

	public RentType getRentType() {
		return rentType;
	}

}
