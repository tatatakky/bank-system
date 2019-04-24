val defaultSetting = Seq(
  organization := "com.github.tatatakky",
  scalaVersion := "2.12.7"
)

val akkaVersion = "2.5.21"
lazy val akkalibraryDependencies = Seq(
  "com.typesafe.akka"       %%  "akka-actor"   % akkaVersion,
  "com.typesafe.akka"       %%  "akka-slf4j"   % akkaVersion,
  "com.typesafe.akka"       %%  "akka-testkit" % akkaVersion   % "test"
)

lazy val commonlibraryDependencies = Seq(
  "org.scalatest" %% "scalatest"  % "3.0.0" % "test"
)

lazy val test = (project in file("."))
  .settings(
    name := "bankSystem",
    defaultSetting,
    libraryDependencies ++= akkalibraryDependencies ++ commonlibraryDependencies
  )
