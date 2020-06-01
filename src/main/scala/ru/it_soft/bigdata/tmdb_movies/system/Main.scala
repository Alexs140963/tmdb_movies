package ru.it_soft.bigdata.system

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession
import ru.it_soft.bigdata.staging.tools.Writer

object Main {
  def main(args: Array[String]): Unit = {
    val config = new Configuration()
    val fs = FileSystem.get(config)
    val parameters = Parameters.instance(args, fs)
    val spark = SparkSession
      .builder
      .enableHiveSupport()
      .getOrCreate()

    spark.conf.set("spark.sql.caseSensitive", "false")
    spark.conf.set("hive.exec.dynamic.partition.mode", "nonstrict")
    spark.conf.set("hive.exec.dynamic.partition", "true")
    spark.conf.set("spark.sql.hive.convertMetastoreOrc", "false")

    Writer.saveTableAsOrc(AggInterestsJoin(spark, parameters),
      parameters.tmdb_movies_TABLE)
    spark.stop()
  }
}