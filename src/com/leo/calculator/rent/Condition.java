package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Condition extends ArrayList<Range> {

	private static final long serialVersionUID = 1L;

	public static final BigDecimal MAX = BigDecimal.valueOf(999_999_999_999L);

	public static final Condition EMPTY_CONDITION = new Condition(RentType.FIXED);
	public static Condition emptyCondition() {
		return EMPTY_CONDITION;
	}

	private final RentType rentType;


}
