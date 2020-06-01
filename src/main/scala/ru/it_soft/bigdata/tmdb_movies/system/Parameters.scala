package ru.it_soft.bigdata.tmdb_movies.system

import org.apache.hadoop.fs.{FileSystem, Path}

object Parameters {
  def instance(args: Array[String], fs: FileSystem) = new Parameters(args, fs)
}

class Parameters(args: Array[String], fs: FileSystem) extends Serializable {
  val EMPTY_PATH = "/user/..."
  private val paramMap = args
    .map(param => {
      val pair = param.split("=", -1)
      if (pair.length == 3)
        (pair(0), pair(1) + "=" + pair(2))
      else
        (pair(0), pair(1))
    }).toMap
}
