package com.leo.calculator.rpn;

import java.math.BigDecimal;

public class NumberUtil {

	public static boolean isNumber(String val) {
		try {
			new BigDecimal(val);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
