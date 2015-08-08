package com.leo.sch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColumnUtils {

	public static List<Column> getColumns(List<Column> allColumns,
			SchFacet... schFacets) {
		return allColumns.stream().filter(SchFacet.mergeFilters(schFacets))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		List<Column> allColumns = Stream.generate(Column::new).limit(10)
				.collect(Collectors.toList());
		System.out.println(allColumns);
		List<Column> columns = getColumns(allColumns, SchFacet.SCH,
				SchFacet.LIST);
		System.out.println(columns);
	}

}
