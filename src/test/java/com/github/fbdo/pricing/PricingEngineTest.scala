package com.github.fbdo.pricing;

import com.github.fbdo.pricing.drools.DroolsPricingEngine;
import com.github.fbdo.pricing.groovy.GroovyPricingEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public final class PricingEngineTest {

    private PricingEngine engine;

    public PricingEngineTest(PricingEngine pe) {
        this.engine = pe;
    }

    @Parameterized.Parameters
    public static List<PricingEngine> pricingEngines() {
        return Arrays.asList(new DroolsPricingEngine(), new GroovyPricingEngine());
    }

    @Test
    public void testAccountActivationFixedPricing() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("event", "accountActivation");
        Assert.assertEquals(new BigDecimal("1.50"), engine.getPrice(attrs));
    }

    @Test
    public void testSubscriptionRenewalLayer1() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("event", "subscriptionRenewal");
        attrs.put("amount", new BigDecimal("20.00"));
        Assert.assertEquals(new BigDecimal("2.50"), engine.getPrice(attrs));
    }

    @Test
    public void testSubscriptionRenewalLayer2() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("event", "subscriptionRenewal");
        attrs.put("amount", new BigDecimal("200.00"));
        Assert.assertEquals(new BigDecimal("3.0000"), engine.getPrice(attrs));
    }

    @Test
    public void testSubscriptionRenewalLayer3() {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("event", "subscriptionRenewal");
        attrs.put("amount", new BigDecimal("1500.00"));
        Assert.assertEquals(new BigDecimal("7.50000"), engine.getPrice(attrs));
    }
}
