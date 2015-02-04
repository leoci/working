package com.leo.codeiq;

import java.util.stream.IntStream;

public class Steps30Neo {

	public static void main(String[] args) {
		long millis = System.currentTimeMillis();

		PatternCounter pc = new PatternCounter();
		int answer = pc.count(30);

		System.out.println(answer);
		System.out.println(System.currentTimeMillis() - millis);
	}

	static class PatternCounter {

		private static final int MIN_HOP = 1;
		private static final int MAX_HOP = 3;

		private int CACHE_THRESHOLD = 10;
		private int[] cachedCount;

		public PatternCounter() {
			cachedCount = IntStream.rangeClosed(1, CACHE_THRESHOLD)
					.map(this::count).toArray();
		}

		public int count(int steps) {
			return step(steps, 0);
		}

		private int step(int rest, int count) {
			if (rest == 0) {
				return ++count;
			} else if (cachedCount != null && rest <= CACHE_THRESHOLD) {
				return count + cachedCount[rest - 1];
			}
			for (int i = MIN_HOP; i <= Math.min(MAX_HOP, rest); i++) {
				count = step(rest - i, count);
			}
			return count;
		}
	}

}
