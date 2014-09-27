package com.github.fbdo.pricing;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by fabio on 27/09/14.
 */
public interface PricingEngine {

    BigDecimal getPrice(Map<String, Object> attributes);
}
