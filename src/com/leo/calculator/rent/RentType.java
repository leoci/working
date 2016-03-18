package com.leo.calculator.rent;

public enum RentType {
	
//	FIXED("固定")
//	PERCENTAGE("完全歩合")
//	FIXED_PERCENTAGE("固定＋完全歩合")
//	PERCENTAGE_FIXED_GUARANTEE("完全歩合＋固定保証")
//	FIXED_PERCENTAGE_FIXED_GUARANTEE("固定＋完全歩合＋固定保証")
//	STEPDOWN("売上逓減")
//	STEPDOWN_FIXED_GUARANTEE("売上逓減＋固定保証")
//	STEPDOWN_GUARANTEED_MINIMUM("売上逓減＋最低保証")
//	FIXED_STEPDOWN("固定＋売上逓減")
//	FIXED_STEPDOWN_FIXED_GUARANTEE("固定＋売上逓減＋固定保証")
//	FIXED_STEPDOWN_GUARANTEED_MINIMUM("固定＋売上逓減＋最低保証")
//	RATIO_STEPDOWN("売上比例(歩率逓減)")
//	FIXED_RATIO_STEPDOWN("固定＋売上比例(歩率逓減)")
//	RATIO_STEPUP("売上比例(歩率逓増)")
//	FIXED_RATIO_STEPUP("固定＋売上比例(歩率逓増)")
//	STEPUP("売上逓増")
//	STEPUP_FIXED_GUARANTEE("売上逓増＋固定保証")
//	STEPUP_GUARANTEED_MINIMUM("売上逓増＋最低保証")
//	FIXED_STEPUP("固定＋売上逓増")
//	FIXED_STEPUP_FIXED_GUARANTEE("固定＋売上逓増＋固定保証")
//	FIXED_STEPUP_GUARANTEED_MINIMUM("固定＋売上逓増＋最低保証")

	FIXED("固定", CalculationMethod.FIXED), 
	STEPUP("逓増", CalculationMethod.PERCENTAGE) {

	},
	STEPDOWN("逓減", CalculationMethod.PERCENTAGE) {

	},
	;

	private final String caption;
	private final CalculationMethod rangeCalculateType;

	private RentType(String caption, CalculationMethod rangeCalculateType) {
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
