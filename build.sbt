
name := "micro-geoip"

version := IO.read(new File("VERSION")).mkString.trim

scalaVersion := "2.11.8"

assemblyJarName in assembly := "micro-geoip.jar"

test in assembly := false

resolvers ++= Seq(
  "SnowPlow Repo" at "http://maven.snplow.com/releases/"
)

val akkaVersion = "2.4.11"
val akkaHttpVersion = "10.0.1"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.5",
  "org.joda" % "joda-convert" % "1.7",
  "com.typesafe" % "config" % "1.3.0",
  "ch.megard" %% "akka-http-cors" % "0.1.8",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.snowplowanalytics"  %% "scala-maxmind-iplookups"  % "0.2.0",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1",

  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,

  "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-jackson-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaVersion,

  "com.jayway.restassured" % "rest-assured" % "2.9.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1"
)
