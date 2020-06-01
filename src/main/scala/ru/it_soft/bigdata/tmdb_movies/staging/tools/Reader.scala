package ru.it_soft.bigdata.staging.tools

import org.apache.hadoop.io.Text
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import ru.it_soft.bigdata.staging.RefusePattern
import ru.it_soft.bigdata.system.Parameters

object Reader {
  def readSequenceFile(sc: SparkContext, path: String, delimiter: String): RDD[Array[String]] = {
    sc.sequenceFile(path, classOf[Text], classOf[Text]).
      map(x => x._2.toString.split(delimiter, -1))
  }

  def readTextFile(spark: SparkSession, path: String): Broadcast[Array[String]] = {
    spark.sparkContext.broadcast(spark.sparkContext.textFile(path).collect())
}
 
}
