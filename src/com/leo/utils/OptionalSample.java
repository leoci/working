package com.leo.utils;

import java.util.Optional;

public class OptionalSample {
	
	public static void main(String[] args) {
		
		Optional<Sample> s1 = Optional.ofNullable(null);
		Optional<Sample> s2 = Optional.ofNullable(new Sample("default"));
		
	}
	
	private static class Sample {
		
		@SampleAnnotation(sheet = EnumSample.A)
		private final String caption;
		
		public Sample(String caption) {
			this.caption = caption;
		}
		
		public String getCaption() {
			return caption;
		}
	}

}
