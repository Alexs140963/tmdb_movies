package ru.it_soft.bigdata.tmdb_movies.tools

class Parameters(args: Array[String]) extends Serializable {
  @transient private lazy val log = Logger.getLogger(getClass)
  private val ArgSeparator = "="

  private def pair(arg: String): (String, String) = {
    val array = arg.split(ArgSeparator, -1)
    log.info(s"Added properties {${array(0)};${array(1)}}")
    (array(0), array(1))
  }

  private val argsMap = args.map(pair).toMap

  val SrcPath = argsMap.getOrElse("srcPath", "no_data")
  val DatabaseName = argsMap.getOrElse("database", "no_data")
  val TableName = argsMap.getOrElse("tableName", "no_data")
  val UserName = argsMap.getOrElse("username", "no_data")
  val Password = argsMap.getOrElse("password", "no_data")

  System.out.println(SrcPath)
  System.out.println(DatabaseName)
  System.out.println(TableName)
  System.out.println(UserName)
  System.out.println(Password)
}

object Parameters extends Serializable {
  def instance(args: Array[String]) = new Parameters(args)
}