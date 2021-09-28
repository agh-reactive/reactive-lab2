name := """reactive-lab2"""

version := "1.3"

scalaVersion := "2.13.6"

val akkaVersion = "2.6.16"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "org.scalatest"     %% "scalatest"        % "3.2.9" % "test",
  "ch.qos.logback"    % "logback-classic"   % "1.2.6"
)
