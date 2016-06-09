package com.cisco.mazut.dts.utils

import java.text.SimpleDateFormat
import java.util.{Date, Locale}
import javax.xml.bind.annotation.adapters.XmlAdapter

/**
  * Created by dbort on 21.03.2016.
  */
class DateAdapter extends XmlAdapter[String, Date] {
  private val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
  //private val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)

  @throws(classOf[Exception])
  def marshal(v: Date): String = {
    dateFormat synchronized {
      dateFormat.format(v)
    }
  }

  @throws(classOf[Exception])
  def unmarshal(v: String): Date = {
    dateFormat synchronized {
      dateFormat.parse(v)
    }
  }
}
