package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import lombok.Data;

public final class CaseBuilder<A> {
	
	public static final Predicate<BigDecimal> ALWAYS = new Predicate() {
		public boolean test(A t) {
			return true;
		};
	};
	
	@Data
	private static class CaseElement<A> {

		private final Predicate<A> when;

	}

	public abstract static class Cases<A> {

		private final List<CaseElement<A>> cases = new ArrayList<>();

		Cases<A> addCase(Predicate<A> when) {
			cases.add(0, new CaseElement<A>(when));
			return this;
		}
		
		public abstract Cases<A> setSource(A source);
		
		public abstract A getResult();
		
	}

	public static class CaseWhen<A> {

		private final Predicate<A> when;

		private final Cases<A> cases;

		public CaseWhen(Cases<A> cases, Predicate<A> when) {
			this.cases = cases;
			this.when = when;
		}

	}

	public static class Initial<A> extends Cases<A> {

		private final Predicate<A> when;

		public Initial(Predicate<A> when) {
			this.when = when;
		}

		Cases<A> then(A val) {
			return new Cases<A>() {

			}.addCase(when);
		}
		
	}

	public static <A> Initial<A> when(Predicate<A> when) {
		return new Initial<>(when);
	}
	
}
