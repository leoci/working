package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.Predicate;

public final class PredicateBuilder {

	private PredicateBuilder() {
	}

	public static Predicate<BigDecimal> at(long threshold) {
		return 	at(BigDecimal.valueOf(threshold));
	}

	public static Predicate<BigDecimal> at(BigDecimal threshold) {
		return 	s -> s.compareTo(threshold) >= 0;
	}

	public static Predicate<BigDecimal> between(long from, long to) {
		return 	between(BigDecimal.valueOf(from), BigDecimal.valueOf(to));
	}

	public static Predicate<BigDecimal> between(BigDecimal from, BigDecimal to) {
		return 	s -> NumberUtil.isInRange(s, from, to);
	}

}
