package com.leo.codefights;

public class IntArray {
	public static void main(String[] args) {
		int[] a = { 1, 3, 10, 3, 1 };

		System.out.println(find(a));

	}

	static int find(int[] sequence) {
		int answer = 0, precount = 0;
		for (int i = 1; i <= 10; i++) {
			int count = 0;
			for (int t : sequence) {
				if (t == i) {
					count++;
				}
			}
			System.out.println(count + " " + i + " " + precount);
			if (count > precount) {
				answer = i;
				precount = count;
			}
		}
		return answer;
	}

}
