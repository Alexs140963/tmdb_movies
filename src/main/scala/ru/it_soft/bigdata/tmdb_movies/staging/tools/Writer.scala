package ru.it_soft.bigdata.staging.tools

import org.apache.spark.sql.{DataFrame, SaveMode}

object Writer {

  def saveTableAsOrc(df: DataFrame, tableWithScheme: String): Unit = {
    df.write
      .option("orc.compress", "snappy")
      .format("orc")
      .mode(SaveMode.Overwrite)
      .insertInto(tableWithScheme)
  }
}
