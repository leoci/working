package com.leo.codefights;

public class FindPath {
	public static void main(String[] args) {
		int[][] a = { { 2, 3, 4, 5 }, { 1, 8, 7, 6 }, { 12, 9, 10, 11 } };
		System.out.println(findPath(a));
	}

	static boolean findPath(int[][] matrix) {
		int cx = -1, cy = -1;
		for (int i = 1; i <= matrix.length * matrix[0].length; i++) {
			for (int y = 0; y < matrix.length; y++) {
				for (int x = 0; x < matrix[y].length; x++) {
					if (i == matrix[y][x]) {
						if (cx != -1 && Math.abs(x + y - cx - cy) != 1) {
							return false;
						}
						cx = x;
						cy = y;
					}
				}
			}
		}
		return true;
	}

}
