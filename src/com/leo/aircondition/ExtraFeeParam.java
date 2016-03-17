package com.leo.aircondition;

import com.leo.calculator.VariableKey;

public enum ExtraFeeParam implements VariableKey {

	AREA_SQM("空調面積"), EXTRA_HOURS("延長利用時間(時間)");

	private final String caption;

	private ExtraFeeParam(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

}
