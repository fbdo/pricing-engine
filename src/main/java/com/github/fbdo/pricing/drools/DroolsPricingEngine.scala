package com.github.fbdo.pricing.drools;

import com.github.fbdo.pricing.FileUtils;
import com.github.fbdo.pricing.PricingEngine;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

class DroolsPricingEngine extends PricingEngine {

      @Override
    def getPrice(attributes: Map[String, Object]): BigDecimal = {
        val ks = KieServices.Factory.get
        val kr = ks.getRepository
        val kModule = kr.addKieModule(ks.getResources().newFileSystemResource(FileUtils.getScriptFolder("drools")))

        val kContainer = ks.newKieContainer(kModule.getReleaseId)

        val kSession = kContainer.newKieSession
        kSession.setGlobal("out", System.out)
        val price = new Price 
        kSession.setGlobal("price", price)

        kSession.insert(attributes)
        kSession.fireAllRules
        return price.getValue
    }


}
