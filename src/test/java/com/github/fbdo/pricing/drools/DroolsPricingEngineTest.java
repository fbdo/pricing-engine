package com.github.fbdo.pricing.drools;

import com.github.fbdo.pricing.PricingEngine;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabio on 27/09/14.
 */
public class DroolsPricingEngineTest {

    private PricingEngine engine = new DroolsPricingEngine();

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
