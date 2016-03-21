package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.Predicate;

import lombok.Data;

public final class RangeBuilder {

	@Data
	private static class RangeElement {

		private final Predicate<BigDecimal> condition;

	}

}
