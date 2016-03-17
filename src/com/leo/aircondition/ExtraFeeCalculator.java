package com.leo.aircondition;

import java.math.BigDecimal;

import com.leo.calculator.Variables;
import com.leo.calculator.rpn.RPNCalculator;

public class ExtraFeeCalculator {

	public BigDecimal calculate(long id) {
		// TODO load config & varibles
		return calculate("", new Variables<>(ExtraFeeParam.class));
	}

	public BigDecimal calculate(String formula, Variables<ExtraFeeParam> param) {
		// TODO SCALE & ROUNDINGMODE & 計算式が一意なら宣言は一回でいい
		RPNCalculator calculator = new RPNCalculator(formula);
		return calculator.calculate(param);
	}
}
