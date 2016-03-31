package com.leo.calculator.rent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.leo.calculator.rent.CaseBuilder.Cases;

/**
 * TODO 中間処理の端数処理は決めうち。それぞれ切り捨てて、合計額と差分があれば最後に加算する
 */
public class RentCalculator {

	private final Cases cases;
	private final int scale = 0;
	private final RoundingMode roundingModeOnProcess = RoundingMode.DOWN;
	private final RoundingMode roundingMode;

	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.DOWN;

	public RentCalculator(Cases cases) {
		this.cases = cases;
		this.roundingMode = DEFAULT_ROUNDING_MODE;
	}

	public RentCalculator(Cases cases, RoundingMode roundingMode) {
		this.cases = cases;
		this.roundingMode = roundingMode;
	}

	public BigDecimal calculate(BigDecimal source) {
		return calculateFull(source).getValue();
	}

	public Result calculateFull(BigDecimal source) {
		List<Process> processes;
		if (cases.getMinimumCase().filter(c -> c.getWhen().test(source)).isPresent()) {
			processes = Arrays.asList(cases.getMinimumCase().get().getOperator().apply(source));
		} else {
			processes =cases.getCases().stream()
					.filter(c -> c.getWhen().test(source))
					.map(c -> c.getOperator().apply(source))
					.collect(Collectors.toList());
		}
		Result result = summarize(processes);
		return finalize(result);
	}

	private Result finalize(Result result) {
		BigDecimal value = result.getValue();
		if (cases.getResultCase().filter(c -> c.getWhen().test(value)).isPresent()) {
			return summarize(Arrays.asList(cases.getResultCase().get()
					.getOperator().apply(value)));
		}
		return result;
	}

	private Result summarize(List<Process> processes) {
		BigDecimal value = processes.stream().map(Process::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		value = value.setScale(scale, roundingMode);

		processes.forEach(p -> p.setValue(p.getValue().setScale(scale,
				roundingModeOnProcess)));
		BigDecimal offValue = processes.stream().map(Process::getValue)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		if (!NumberUtil.equals(value, offValue)) {
			BigDecimal fraction = value.subtract(offValue);
			Process lastProcess = processes.get(processes.size() - 1);
			lastProcess.setValue(lastProcess.getValue().add(fraction));
			lastProcess.setFraction(fraction);// TODO どう残すべきなのか？
		}
		return new Result(value, processes);
	}

}
