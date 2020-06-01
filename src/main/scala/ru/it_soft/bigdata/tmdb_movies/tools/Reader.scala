package ru.it_soft.bigdata.tmdb_movies.tools

import org.apache.hadoop.io.Text
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import spark.implicits._
import ru.it_soft.bigdata.tmdb_movies.system.Parameters

object Reader {
  val sourceDf = spark.read.format("csv").option("header", "true").load("https://www.kaggle.com/tmdb/tmdb-movie-metadata?select=tmdb_5000_movies.csv")
  val df1 = sourceDF.select(
          '_c1.alias("genres").cast(StringType),
          '_c3.alias("idd").cast(DoubleType),
          '_c12.alias("revenue").cast(DoubleType)
        )
       .where('_c1.isNotNull && '_c12.isNotNull).cashe()

   //val ds = list.flatMap(_.split(",")).toDS() // Records split by comma 
   val df2 = simpleData.toDF("genres","idd","revenue").groupByKey("idd").mean("revenue")
   //val df3 = df.groupByKey("idd").mean("revenue").show(false)  

   val result = udf((text: String) => WriterToPostgres(df2))
    
 }
