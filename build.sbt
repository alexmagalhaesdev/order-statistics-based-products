ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "dott_technical_test"
  )

// Scala 2.12, 2.13
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
  "org.postgresql" % "postgresql" % "42.2.14",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
