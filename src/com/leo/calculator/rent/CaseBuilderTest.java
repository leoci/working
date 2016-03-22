package com.leo.calculator.rent;

import static com.leo.calculator.rent.OperatorBuilder.fixed;
import static com.leo.calculator.rent.OperatorBuilder.rate;
import static com.leo.calculator.rent.PredicateBuilder.at;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class CaseBuilderTest {

	@Test
	public void test() {

		//固定賃料
		RentCalculator calculator_1_1 = CaseBuilder.always(fixed(80_000L)).build();
		RentCalculator calculator_1_2 = CaseBuilder.always(fixed(80_000L)).build();
		RentCalculator calculator_1_3 = CaseBuilder.always(fixed(100_000L)).build();


		//80_000以下なら固定50_000、以上であれば歩合3%
		RentCalculator calculator_99_1 = CaseBuilder
				.minimumOf(at(80_000L)).then(fixed(50_000L))
				.always(rate(0.03d)).build();

		assertThat(calculator_99_1.calculate(BigDecimal.valueOf(79_999L)), is(50_000L));
		assertThat(calculator_99_1.calculate(BigDecimal.valueOf(80_000L)), is(50_000L));
		assertThat(calculator_99_1.calculate(BigDecimal.valueOf(100_000L)), is(3_000L));

		//80_000以下なら固定50_000、以上であれば歩合0-80_000:8%, 80_000-200_000:5%, 200_000+:3%
		RentCalculator calculator_99_2 = CaseBuilder
				.minimumOf(at(80_000L)).then(fixed(50_000L))
				.append(
						CaseBuilder
						.when(at(200_000L)).then(rate(0.08d))
						.when(at(80_000L)).then(rate(0.05d))
						.when(at(0L)).then(rate(0.03d))
						).build();

		//80_000以下なら固定50_000、以上であれば範囲ごとの逓減0-80_000:8%, 80_000-200_000:5%, 200_000+:3%
		RentCalculator calculator_99_3 = CaseBuilder
				.when(at(80_000L)).then(fixed(50_000L))
				.append(
						CaseBuilder
						.when(at(200_000L)).then(rate(0.08d))
						.when(at(80_000L)).then(rate(0.05d))
						.when(at(0L)).then(rate(0.03d))
						).build();

	}

}
