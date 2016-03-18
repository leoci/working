package com.leo.calculator.rpn;

import java.math.BigDecimal;

/**
 * apachecommonsNumberUtils入れて使って欲しい。
 */
@Deprecated
public class NumberUtils {

	public static boolean isNumber(String val) {
		try {
			new BigDecimal(val);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
