name := """reactive-lab2"""

version := "1.4"

scalaVersion := "2.13.6"

val akkaVersion = "2.6.20"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "org.scalatest"     %% "scalatest"        % "3.2.12" % "test",
  "ch.qos.logback"    % "logback-classic"   % "1.4.1"
)
