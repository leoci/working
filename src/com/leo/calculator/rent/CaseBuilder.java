package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class CaseBuilder {

	@RequiredArgsConstructor
	@Getter
	public static class CaseElement {
		private final Predicate<BigDecimal> when;
		private final UnaryOperator<BigDecimal> operator;
	}
	
	@Data
	public static class Cases {

		private Optional<CaseElement> minimum = Optional.empty();
		private final List<CaseElement> cases = new ArrayList<>();
		private Optional<CaseElement> end = Optional.empty();

		private Cases addCaseMinimum(Predicate<BigDecimal> when,
				UnaryOperator<BigDecimal> operator) {
			minimum = Optional.of(new CaseElement(when, operator));
			return this;
		}

		private Cases addCase(Predicate<BigDecimal> when,
				UnaryOperator<BigDecimal> operator) {
			cases.add(new CaseElement(when, operator));
			return this;
		}
		
		private Cases addCaseEnd(Predicate<BigDecimal> when,
				UnaryOperator<BigDecimal> operator) {
			end = Optional.of(new CaseElement(when, operator));
			return this;
		}

		public Cases always(UnaryOperator<BigDecimal> operator) {
			return addCase(ALWAYS, operator);
		}

		public CaseWhen when(Predicate<BigDecimal> when) {
			return new CaseWhen(this, when);
		}
		
		public CaseWhenEnd end(Predicate<BigDecimal> when) {
			return new CaseWhenEnd(this, when);
		}

		public Cases append(Cases other) {
			cases.addAll(other.getCases());
			return this;
		}

		public RentCalculator build(RoundingMode roundingMode) {
			return new RentCalculator(this, roundingMode);
		}
	}

	@RequiredArgsConstructor
	public static class CaseWhen {

		private final Cases cases;

		private final Predicate<BigDecimal> when;

		public Cases then(UnaryOperator<BigDecimal> operator) {
			return cases.addCase(when, operator);
		}
	}

	@RequiredArgsConstructor
	public static class CaseWhenMinimum {

		private final Predicate<BigDecimal> when;

		public Cases then(UnaryOperator<BigDecimal> operator) {
			return new Cases().addCaseMinimum(when, operator);
		}
	}
	
	@RequiredArgsConstructor
	public static class CaseWhenEnd {

		private final Cases cases;
		
		private final Predicate<BigDecimal> when;

		public Cases then(UnaryOperator<BigDecimal> operator) {
			return cases.addCaseEnd(when, operator);
		}
	}

	private static final Predicate<BigDecimal> ALWAYS = b -> true;// Predicates.alwaysTrue();

	/**
	 * 基本賃料の設定
	 */
	public static Cases base(UnaryOperator<BigDecimal> operator) {
		return new Cases().addCase(ALWAYS, operator);
	}
	
	/**
	 * 最低保証関連
	 */
	public static CaseWhenMinimum minimumOf(Predicate<BigDecimal> when) {
		return new CaseWhenMinimum(when);
	}
	
	/**
	 * 基本 = baseと同じ動作
	 */
	public static Cases always(UnaryOperator<BigDecimal> operator) {
		return new Cases().addCase(ALWAYS, operator);
	}

	/**
	 * 分岐条件{@link PredicateBuilder}を利用して設定してください
	 */
	public static CaseWhen when(Predicate<BigDecimal> when) {
		return new CaseWhen(new Cases(), when);
	}

}
