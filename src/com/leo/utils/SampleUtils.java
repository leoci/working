package com.leo.utils;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SampleUtils {

	public static void main(String[] args) {
		
		System.out.println(Locale.getDefault(Locale.Category.FORMAT));
		
		List<String> s = Arrays.asList("a",null,"b");
		
		s.stream().sorted(Comparator.comparing(String::toString, Comparator.nullsFirst(Comparator.naturalOrder()))).forEach(System.out::println);
		
	}
	
	static class Sample{ 
		
		String code;
		
		public Sample(String code) {
			this.code = code;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	}

}
