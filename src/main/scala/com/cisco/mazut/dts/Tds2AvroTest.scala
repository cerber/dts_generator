package com.cisco.mazut.dts

import java.io.{File, PrintWriter}

import org.apache.log4j.{Level, Logger}
import com.google.gson.GsonBuilder
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import com.cisco.mazut.dts.model.Logs
import com.cisco.mazut.dts.utils.XMLParser
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by cerber on 4/14/16.
  */

case class LogData(data: Array[String], mnemonicList: String, unitList: String)

object Tds2AvroTest {

  val conf = new SparkConf().setAppName("Tds2Avro Test").setMaster("local")
  val sc = new SparkContext(conf)
  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)

  import com.databricks.spark.avro._
  val sqlContext = new SQLContext(sc)
  import sqlContext.implicits._

  def main(args: Array[String]): Unit = {

    val baseDir = "./src/main/resources/samples"
    val outDir = new File( s"""$baseDir/out""")
    if (!outDir.exists()) outDir.mkdir()
    val xmlMask = ".xml".r

    val logs = XMLParser.getBindings(new File(baseDir), xmlMask)

    val gson = new GsonBuilder()
      //      .setPrettyPrinting()
      .create

    val tdsRDD: RDD[String] = sc.parallelize(logs.map { case (file, logs: Logs) =>
      gson.toJson(logs)
    })

    val tds = sqlContext.read.json(tdsRDD)


//    tds.select("logs.logData.data").write.saveAsTable()
/*
    logData:struct<data:array<string>,mnemonicList:string,unitList:string>
    {
      "logData": [
      {
        "data": ["27354.6,-0.942111,0.331794,4800.41,3964.42,-6.0745"],
        "mnemonicList": "LAF, ST, AST, REV-ST, REV-AST ,TMP",
        "unitList": "ft, none, none, none, none, degC"
      }]
    }
*/

    //    saveSchema(tds, outDir)
//        writeCSV(tds, outDir)


    tds.select("logs.logData.data", "logs.logData.mnemonicList", "logs.logData.unitList")
      .show()

//    tds.select("logs.logData.mnemonicList", "logs.logData.unitList")
//      .collect()
//      .foreach(println)
//
    val schema =   StructType("LAF,ST,AST,TMP".split(",").map(fieldName => StructField(fieldName.trim(), StringType, true)))
    var fin = sqlContext.createDataFrame(sc.emptyRDD[Row], schema)

    val logData = tds.select("logs.logData.data").take(1)(0).get(0).toString
    println(logData)


//    val l = tds.select("logs.logData.data", "logs.logData.mnemonicList", "logs.logData.unitList")
//      .collect()
//      .map { row =>
//        row.getList[Array[String]](0).toArray
////        Seq(row.getList[Array[String]](0), row.getList[String](1), row.getList[String](2))
//      }
//
//    l.foreach { r =>
//      r.foreach( b =>
//        println(b.toString)
//      )
//    }
//    val data = tds
//      .select("logs.logData.data", "logs.logData.mnemonicList", "logs.logData.unitList")
//      .collect()
//
//    println("Data Length: " + data.length)
//    data.foldLeft(0) { (n, rec) =>
//      println("Record: " + n + " length: " + rec.length)
//      println(rec.getString(1), rec.getString(2))
////      rec.getSeq(0).foreach(println)
//      n + 1
//    }
  }

  def saveSchema(tds: DataFrame, outDir: File): Unit = {
    // Save the Schema
    val schemaFileName = s"""${outDir.getAbsolutePath}/out-${System.currentTimeMillis}.schema"""
    val schema = new PrintWriter(new File(schemaFileName))
    schema.write(tds.schema.treeString)
  }

  def writeJson(tds: DataFrame, outDir: File): Unit = {
    val jsonFileName = s"""${outDir.getAbsolutePath}/out-${System.currentTimeMillis}.json"""
    tds.write.json(jsonFileName)
  }

  def writeCSV(tds: DataFrame, outDir: File): Unit = {
    val textFileName = s"""${outDir.getAbsolutePath}/out-${System.currentTimeMillis}.csv"""
    tds.select("logs.logData.data").write.saveAsTable(textFileName)
  }

  def writeAvro(tds: DataFrame, outDir: File): Unit = {
    // Write Avro
    val avroFileName = s"""${outDir.getAbsolutePath}/out-${System.currentTimeMillis}.avro"""
    tds.write.avro(avroFileName)

    // Read Avro
    val avrodf = sqlContext.read
      .format("com.databricks.spark.avro")
      .load(avroFileName)

//    avrodf.foreach { row => println(row.mkString) }
  }

  def writeParquet(tds: DataFrame, outDir: File): Unit = {
    // Write Parquet
    val parquetFileName = s"""${outDir.getAbsolutePath}/out-${System.currentTimeMillis}.parquet"""
    tds.write.parquet(parquetFileName)

    // Read Parquet
    val parquetdf = sqlContext.read.parquet(parquetFileName)
//    parquetdf.foreach { row => println(row.mkString) }
  }

}

