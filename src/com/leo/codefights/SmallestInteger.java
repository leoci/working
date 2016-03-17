package com.leo.codefights;

import java.util.Arrays;

public class SmallestInteger {
	static int smallestInteger(int[][] matrix) {
		for (int[] a : matrix)
			Arrays.sort(a);
		int i = 0;
		while (true) {
			boolean r = false;
			for (int[] a : matrix)
				r = Arrays.binarySearch(a, i) >= 0 || r;
			if (!r)
				return i;
			i++;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 0, 2 }, { 5, 1 } };
		System.out.println(smallestInteger(matrix));
	}
}
