name := """KLOUD"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  jdbc,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.google.code.gson" % "gson" % "2.6.2"

)