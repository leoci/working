package com.leo.calculator;

import java.math.BigDecimal;
import java.util.EnumMap;

public class Variables<K extends Enum<K> & VariableKey> extends
		EnumMap<K, BigDecimal> {

	private static final long serialVersionUID = 1L;
	private final Class<K> keyType;

	public Variables(Class<K> keyType) {
		super(keyType);
		this.keyType = keyType;
	}

	public void put(K key, double val) {
		super.put(key, BigDecimal.valueOf(val));
	}

	public void put(K key, int val) {
		super.put(key, BigDecimal.valueOf(val));
	}

	public void put(K key, long val) {
		super.put(key, BigDecimal.valueOf(val));
	}

	public BigDecimal get(String key) {
		return super.get(Enum.valueOf(keyType, key));
	}
}
