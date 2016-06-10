name := "dts_generator"

version := "1.0.1"

scalaVersion := "2.11.7"

organization := "cisco.com"

resolvers += Resolver.bintrayRepo("crate", "crate")

sparkVersion := "1.6.1"

libraryDependencies ++= Seq(
  //  "org.apache.hadoop" % "hadoop-common" % "2.2.0",
  //  "org.apache.hadoop" % "hadoop-hdfs" % "2.2.0",
  "com.github.scopt" %% "scopt" % "3.4.0",
  "joda-time" % "joda-time" % "2.9.3",
  "com.databricks" %% "spark-avro" % "2.0.1",
  "com.databricks" %% "spark-csv" % "1.4.0",
  "org.apache.commons" % "commons-math3" % "3.6.1",
  "io.crate" % "crate-jdbc-standalone" % "1.12.1",
  "com.google.code.gson" % "gson" % "2.6"
)

sparkComponents := Seq("core", "sql", "mllib")

spIgnoreProvided := true

spAppendScalaVersion := true

spIncludeMaven := true

javaOptions += "-Dlog4j.configuration=file:conf/log4j.properties"

/********************
  * Release settings *
  ********************/
publishMavenStyle := true

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

pomExtra :=
  <url>https://github.com/CiscoCloud/mazut</url>
  <scm>
    <url>git@github.com:CiscoCloud/mazut.git</url>
    <connection>scm:git:git@github.com:CiscoCloud/mazut.git</connection>
  </scm>
  <developers>
    <developer>
      <id>cerber</id>
      <name>Dmytro Nezhynskyi</name>
    </developer>
  </developers>

//parallelExecution in Test := false

lazy val root = (project in file(".")).
  enablePlugins(BuildInfoPlugin).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "com.cisco.mazut"
  )

buildInfoKeys ++= Seq[BuildInfoKey](
  resolvers,
  libraryDependencies in Test,
  BuildInfoKey.map(name) { case (k, v) => "project" + k.capitalize -> v.capitalize },
  "custom" -> 1234, // computed at project load time
  BuildInfoKey.action("buildTime") {
    System.currentTimeMillis
  } // re-computed each time at compile
)

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.startsWith("META-INF") => MergeStrategy.discard
  case m if m.endsWith(".html") => MergeStrategy.discard
  case m if m.contains("slf4j-api") => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "reference.conf" => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case PathList("org", "apache", "commons", xs @ _*) => MergeStrategy.last
  case m =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(m)
}
