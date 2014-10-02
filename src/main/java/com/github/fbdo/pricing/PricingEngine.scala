package com.github.fbdo.pricing;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by fabio on 27/09/14.
 */
trait PricingEngine {

    def getPrice(attributes: Map[String, Object]): BigDecimal
}
