package com.cisco.mazut.dts.utils

import java.math.MathContext
import javax.xml.bind.annotation.adapters.XmlAdapter

/**
  * Created by dbort on 21.03.2016.
  */
class BigDecimalAdapter extends XmlAdapter[String, java.math.BigDecimal] {

  @throws(classOf[Exception])
  def marshal(bigDecimal: java.math.BigDecimal): String = {
    if (bigDecimal != null) bigDecimal.toString
    else ""
  }

  @throws(classOf[Exception])
  def unmarshal(s: String): java.math.BigDecimal = {
    try {
      new java.math.BigDecimal(s, new MathContext(3))
    } catch  {
      case e: NumberFormatException => null
    }
  }

}
