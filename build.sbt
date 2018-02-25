val ScalatraVersion = "2.6.2"

organization := "com.redbee.challenge"

name := "redbee-challenge-publisher"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.4"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.8.v20171121" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.danielasfregola" %% "twitter4s" % "5.5"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
