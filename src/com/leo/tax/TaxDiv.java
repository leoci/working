package com.leo.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;

import lombok.AllArgsConstructor;

import com.worksap.company.ac.util.EnumUtil;

public enum TaxDiv {
    OUT(1, "外税") {
        @Override
        BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round) {
            return amount.multiply(rate).divide(ONE_HUNDRED, 0, round);
        }

        @Override
        public BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax) {
            return amount;
        }

    },
    IN(2, "内税") {
        @Override
        BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round) {
            BigDecimal dividend = amount.multiply(ONE_HUNDRED);
            BigDecimal divisor = rate.add(ONE_HUNDRED);
            return amount.subtract(dividend.divide(divisor, 0, round));
        }

        @Override
        public BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax) {
            return amount.subtract(tax);
        }
    },
    FREE(3, "非課税") {
        @Override
        BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round) {
            return BigDecimal.ZERO;
        }

        @Override
        public BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax) {
            return amount;
        }
    },
    NON(4, "不課税") {
        @Override
        BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round) {
            return BigDecimal.ZERO;
        }

        @Override
        public BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax) {
            return amount;
        }
    },
    EXEMPTION(5, "免税") {
        @Override
        BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round) {
            return BigDecimal.ZERO;
        }

        @Override
        public BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax) {
            return amount;
        }
    };

    private final int val;
    private final String caption;
    
    private TaxDiv(int val, String caption) {
    	this.val = val;
    	this.caption = caption;
	}

    private final static BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    abstract BigDecimal getTax(BigDecimal amount, BigDecimal rate, RoundingMode round);

    public abstract BigDecimal getTaxExcludedAmount(BigDecimal amount, BigDecimal tax);


}
