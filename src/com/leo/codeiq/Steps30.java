package com.leo.codeiq;

public class Steps30 {

	public static void main(String[] args) {
		PatternCounter pc = new PatternCounter();
		int answer = pc.count(30);
		System.out.println(answer);
	}

	static class PatternCounter {

		private static final int MAX_HOP = 3;

		public int count(int steps) {
			return step(steps, 0);
		}

		private int step(int rest, int count) {
			for (int i = 1; i <= Math.min(MAX_HOP, rest); i++) {
				count = step(rest - i, count);
			}
			if (rest == 0) {
				count++;
			}
			return count;
		}
	}

}
