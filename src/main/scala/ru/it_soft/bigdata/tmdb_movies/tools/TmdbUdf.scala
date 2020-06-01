package ru.it_soft.bigdata.tmdb_movies.tools

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.udf
import ru.it_soft.bigdata.tmdb_movies.system.Parameters

object TmdbUdf {

  def apply(spark: SparkSession, stringDict: Broadcast[Array[String]], parameters: Parameters) : DataFrame = {

    val braces = """\{.*?\}""".r
	var result = false
      for (i <- stringDict.value) {
        if (number.substring(1).equals(i)) { 
          result = true
   } 
 }  
  def genres_match(s: String) = {
    (braces findAllIn s).toList
    val genres_match_udf = udf(genres_match _)
  }
}
