
scalaVersion := "2.11.4"

name := "googlespreadsheet-sample"

libraryDependencies ++= Seq(
  "com.google.api-client" % "google-api-client" % "1.21.0",
  "com.google.oauth-client" % "google-oauth-client-jetty" % "1.21.0",
  "com.google.gdata" % "core" % "1.47.1"
)
