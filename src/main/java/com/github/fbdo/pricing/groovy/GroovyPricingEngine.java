package com.github.fbdo.pricing.groovy;

import com.github.fbdo.pricing.FileUtils;
import com.github.fbdo.pricing.PricingEngine;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

public class GroovyPricingEngine implements PricingEngine {
    @Override
    public BigDecimal getPrice(Map<String, Object> attributes) {
        ClassLoader parent = getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        GroovyObject groovyObject;
        try {
            Class groovyClass = loader.parseClass(new File(FileUtils.getScriptFolder("groovy"), attributes.get("event") + ".groovy"));

            groovyObject = (GroovyObject) groovyClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return (BigDecimal) groovyObject.invokeMethod("run", new Object[] {attributes});
    }
}
