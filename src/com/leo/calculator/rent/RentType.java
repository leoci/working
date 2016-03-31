package com.leo.calculator.rent;

import static com.leo.calculator.rent.OperatorBuilder.fixed;
import static com.leo.calculator.rent.OperatorBuilder.rangeRate;
import static com.leo.calculator.rent.OperatorBuilder.rate;
import static com.leo.calculator.rent.PredicateBuilder.at;

import java.math.RoundingMode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.leo.calculator.rent.CaseBuilder.Cases;

@RequiredArgsConstructor
@Getter
public enum RentType {

	FIXED("固定"),
	PERCENTAGE("完全歩合"),
	FIXED_PERCENTAGE("固定＋完全歩合"),
	PERCENTAGE_FIXED_GUARANTEE("完全歩合＋固定保証"),
	FIXED_STEPDOWN("固定＋売上逓減"),
	;

	private final String caption;

	// TODO 空気読むのではなく、ちゃんとルールにそぐうものでbuildしたい。
	public RentCalculator build(IBaseRule rule, RoundingMode roundingMode) {
		Cases cases = null;
		if (rule.getMinimumOf().isPresent()) {
			// TODO
		} else {
			cases = CaseBuilder.empty();
		}

		if (rule.getFixedBase().isPresent()) {
			cases.always(fixed(rule.getFixedBase().get()));
		} else if (rule.getBaseRate().isPresent()) {
			cases.always(rate(rule.getBaseRate().get()));
		}

		cases.append(buildRange(rule));

		if (rule.getResultOf().isPresent()) {
			// TODO
		}

		return cases.build(roundingMode);
	}

	private Cases buildRange(IBaseRule rule) {
		Cases cases = CaseBuilder.empty();
		rule.getRangeRules().forEach(r -> {
			cases.when(at(r.getFrom())).then(rangeRate(r.getFrom(), r.getTo(), r.getRate()));
		});
		return cases;
	}

}
