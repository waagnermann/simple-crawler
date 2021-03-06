name := """simple-crawler"""
organization := "com.mscping"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "2.2.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.vagner.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.vagner.binders._"
