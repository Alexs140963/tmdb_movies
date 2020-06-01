package ru.it_soft.bigdata.tmdb_movies.tools

import org.apache.spark.sql.{DataFrame, SaveMode}

object WriterToPostgres {

  def saveToPostgres(df: DataFrame, tableWithScheme: String): Unit = {

    import spark.implicits._

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
    df.write
      .mode(SaveMode.Overwrite)
      .jdbc(url, tableName, properties)
  }
}
