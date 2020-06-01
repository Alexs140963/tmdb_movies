package ru.it_soft.bigdata.tmdb_movies.tools

import org.apache.spark.sql.catalyst.encoders.RowEncoder
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.types.LongType

object Main extends App {

  val spark = SparkSession.builder()
    .appName("tmdb_movies")
    .config("spark.master", "local")
    .getOrCreate()

  import spark.implicits._

  val sc = spark.sparkContext
  val df = spark.read.format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .option("quote", "\"")
    .option("escape", "\"")
    .load("oozie/tmdb_movies/dim/tmdb_5000_movies.csv")
    .na.drop

//  df.show(10)

  val df1 = df.select("genres", "revenue").withColumn("revenue_num", col("revenue").cast(LongType))
      .select("genres", "revenue_num")
  
//  println(df1.schema)
//  df1.show(10)

  val braces = """\{.*?\}""".r

  val encoder = RowEncoder(df1.schema)
  val df2 = df1.flatMap(r => braces.findAllMatchIn(r.getString(0)).map(s => Row(s.toString, r.getLong(1))))(encoder)
// df2.show(10)

  val df3 = df2.groupBy("genres").avg()
// df3.show(truncate = false)
}