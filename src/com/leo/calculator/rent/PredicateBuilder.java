package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.util.function.Predicate;

public final class PredicateBuilder {

	private PredicateBuilder() {
	}

	public static Predicate<BigDecimal> upto(long threshold) {
		return 	upto(BigDecimal.valueOf(threshold));
	}

	public static Predicate<BigDecimal> upto(BigDecimal threshold) {
		return s -> s.compareTo(threshold) <= 0;
	}

	public static Predicate<BigDecimal> at(long threshold) {
		return 	at(BigDecimal.valueOf(threshold));
	}

	public static Predicate<BigDecimal> at(BigDecimal threshold) {
		return 	s -> s.compareTo(threshold) > 0;
	}

	public static Predicate<BigDecimal> between(long from, long to) {
		return 	between(BigDecimal.valueOf(from), BigDecimal.valueOf(to));
	}

	/**
	 * 賃料計算では○○○円を超過で・・・が一般的なので、upto/atを使うことを推奨。<br>
	 * レンジごとの固定などになる場合には使うが、betweenだが下限は含まない<br>
	 * i.e. 0-100:¥30/100-200:¥50/200+:¥70のように指定するが、¥200なら¥50
	 */
	public static Predicate<BigDecimal> between(BigDecimal from, BigDecimal to) {
		return 	s -> NumberUtil.isInRange(s, from, to) && !NumberUtil.equals(s, from);
	}

}
