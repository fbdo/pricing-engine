package com.github.fbdo.pricing.drools;

import com.github.fbdo.pricing.FileUtils;
import com.github.fbdo.pricing.PricingEngine;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;
import java.util.Map;

public class DroolsPricingEngine implements PricingEngine {
    @Override
    public BigDecimal getPrice(Map<String, Object> attributes) {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieModule kModule = kr.addKieModule(ks.getResources().newFileSystemResource(FileUtils.getScriptFolder("drools")));

        KieContainer kContainer = ks.newKieContainer(kModule.getReleaseId());

        KieSession kSession = kContainer.newKieSession();
        kSession.setGlobal("out", System.out);
        Price price = new Price();
        kSession.setGlobal("price", price);

        kSession.insert(attributes);
        kSession.fireAllRules();
        return price.getValue();
    }


}
