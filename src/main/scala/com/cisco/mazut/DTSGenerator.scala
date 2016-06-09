package com.cisco.mazut

/**
  * Created by dnezh on 6/8/16.
  */

import com.cisco.mazut.dts.utils.XMLParser
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.{DataFrame, Row, SQLContext}

import scala.collection.JavaConversions._

object DTSGenerator {

  private def dtFormat = new java.text.SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS")
  val sampleDTS = "data/mllib/sample_dts.xml"

  val dts = com.cisco.mazut.dts.utils.XMLParser.parse("data/mllib/sample_dts.xml").logs.get(0)

  def load_data(sqlContext: SQLContext, path: String): DataFrame = {
    val dts = XMLParser.parse(path).logs.get(0)

    val ts = new java.sql.Timestamp(dts.startDateTimeIndex.getTime)
    val wellName = dts.nameWell
    val processName = dts.customData.userConfiguration.mainMeasurementConfiguration.configurationName

    val rowRDD = dts.logData.data
      .map(_.split(",")).map(p =>
        Row(ts, p(0).toDouble, p(5).toDouble, wellName, processName))

    import  org.apache.spark.sql.types._
    val schema = StructType(List(
      StructField("ts",           TimestampType),
      StructField("depth",        DoubleType),
      StructField("temperature",  DoubleType),
      StructField("wellName",     StringType),
      StructField("processName",  StringType)
    ))

    sqlContext.createDataFrame(rowRDD, schema)
  }

  def eval(sqlContext: SQLContext) = {
    // Load the data stored in LIBSVM format as a DataFrame.
//    val data = sqlContext.read.format("libsvm")
//      .load("data/mllib/sample_multiclass_classification_data.txt")

    val data = load_data(sqlContext, sampleDTS)

    // Split the data into train and test
    val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
    val train = splits(0)
    val test = splits(1)
    // specify layers for the neural network:
    // input layer of size 4 (features), two intermediate of size 5 and 4
    // and output of size 3 (classes)
    val layers = Array[Int](4, 5, 4, 3)
    // create the trainer and set its parameters
    val trainer = new MultilayerPerceptronClassifier()
      .setLayers(layers)
      .setBlockSize(128)
      .setSeed(1234L)
      .setMaxIter(100)
    // train the model
    val model = trainer.fit(train)
    // compute precision on the test set
    val result = model.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
    val evaluator = new MulticlassClassificationEvaluator()
      .setMetricName("precision")
    println("Precision: " + evaluator.evaluate(predictionAndLabels))

    println("Params:\n" + evaluator.explainParams())
    result.show(20)
  }
}
