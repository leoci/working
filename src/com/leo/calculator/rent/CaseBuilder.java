package com.leo.calculator.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import lombok.Data;

public final class CaseBuilder<A> {

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

	}

	public static class CaseWhen<A> {

		private final Predicate<A> when;

		private final Cases<A> cases;

		public CaseWhen(Cases<A> cases, Predicate<A> when) {
			this.cases = cases;
			this.when = when;
		}

	}

	public static class Initial<A> {

		private final Predicate<A> when;

		public Initial(Predicate<A> when) {
			this.when = when;
		}

		private Cases<A> then() {
			return new Cases<A>() {

			}.addCase(when);
		}

	}

	public Initial<A> when(Predicate<A> when) {
		return new Initial<>(when);
	}
}
