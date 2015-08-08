package com.leo.sch;

import java.util.Random;

public class Column {

	private String id;
	private String field;
	private boolean useInSch;
	private boolean useInList;
	private boolean useInExp;

	private static Random randomaizer = new Random();

	public Column() {
		id = Integer.toString(randomaizer.nextInt());
		field = id;
		useInSch = randomaizer.nextBoolean();
		useInList = randomaizer.nextBoolean();
		useInExp = randomaizer.nextBoolean();
	}

	public Column(String id, String field, boolean useInSch, boolean useInList,
			boolean useInExp) {
		super();
		this.id = id;
		this.field = field;
		this.useInSch = useInSch;
		this.useInList = useInList;
		this.useInExp = useInExp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isUseInSch() {
		return useInSch;
	}

	public void setUseInSch(boolean useInSch) {
		this.useInSch = useInSch;
	}

	public boolean isUseInList() {
		return useInList;
	}

	public void setUseInList(boolean useInList) {
		this.useInList = useInList;
	}

	public boolean isUseInExp() {
		return useInExp;
	}

	public void setUseInExp(boolean useInExp) {
		this.useInExp = useInExp;
	}

	@Override
	public String toString() {
		return String.format("%s %s %b %b %b", id, field, useInSch, useInList,
				useInExp);
	}

}
