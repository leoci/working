package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.worksap.company.ac.util.enums.MinusSign;
import com.worksap.company.ac.util.value.ColorText;

/**
 * 数値処理系（特にBigDecimal）の処理系をまとめる。
 */
public final class NumberUtil {
    private NumberUtil() {
    }

    /**
     * 無駄なscaleを省いたり、マイナスscaleを0にしたりする<br>
     * ex. 0.100 ⇒ 0.1<br>
     * ex. 1E+1 ⇒ 10
     */
    public static BigDecimal normalizeScale(BigDecimal value) {
        if (value == null) {
            return null;
        }
        if (isZero(value)) {
            return BigDecimal.ZERO;
        } else if (value.scale() < 0) {
            return value.setScale(0, RoundingMode.DOWN);
        } else if (value.scale() == 0) {
            return value;
        }
        BigDecimal stripVal = value.stripTrailingZeros();
        if (stripVal.scale() < 0) {
            return stripVal.setScale(0, RoundingMode.DOWN);
        } else {
            return stripVal;
        }
    }

    /**
     * 小数が無い場合true
     */
    public static boolean isNotDecimal(BigDecimal value) {
        if (value.scale() <= 0) {
            return true;
        }
        return value.stripTrailingZeros().scale() <= 0;
    }

    /**
     * 小数が在る場合true
     */
    public static boolean isDecimal(BigDecimal value) {
        return !isNotDecimal(value);
    }

    /**
     * スケール無視のequals（compareToで一致するかどうか）
     */
    public static boolean equals(BigDecimal a, BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    /**
     * スケール無視のnot equals（compareToで一致しないかどうか）
     */
    public static boolean notEquals(BigDecimal a, BigDecimal b) {
        return !equals(a, b);
    }

    /**
     * Numberだったら何でも出来るようにしたequals
     */
    public static boolean equals(Number a, Number b) {
        if (a == b) {
            return true;
        } else if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return equals((BigDecimal)a, (BigDecimal)b);
        } else {
            return a != null
                    && Double.compare(a.doubleValue(), b.doubleValue()) == 0;
        }
    }

    /**
     * マイナスかどうか？
     */
    public static boolean isNegative(BigDecimal value) {
        return value.signum() < 0;
    }

    /**
     * マイナスかどうか？
     */
    public static boolean isNegative(int value) {
        return value < 0;
    }

    /**
     * マイナスかどうか？
     */
    public static boolean isNegative(long value) {
        return value < 0;
    }

    /**
     * マイナスかどうか？
     */
    public static boolean isNegative(double value) {
        return value < 0;
    }

    /**
     * 正の数、または0かどうか？
     */
    public static boolean isPositiveOrZero(BigDecimal value) {
        return value.signum() >= 0;
    }

    /**
     * 正の数、または0かどうか？
     */
    public static boolean isPositiveOrZero(int value) {
        return value >= 0;
    }

    /**
     * 正の数、または0かどうか？
     */
    public static boolean isPositiveOrZero(long value) {
        return value >= 0;
    }

    /**
     * 正の数、または0かどうか？
     */
    public static boolean isPositiveOrZero(double value) {
        return value >= 0;
    }

    /**
     * 正の数かどうか？<br>
     * <br>
     * 関数名としてNotZeroは不要だが、理系じゃない人向けに書いておく<br>
     * 正の数（Positive）という言葉は0を含まないという理系的常識を把握していない人は結構いるので。
     */
    public static boolean isPositiveNotZero(BigDecimal value) {
        return value.signum() > 0;
    }

    /**
     * 正の数かどうか？<br>
     * <br>
     * 関数名としてNotZeroは不要だが、理系じゃない人向けに書いておく<br>
     * 正の数（Positive）という言葉は0を含まないという理系的常識を把握していない人は結構いるので。
     */
    public static boolean isPositiveNotZero(int value) {
        return value > 0;
    }

    /**
     * 正の数かどうか？<br>
     * <br>
     * 関数名としてNotZeroは不要だが、理系じゃない人向けに書いておく<br>
     * 正の数（Positive）という言葉は0を含まないという理系的常識を把握していない人は結構いるので。
     */
    public static boolean isPositiveNotZero(long value) {
        return value > 0;
    }

    /**
     * 正の数かどうか？<br>
     * <br>
     * 関数名としてNotZeroは不要だが、理系じゃない人向けに書いておく<br>
     * 正の数（Positive）という言葉は0を含まないという理系的常識を把握していない人は結構いるので。
     */
    public static boolean isPositiveNotZero(double value) {
        return value > 0;
    }

    /**
     * 0かどうか？
     */
    public static boolean isZero(BigDecimal value) {
        return value.signum() == 0;
    }

    /**
     * 0かどうか？
     */
    public static boolean isZero(int value) {
        return value == 0;
    }

    /**
     * 0かどうか？
     */
    public static boolean isZero(long value) {
        return value == 0L;
    }

    /**
     * 0かどうか？
     */
    public static boolean isZero(double value) {
        return value == 0D;
    }

    /**
     * 0でない？
     */
    public static boolean isNotZero(BigDecimal value) {
        return !isZero(value);
    }

    /**
     * 0でない？
     */
    public static boolean isNotZero(int value) {
        return !isZero(value);
    }

    /**
     * 1でない？
     */
    public static boolean isNotOne(int value) {
        return value != 1;
    }

    /**
     * 0でない？
     */
    public static boolean isNotZero(long value) {
        return !isZero(value);
    }

    /**
     * 0でない？
     */
    public static boolean isNotZero(double value) {
        return !isZero(value);
    }

    /**
     * @return 整数桁数
     */
    public static int getIntegerDigits(BigDecimal value) {
        return Math.max(value.precision() - value.scale(), 0);
    }

    /**
     * BigDecimalのString化。<br>
     * と言いながらtoPlainStringだけど。<br>
     * さらに下記のような処理をしてる<br>
     * ex. 0.10 ⇒ "0.1"<br>
     * 通常のtoPlainStringだと"0.10"になる<br>
     * 他は普通 <br>
     * ex. 0.1 ⇒ "0.1"<br>
     * ex. 1E+1 ⇒ "10"<br>
     */
    public static String toString(BigDecimal value) {
        if (value == null) {
            return null;
        }
        if (value.scale() <= 0) {
            return value.toPlainString();
        }
        return value.stripTrailingZeros().toPlainString();
    }

    /**
     * ただのtoString書いてるだけ
     */
    public static String toString(int value) {
        return String.valueOf(value);
    }

    /**
     * ただのtoString書いてるだけ
     */
    public static String toString(long value) {
        return String.valueOf(value);
    }

    /**
     * ただのtoString書いてるだけ
     */
    public static String toString(double value) {
        return toString(BigDecimal.valueOf(value));
    }

    /**
     * ex. precision:8 scale:3 / return 99999.999
     *
     * @param precision
     * @param scale
     * @return
     */
    public static BigDecimal getMaxValue(int precision, int scale) {
        // ex. precision:8 scale:3
        return BigDecimal.valueOf(1, scale - precision)// 100000
                .subtract(BigDecimal.valueOf(1, scale)/* 0.001 */)// 99999.999
        ;
    }

    /**
     * ex. intDigits:5 decimalDigits:3 / return 99999.999
     *
     * @param intDigits
     * @param decimalDigits
     * @return
     */
    public static BigDecimal getMaxValueFromDigits(int intDigits, int decimalDigits) {
        return getMaxValue(intDigits + decimalDigits, decimalDigits);
    }

    /**
     * valueがrange1とrange2の間にいるかどうか？
     */
    public static boolean isInRange(BigDecimal value, BigDecimal range1, BigDecimal range2) {
        if (range1.compareTo(range2) > 0) {
            return range1.compareTo(value) >= 0 && value.compareTo(range2) >= 0;
        } else if (range1.compareTo(range2) < 0) {
            return range1.compareTo(value) <= 0 && value.compareTo(range2) <= 0;
        } else {
            return NumberUtil.equals(value, range1);
        }
    }


    /**
     * b1 > b2
     *
     * @param b1
     * @param b2
     * @return
     */
    public static boolean isGreaterThan(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) > 0;
    }

    /**
     * b1 > b2
     *
     * @param b1
     * @param b2
     * @return
     */
    public static boolean isSmallerThan(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) < 0;
    }

    /**
     * b1 >= b2
     *
     * @param b1
     * @param b2
     * @return
     */
    public static boolean isMoreThan(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) >= 0;
    }

    /**
     * b1 >= b2
     *
     * @param b1
     * @param b2
     * @return
     */
    public static boolean isLessThan(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) <= 0;
    }

}
