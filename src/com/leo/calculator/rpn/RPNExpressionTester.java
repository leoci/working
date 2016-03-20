package com.leo.calculator.rpn;

import com.leo.calculator.VariableKey;

public final class RPNExpressionTester {

	/**
	 * 実際に解析して実行してみます。
	 * 
	 * @param expression
	 *            e.g. "( A1 + 3.3 ) * 3"
	 */
	public static <K extends Enum<K> & VariableKey> boolean test(String input,
			Class<K> keyType) {
		try {
			RPNExpression expression = RPNConverter.convert(input);
			RPNCalculator calculator = new RPNCalculator(expression);
			calculator.calculate(RPNUtil.createRandomVariables(keyType));
		} catch (Exception e) {
			// TODO divide into proper categories and throw new hogeExceptions
			return false;
		}
		return true;
	}

}
