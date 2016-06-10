package com.cisco.mazut

/**
  * Created by dnezh on 6/10/16.
  */

/*
I need modify fields:

<?xml version="1.0" encoding="utf-8"?>
<logs xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.4.1.1" xmlns="http://www.witsml.org/schemas/1series" xsi:schemaLocation="http://www.witsml.org/schemas/1series ../xsd_schemas/obj_log.xsd">
 <log uid="measurement">
 <startDateTimeIndex>2015-10-11T13:09:52.000Z</startDateTimeIndex>
 <endDateTimeIndex>2015-10-11T13:10:02.756Z</endDateTimeIndex>
 <logData>
   <mnemonicList>LAF, ST, AST, REV-ST, REV-AST ,TMP</mnemonicList>
   <unitList>ft, none, none, none, none, degF</unitList>
   <data> </data>
 </logData>
 </log>
</logs>
 */

object DTSFakeGen extends App {
  val path = "/Users/dnezh/Devel/scala/samples/dts_generator/src/main/resources/data/dts/sample_dts.xml"
  load_data(path)

  def load_data(path: String) = {
    val logs = xml.XML.loadFile(path)
    val startDateTimeIndex = logs \ "log" \ "startDateTimeIndex" text
    val endDateTimeIndex = logs \ "log" \ "endDateTimeIndex" text
    val data = logs \ "log" \ "logData" \ "data"
    println("startDateTimeIndex: " + startDateTimeIndex)
    println("endDateTimeIndex: " + endDateTimeIndex)
//    data.foreach { d =>
//      print(d.text)
//    }
  }
}
