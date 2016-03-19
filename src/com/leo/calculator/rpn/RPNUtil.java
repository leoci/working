package com.leo.calculator.rpn;

import java.util.Arrays;

import com.leo.calculator.VariableKey;
import com.leo.calculator.Variables;

public final class RPNUtil {

	private RPNUtil() {
	}

	enum TestKey implements VariableKey {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
	}

	public static <K extends Enum<K> & VariableKey> Variables<K> createRandomVariables(
			Class<K> keyType) {
		Variables<K> varibales = new Variables<K>(keyType);
		Arrays.stream(keyType.getEnumConstants()).forEach(
				k -> varibales.put(k, k.getSampleValue()));
		return varibales;
	}

}
