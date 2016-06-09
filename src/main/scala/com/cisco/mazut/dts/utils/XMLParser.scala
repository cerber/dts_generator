package com.cisco.mazut.dts.utils

import java.io.{File, FileReader}
import javax.xml.bind.{JAXBContext, Unmarshaller}
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.sax.SAXSource

import com.cisco.mazut.dts.model.Logs
import org.xml.sax.InputSource

import scala.util.matching.Regex

/**
  * Created by dbort on 24.03.2016.
  */
object XMLParser {

  final val jaxbContext: JAXBContext = JAXBContext.newInstance(classOf[Logs])
  private val jaxbUnmarshaller: Unmarshaller = jaxbContext.createUnmarshaller
  private val sax = SAXParserFactory.newInstance()
  private val reader = sax.newSAXParser().getXMLReader

  def parse(path: String): Logs = {
    parse(path, namespaceAware = false)
  }

  def parse(path: String, namespaceAware: Boolean): Logs = {
    sax.setNamespaceAware(namespaceAware)
    val er = new SAXSource(reader, new InputSource(new FileReader(path)))
    jaxbUnmarshaller.unmarshal(er).asInstanceOf[Logs]
  }

  private def listFiles(f: File, r: Regex): Array[File] = {
    val files = if (f.isDirectory) f.listFiles() else Array(f)
    files.filter(f => r.findFirstIn(f.getName).isDefined)
  }

  def getLogs(f: File, r: Regex): Array[Logs] = listFiles(f, r).map(f => parse(f.getAbsolutePath))

  def getBindings(f: File, r: Regex): Array[(File, Logs)] = listFiles(f, r).map(f => (f, parse(f.getAbsolutePath)))

}
