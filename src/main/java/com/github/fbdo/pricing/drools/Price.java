package com.github.fbdo.pricing.drools;

import java.math.BigDecimal;

class Price {
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
