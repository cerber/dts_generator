package com.cisco.mazut.dts.utils

import javax.xml.bind.annotation.adapters.XmlAdapter

/**
  * Created by dbort on 31.03.2016.
  */
class RawDataAdapter extends XmlAdapter[String, String] {

  @throws(classOf[Exception])
  def marshal(string: String): String = {
    string
  }

  @throws(classOf[Exception])
  def unmarshal(string: String): String = {
    string.replaceAll("\n", "")
  }

}
