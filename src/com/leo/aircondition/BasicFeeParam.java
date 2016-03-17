package com.leo.aircondition;

import com.leo.calculator.VariableKey;

public enum BasicFeeParam implements VariableKey {

	AREA_SQM("空調面積"), HOURS("稼働時間(時間)");

	private final String caption;

	private BasicFeeParam(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

}
