package com.github.fbdo.pricing.groovy

import java.io.File
import java.math.BigDecimal
import java.util

import com.github.fbdo.pricing.{FileUtils, PricingEngine}
import groovy.lang.{GroovyClassLoader, GroovyObject}

/**
 * Created by fabio on 09/10/14.
 */
class GroovyPricingEngine extends PricingEngine {

  override def getPrice(attributes: util.Map[String, Object]): BigDecimal = {
    val parent: ClassLoader = getClass.getClassLoader
    val loader: GroovyClassLoader = new GroovyClassLoader(parent)

    var groovyObject: GroovyObject = null
    try {
      val groovyClass: Class[_] = loader.parseClass(new File(FileUtils.getScriptFolder("groovy"), attributes.get("event") + ".groovy"))
      groovyObject = groovyClass.newInstance.asInstanceOf[GroovyObject]
    }
    catch {
      case e: Exception => {
        throw new RuntimeException(e)
      }
    }
    return groovyObject.invokeMethod("run", Array[AnyRef](attributes)).asInstanceOf[BigDecimal]
  }

}
