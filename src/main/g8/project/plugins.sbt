addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.23")
addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.9-0.6")
// fast development turnaround when using sbt ~re-start
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.6.1")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.28")
// addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10" exclude("org.apache.maven", "maven-plugin-api"))