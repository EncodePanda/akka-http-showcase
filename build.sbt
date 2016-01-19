name          := "akka-http-showcase"
scalaVersion  := "2.11.7"

libraryDependencies ++= {
  val akkaHttpVersion          = "2.0.2"
  Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaHttpVersion
  )
}
