import Dependencies._

name := "gatling-grpc-tests-sample"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++=  gatlingDependencies ++ loggingDependencies ++ grpcDependencies ++
  Seq(
    "com.github.daddykotex" %% "courier" % "3.0.0-M2a",
    "com.typesafe" % "config" % "1.4.1",
    "com.google.guava" % "guava" % "30.1.1-jre",
    "com.typesafe.akka" %% "akka-actor-typed" % "2.6.10",
    "com.typesafe.akka" %% "akka-protobuf-v3" % "2.6.10",
    "com.typesafe.akka" %% "akka-stream" % "2.6.10"
  )

enablePlugins(GatlingPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)