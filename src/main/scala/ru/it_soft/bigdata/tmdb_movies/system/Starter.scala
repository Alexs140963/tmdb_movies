package ru.it_soft.bigdata.tmdb_movies.system

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession
import ru.it_soft.bigdata.tmdb_movies.system.Parameters

object Starter extends App {
  implicit val spark: SparkSession = SparkSession
    .builder()
    .enableHiveSupport()
    .getOrCreate()

  implicit val fs: FileSystem = FileSystem.get(spark.sparkContext.hadoopConfiguration)
  implicit val params: Parameters = new Parameters(args)

  
  LogManager.setLogWriteOnlyErrorMessages

}
