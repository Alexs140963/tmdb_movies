package ru.it_soft.bigdata.system

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

  val DATE_1D: String = paramMap.getOrElse("DATE_1D", EMPTY_PATH)
  val DATE_1D_yyyyMMdd: String = paramMap.getOrElse("DATE_1D_yyyyMMdd", EMPTY_PATH)

  println("#------------PARAMETERS----------------#")
  println("DATE_1D                      =" + DATE_1D)
  println("DATE_1D_yyyyMMdd             =" + DATE_1D_yyyyMMdd)
}
