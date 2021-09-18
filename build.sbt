name := """reactive-lab2"""

version := "1.3"

scalaVersion := "3.0.2"

val akkaVersion = "2.6.16"

libraryDependencies ++= Seq(
  ("com.typesafe.akka" %% "akka-actor"               % akkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-testkit"             % akkaVersion % "test").cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % "test").cross(CrossVersion.for3Use2_13),
  ("org.scalatest"     %% "scalatest"                % "3.2.2" % "test").cross(CrossVersion.for3Use2_13),
  "ch.qos.logback"    % "logback-classic"           % "1.2.3"
)
