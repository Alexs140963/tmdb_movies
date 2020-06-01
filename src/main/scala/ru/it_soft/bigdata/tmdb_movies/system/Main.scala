package ru.it_soft.bigdata.tmdb_movies.system

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession
import ru.it_soft.bigdata.tmdb_movies.tools.Writer

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

    val database = jobConfig.getString("database")
    val url: String = s"jdbc:postgresql://localhost/$database"
    val tableName: String = jobConfig.getString("tableName")
    val user: String = jobConfig.getString("user")
    val password: String = jobConfig.getString("password")
    val sql = jobConfig.getString("sql")
    val df = sc.sql(sql)
    val properties = new Properties()
    properties.setProperty("user", user)
    properties.setProperty("password", password)
    properties.put("driver", "org.postgresql.Driver")
    df.write.mode(SaveMode.Overwrite).jdbc(url, tableName, properties)

    spark.stop()
}



}