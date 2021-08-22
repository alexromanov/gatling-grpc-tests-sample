import sbt._

object Dependencies {
  val scalaTestVersion = "3.2.7"
  val gatlingVersion = "3.5.0"

  val scalaTestDependencies = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "org.scalatest" %% "scalatest-flatspec" % scalaTestVersion % Test
  )

  val gatlingDependencies = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
    "io.gatling" % "gatling-test-framework" % gatlingVersion % Test,
    "com.github.phisgr" % "gatling-grpc" % "0.11.1" % Test,
    "com.github.phisgr" % "gatling-ext" % "0.2.0" % Test
  )

  val loggingDependencies = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
  )

  val grpcDependencies = Seq(
    "io.grpc" % "grpc-netty" % "1.36.0",
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
  )
}
