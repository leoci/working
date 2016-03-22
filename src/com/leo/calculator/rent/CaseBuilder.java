package com.leo.calculator.rent;

import java.math.BigDecimal;
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

		Cases addMinimumCase(Predicate<BigDecimal> when,
				UnaryOperator<BigDecimal> operator) {
			minimum = Optional.of(new CaseElement(when, operator));
			return this;
		}

		Cases addCase(Predicate<BigDecimal> when,
				UnaryOperator<BigDecimal> operator) {
			cases.add(new CaseElement(when, operator));
			return this;
		}

		public Cases always(UnaryOperator<BigDecimal> operator) {
			return addCase(ALWAYS, operator);
		}

		public CaseWhen when(Predicate<BigDecimal> when) {
			return new CaseWhen(this, when);
		}

		public Cases append(Cases other) {
			cases.addAll(other.getCases());
			return this;
		}

		public RentCalculator build() {
			return new RentCalculator(this);
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
			return new Cases().addMinimumCase(when, operator);
		}

	}

	private static final Predicate<BigDecimal> ALWAYS = b -> true;// Predicates.alwaysTrue();

	public static Cases always(UnaryOperator<BigDecimal> operator) {
		return new Cases().addCase(ALWAYS, operator);
	}

	public static CaseWhenMinimum minimumOf(Predicate<BigDecimal> when) {
		return new CaseWhenMinimum(when);
	}

	public static CaseWhen when(Predicate<BigDecimal> when) {
		return new CaseWhen(new Cases(), when);
	}

}
