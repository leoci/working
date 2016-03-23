package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class CaseBuilder {

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	public static class CaseElement {
		private final Predicate<BigDecimal> when;
		private final Operator operator;
	}

	@Data
	public static class Cases {

		private Optional<CaseElement> minimumCase = Optional.empty();
		private final List<CaseElement> cases = new ArrayList<>();
		private Optional<CaseElement> resultCase = Optional.empty();

		private Cases addCaseMinimum(Predicate<BigDecimal> when,
				Operator operator) {
			minimumCase = Optional.of(new CaseElement(when, operator));
			return this;
		}

		private Cases addCase(Predicate<BigDecimal> when,
				Operator operator) {
			cases.add(new CaseElement(when, operator));
			return this;
		}

		private Cases addResultCase(Predicate<BigDecimal> when,
				Operator operator) {
			resultCase = Optional.of(new CaseElement(when, operator));
			return this;
		}

		public Cases always(Operator operator) {
			return addCase(ALWAYS, operator);
		}

		public CaseWhen when(Predicate<BigDecimal> when) {
			return new CaseWhen(this, when);
		}

		public CaseResultWhen whenResultIs(Predicate<BigDecimal> when) {
			return new CaseResultWhen(this, when);
		}

		public Cases append(Cases other) {
			// TODO 整合性
			cases.addAll(other.getCases());
			return this;
		}

		public RentCalculator build(RoundingMode roundingMode) {
			return new RentCalculator(this, roundingMode);
		}
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CaseWhen {

		private final Cases cases;

		private final Predicate<BigDecimal> when;

		public Cases then(Operator operator) {
			return cases.addCase(when, operator);
		}
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CaseWhenMinimum {

		private final Predicate<BigDecimal> when;

		public Cases then(Operator operator) {
			return new Cases().addCaseMinimum(when, operator);
		}
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CaseResultWhen {

		private final Cases cases;

		private final Predicate<BigDecimal> when;

		public Builder then(Operator operator) {
			cases.addResultCase(when, operator);
			return new Builder(cases);
		}
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Builder {

		private final Cases cases;

		public RentCalculator build(RoundingMode roundingMode) {
			return new RentCalculator(cases, roundingMode);
		}
	}

	private static final Predicate<BigDecimal> ALWAYS = b -> true;// Predicates.alwaysTrue();

	/**
	 * 基本賃料の設定
	 */
	public static Cases base(Operator operator) {
		return new Cases().addCase(ALWAYS, operator);
	}

	/**
	 * 売り上げにかかる最低保証
	 */
	public static CaseWhenMinimum minimumOf(Predicate<BigDecimal> when) {
		return new CaseWhenMinimum(when);
	}

	/**
	 * 基本 = baseと同じ動作
	 */
	public static Cases always(Operator operator) {
		return new Cases().addCase(ALWAYS, operator);
	}

	/**
	 * 分岐条件{@link PredicateBuilder}を利用して設定してください
	 */
	public static CaseWhen when(Predicate<BigDecimal> when) {
		return new CaseWhen(new Cases(), when);
	}

}
