package com.leo.calculator.rent;

public enum RentType {

	FIXED("固定", CalculateType.FIXED), STEPUP("逓増", CalculateType.PERCENTAGE) {

	},
	STEPDOWN("逓減", CalculateType.PERCENTAGE) {

	},
	;

	private final String caption;
	private final CalculateType rangeCalculateType;

	private RentType(String caption, CalculateType rangeCalculateType) {
		this.caption = caption;
		this.rangeCalculateType = rangeCalculateType;
	}

	public Condition createCondition() {
		Condition condition = new Condition(this);
		condition.add(Condition.full(this.rangeCalculateType));
		return condition;
	}

	public String getCaption() {
		return caption;
	}

}
