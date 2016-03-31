package com.leo.calculator.rent;

import static com.leo.calculator.rent.OperatorBuilder.fixed;
import static com.leo.calculator.rent.OperatorBuilder.fixedRate;
import static com.leo.calculator.rent.OperatorBuilder.rangeRate;
import static com.leo.calculator.rent.OperatorBuilder.rangeRateFrom;
import static com.leo.calculator.rent.OperatorBuilder.rangeRateTo;
import static com.leo.calculator.rent.OperatorBuilder.rate;
import static com.leo.calculator.rent.PredicateBuilder.at;
import static com.leo.calculator.rent.PredicateBuilder.upto;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class CaseBuilderTest {

	/**
	 * 固定
	 */
	@Test
	public void test_case_1() {
		RentCalculator calculator_1_1 = CaseBuilder.always(fixed(80_000L).note("固定")).build(RoundingMode.UP);
		RentCalculator calculator_1_2 = CaseBuilder.always(fixed(80_000L)).build(RoundingMode.DOWN);
		RentCalculator calculator_1_3 = CaseBuilder.always(fixed(100_000L)).build(RoundingMode.HALF_UP);

		assertThat(calculator_1_1.calculate(BigDecimal.valueOf(79_999L)), is(BigDecimal.valueOf(80_000L)));
		assertThat(calculator_1_2.calculate(BigDecimal.valueOf(80_000L)), is(BigDecimal.valueOf(80_000L)));
		assertThat(calculator_1_3.calculate(BigDecimal.valueOf(100_001L)), is(BigDecimal.valueOf(100_000L)));
	}

	/**
	 * 完全歩合
	 */
	@Test
	public void test_case_2() {
		RentCalculator calculator_2_1 = CaseBuilder.always(rate(3d)).build(RoundingMode.UP);
		RentCalculator calculator_2_2 = CaseBuilder.always(rate(3d)).build(RoundingMode.UP);
		RentCalculator calculator_2_3 = CaseBuilder.always(rate(3d)).build(RoundingMode.DOWN);
		RentCalculator calculator_2_4 = CaseBuilder.always(rate(3d)).build(RoundingMode.DOWN);
		RentCalculator calculator_2_5 = CaseBuilder.always(rate(3d)).build(RoundingMode.HALF_UP);
		RentCalculator calculator_2_6 = CaseBuilder.always(rate(5d)).build(RoundingMode.HALF_UP);

		assertThat(calculator_2_1.calculate(BigDecimal.valueOf(80_016L)), is(BigDecimal.valueOf(2_401L)));
		assertThat(calculator_2_2.calculate(BigDecimal.valueOf(80_017L)), is(BigDecimal.valueOf(2_401L)));
		assertThat(calculator_2_3.calculate(BigDecimal.valueOf(80_016L)), is(BigDecimal.valueOf(2_400L)));
		assertThat(calculator_2_4.calculate(BigDecimal.valueOf(80_017L)), is(BigDecimal.valueOf(2_400L)));
		assertThat(calculator_2_5.calculate(BigDecimal.valueOf(80_016L)), is(BigDecimal.valueOf(2_400L)));
		assertThat(calculator_2_6.calculate(BigDecimal.valueOf(80_011L)), is(BigDecimal.valueOf(4_001L)));
	}

	/**
	 * 固定＋完全歩合
	 */
	@Test
	public void test_case_3() {
		RentCalculator calculator_3_1 = CaseBuilder.base(fixed(80_000L)).always(rate(3d)).build(RoundingMode.UP);
		RentCalculator calculator_3_2 = CaseBuilder.base(fixed(80_017L)).always(rate(3d)).build(RoundingMode.DOWN);
		RentCalculator calculator_3_3 = CaseBuilder.base(fixed(100_000L)).always(rate(5d)).build(RoundingMode.HALF_UP);

		assertThat(calculator_3_1.calculate(BigDecimal.valueOf(79_983L)), is(BigDecimal.valueOf(82_400L)));
		assertThat(calculator_3_2.calculate(BigDecimal.valueOf(80_017L)), is(BigDecimal.valueOf(82_417L)));
		assertThat(calculator_3_3.calculate(BigDecimal.valueOf(100_009L)), is(BigDecimal.valueOf(105_000L)));
	}

	/**
	 * 完全歩合＋固定保証
	 */
	@Test
	public void test_case_4() {
		RentCalculator calculator_4_1 = CaseBuilder.minimumOf(upto(120_016L)).then(fixedRate(120_016L, 3d))
				.always(rate(3d)).build(RoundingMode.UP);
		RentCalculator calculator_4_2 = CaseBuilder.minimumOf(upto(120_011L)).then(fixedRate(120_011L, 5d))
				.always(rate(5d)).build(RoundingMode.DOWN);
		RentCalculator calculator_4_3 = CaseBuilder.minimumOf(upto(120_011L)).then(fixedRate(120_011L, 5d))
				.always(rate(5d)).build(RoundingMode.HALF_UP);

		assertThat(calculator_4_1.calculate(BigDecimal.valueOf(120_015L)), is(BigDecimal.valueOf(3_601L)));
		assertThat(calculator_4_2.calculate(BigDecimal.valueOf(120_011L)), is(BigDecimal.valueOf(6_000L)));
		assertThat(calculator_4_3.calculate(BigDecimal.valueOf(120_012L)), is(BigDecimal.valueOf(6_001L)));
	}

	/**
	 * 売上逓減＋固定保証
	 */
	@Test
	public void test_case_5() {
		RentCalculator calculator_5_1 = CaseBuilder.minimumOf(upto(50_000L)).then(fixedRate(50_000L, 10d))
				.append(
						CaseBuilder
						.when(at(200_000L)).then(rangeRateFrom(200_000L, 3d))
						.when(at(80_000L)).then(rangeRate(80_000L, 200_000L, 5d))
						.when(at(0L)).then(rangeRate(0L, 80_000L, 8d))
						).build(RoundingMode.DOWN);

		assertThat(calculator_5_1.calculate(BigDecimal.valueOf(50_000L)), is(BigDecimal.valueOf(5_000L)));
		assertThat(calculator_5_1.calculate(BigDecimal.valueOf(120_000L)), is(BigDecimal.valueOf(8_400L)));
	}

	/**
	 * 固定＋売上逓減
	 */
	@Test
	public void test_case_6() {
		RentCalculator calculator_6_1 = CaseBuilder.base(fixed(150_000L))
				.when(at(0L)).then(rangeRateTo(200_000L, 30d))
				.when(at(200_000L)).then(rangeRateFrom(200_000L, 20d))
				.build(RoundingMode.UP);
		RentCalculator calculator_6_2 = CaseBuilder.base(fixed(150_000L))
				.when(at(0L)).then(rangeRateTo(200_008L, 30d))
				.when(at(200_008L)).then(rangeRate(200_008L, 400_000L, 20d))
				.when(at(400_000L)).then(rangeRateFrom(400_000L, 15d))
				.build(RoundingMode.DOWN);
		RentCalculator calculator_6_3 = CaseBuilder.base(fixed(150_000L))
				.when(at(100_000L)).then(rangeRate(100_000L, 200_008L, 30d))
				.when(at(200_008L)).then(rangeRate(200_008L, 400_000L, 20d))
				.when(at(400_000L)).then(rangeRate(400_000L, 600_002L, 15d))
				.when(at(600_002L)).then(rangeRateFrom(600_002L, 10d))
				.build(RoundingMode.HALF_UP);

		assertThat(calculator_6_1.calculate(BigDecimal.valueOf(150_008L)), is(BigDecimal.valueOf(195_003L)));
		assertThat(calculator_6_2.calculate(BigDecimal.valueOf(500_018L)), is(BigDecimal.valueOf(265_003L)));
		assertThat(calculator_6_3.calculate(BigDecimal.valueOf(700_005L)), is(BigDecimal.valueOf(260_002L)));
	}

	/**
	 * 逓減＋最低保障
	 */
	@Test
	public void test_case_7() {
		RentCalculator calculator_7_1 = CaseBuilder
				.when(at(200_000L)).then(rangeRateFrom(200_000L, 3d))
				.when(at(80_000L)).then(rangeRate(80_000L, 200_000L, 5d))
				.when(at(0L)).then(rangeRate(0L, 80_000L, 8d))
				.whenResultIs(upto(6_000L)).then(fixed(6_000L).note("賃料に対する最低保障"))
				.build(RoundingMode.DOWN);

		assertThat(calculator_7_1.calculate(BigDecimal.valueOf(50_000L)), is(BigDecimal.valueOf(6_000L)));
		assertThat(calculator_7_1.calculate(BigDecimal.valueOf(120_000L)), is(BigDecimal.valueOf(8_400L)));
	}

}
