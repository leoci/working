package com.leo.codeiq;

public class Steps30 {

	public static void main(String[] args) {
		long millis = System.currentTimeMillis();

		PatternCounter pc = new PatternCounter();
		long answer = pc.count(30);

		System.out.println(answer);
		System.out.println(System.currentTimeMillis() - millis);
	}

	static class PatternCounter {

		private static final int MIN_HOP = 1;
		private static final int MAX_HOP = 3;

		public long count(int steps) {
			return step(steps, 0);
		}

		private long step(int rest, long count) {
			if (rest == 0) {
				return ++count;
			}
			for (int i = MIN_HOP; i <= Math.min(MAX_HOP, rest); i++) {
				count = step(rest - i, count);
			}
			return count;
		}
	}

}
