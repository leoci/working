package com.leo.codeiq;

public class HAL {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("LZH SN STJHZTMNMZQZ JNMN ATMRGNT VN OTQNFTQZLT CD JZHCNJT RHMZRZH ");
		sb.append("RNQDJTQZH CDJHMZJXZ VZSZRH GZ ETSZQH FZ STJHZT MNVN LHSNLDMZHXN");

		HALDecriptor d = new HALDecriptor();
		System.out.println(d.decript(sb.toString()));
	}

	static class HALDecriptor {

		static char MAX_CHAR = 'Z';
		static char MIN_CHAR = 'A';
		static char SPACE = ' ';

		String decript(String str) {
			char[] base = str.toCharArray();
			for (int i = 0; i < base.length; i++) {
				char c = base[i];
				if (c == SPACE) {
					continue;
				}
				if (c == MAX_CHAR) {
					base[i] = MIN_CHAR;
				} else {
					base[i] = (char) (c + 1);
				}
			}
			return new String(base);
		}

	}

}
