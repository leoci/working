package com.leo.calculator.rpn;

import java.util.EnumSet;

/**
 * apachecommons入れて使って欲しい。
 */
@Deprecated
public class EnumUtils {

	static <E extends Enum<E>> boolean isValidEnum(Class<E> keyType,
			String value) {
		return EnumSet.allOf(keyType).contains(value);
	}

}
