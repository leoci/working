package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.Function;

@FunctionalInterface
public interface Operator extends Function<BigDecimal, Process> {

	default Operator note(String note) {
		return b -> {
			Process p = apply(b);
			p.setNote(note);
			return p;
		};
	}

}
