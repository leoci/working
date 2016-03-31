package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBaseRule {

	Optional<BigDecimal> getMinimumOf();

	Optional<BigDecimal> getFixedBase();

	Optional<BigDecimal> getBaseRate();

	List<IRangeRule> getRangeRules();

	Optional<BigDecimal> getResultOf();

}
