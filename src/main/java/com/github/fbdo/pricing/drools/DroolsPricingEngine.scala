package com.github.fbdo.pricing.drools;

import java.{math, util}

import com.github.fbdo.pricing.{FileUtils, PricingEngine}
import org.kie.api.KieServices;

class DroolsPricingEngine extends PricingEngine {

  override def getPrice(attributes: util.Map[String, Object]): math.BigDecimal = {
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
