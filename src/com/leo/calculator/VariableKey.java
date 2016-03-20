package com.leo.calculator;

import java.math.BigDecimal;

public interface VariableKey {

	default BigDecimal getSampleValue() {
		return BigDecimal.ONE;
	}

}
