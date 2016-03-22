package com.leo.calculator.rpn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.leo.calculator.VariableKey;
import com.leo.calculator.Variables;

public final class RPNConverter {

	@RequiredArgsConstructor
	@Getter
	enum Sign {
		ADDITIVE("+", Priority.LOW), 
		SUBTRACTION("-", Priority.LOW), 
		MULTIPLICATION("*", Priority.HIGH), 
		DIVISION("/", Priority.HIGH), 
		BRACKET_LEFT("(", Priority.NONE), 
		BRACKET_RIGHT(")", Priority.NONE),
		;

		private final String token;
		private final Priority priority;

		static Sign tokenOf(String token) {
			return Arrays.stream(values()).filter(o -> o.token.equals(token))
					.findFirst().orElseThrow(IllegalArgumentException::new);
		}

		static boolean isOperator(String token) {
			return token.matches("[-+*/]");
		}

		static boolean isBracket(String token) {
			return token.matches("[()]");
		}

		boolean isPriorTo(Sign other) {
			return priority.compareTo(other.priority) > 0;
		}
	}

	enum Priority {
		NONE, LOW, HIGH;
	}

	/**
	 * 通常の数式の整合性を確認します<br>
	 * 変数はkeyTypeに指定された文字列のみ許容します。<br>
	 * i.e. 大文字英数とアンダースコアのみ許容。空白は無視
	 * 
	 * @param input
	 *            数式 e.g. ( A + B ) * 3
	 * @param keyType
	 *            利用する{@link Variables}のenumクラスを指定
	 */
	public static <K extends Enum<K> & VariableKey> boolean canConvert(String input, Class<K> keyType) {
		return Arrays.stream(split(input)).filter(t -> !Sign.isOperator(t))
				.filter(t -> !Sign.isBracket(t))
				.allMatch(t -> EnumUtils.isValidEnum(keyType, t));
	}

	private static String[] split(String input) {
		return input.replaceAll("[ ]", "").split("(?<=[-+*/()])|(?=[-+*/()])");
	}

	/**
	 * 通常の式を逆ポーランド記法にコンバートします。<br>
	 * {@link #canConvert(String, Class)}でチェックした上での利用を推奨します。<br>
	 * e.g. ( A + B ) * 3 -> ["(","A","+","B",")","*","3"] -> A B + 3 *
	 */
	public static RPNExpression convert(String input) {
		return convert(split(input));
	}

	/**
	 * 通常の式（文字列の配列）を逆ポーランド記法にコンバートします。<br>
	 * 基本的には文字列での利用{@link #convert(String)}を推奨します。<br>
	 * e.g. ["(","A","+","B",")","*","3"] -> A B + 3 *
	 */
	public static RPNExpression convert(String[] input) {
		List<String> rpn = new ArrayList<>();
		Deque<Sign> oStack = new ArrayDeque<>();
		for (String token : input) {
			if (Sign.isOperator(token)) {
				Sign operator = Sign.tokenOf(token);
				if (oStack.isEmpty()) {
					oStack.push(operator);
					continue;
				}
				Sign o1 = operator, o2 = oStack.peek();
				while (!oStack.isEmpty() && !o1.isPriorTo(o2)) {
					rpn.add(oStack.pop().getToken());
					o2 = oStack.peek();
				}
				oStack.push(operator);
			} else if (Sign.isBracket(token)) {
				Sign bracket = Sign.tokenOf(token);
				if (bracket == Sign.BRACKET_LEFT) {
					oStack.push(bracket);
				} else {
					while (oStack.peek() != Sign.BRACKET_LEFT) {
						rpn.add(oStack.pop().getToken());
					}
					oStack.remove();
				}
			} else {
				rpn.add(token);
			}
		}
		while (!oStack.isEmpty()) {
			rpn.add(oStack.pop().getToken());
		}
		String expression = String.join(RPNExpression.DELIM, rpn);
		return new RPNExpression(expression);
	}

}
