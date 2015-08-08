package com.leo.sch;

import java.util.Arrays;
import java.util.function.Predicate;

public enum SchFacet {

	SCH(Column::isUseInSch), LIST(Column::isUseInList), EXP(Column::isUseInExp);

	final Predicate<Column> filter;

	private SchFacet(Predicate<Column> filter) {
		this.filter = filter;
	}

	public static Predicate<Column> mergeFilters(SchFacet... schFacets) {
		return Arrays.stream(schFacets).map(s -> s.filter)
				.reduce((p, f) -> p.or(f)).get();
	}

}
