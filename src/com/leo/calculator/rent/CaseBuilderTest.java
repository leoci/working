package com.leo.calculator.rent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.leo.calculator.rent.PredicateBuilder.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.leo.calculator.rent.CaseBuilder.Cases;

public class CaseBuilderTest {

	@Test
	public void test() {
		assertThat("Not yet implemented", is(true));
		
		//固定賃料
		Cases cases_1_1 = CaseBuilder.when(CaseBuilder.ALWAYS).then(80_000L)；
		Cases cases_1_2 = CaseBuilder.when(CaseBuilder.ALWAYS).then(80_000L)；
		Cases cases_1_3 = CaseBuilder.when(CaseBuilder.ALWAYS).then(100_000L)；
		
		
		//80_000以下なら固定50_000、以上であれば歩合3%
		Cases<BigDecimal> cases_99_1 = CaseBuilder
		    .when(below(80_000L)).then(fix(50_000L))
		    .otherwise(rate(0.03d));
		    
		assertThat(cases_99_1.setSource(79_999L).getResult(), is(50_000L));
		assertThat(cases_99_1.setSource(80_000L).getResult(), is(50_000L));
		assertThat(cases_99_1.setSource(100_000L).getResult(), is(3_000L));
				
		  //80_000以下なら固定50_000、以上であれば歩合0-80_000:8%, 80_000-200_000:5%, 200_000+:3%
			Cases cases_99_2 = CaseBuilder
			    .when(below(80_000L)).then(fix(50_000L))
			    .otherwise(
			    		CaseBuilder
			    		    .when(at(200_000L)).then(rate(0.08d))
			    		    .when(at(80_000L)).then(rate(0.05d))
			    		    .otherwise(rate(0.03d))
			    		);
		
			 //80_000以下なら固定50_000、以上であれば範囲ごとの逓減0-80_000:8%, 80_000-200_000:5%, 200_000+:3%
			Cases cases_99_2 = CaseBuilder
			    .when(below(80_000L)).then(fix(50_000L))
			    .otherwise(
			    		CaseBuilder
			    		    .when(at(200_000L)).then(rate(0.08d))
			    		    .when(at(80_000L)).then(rate(0.05d))
			    		    .otherwise(rate(0.03d))
			    		);
		
	}

}
