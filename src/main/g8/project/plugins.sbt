// Creates a jar from the source code: https://github.com/sbt/sbt-native-packager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.25")
// To use Scala.js along with any sbt-web server: https://github.com/vmunier/sbt-web-scalajs
addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.9-0.6")
// Fast development turnaround when using sbt ~reStart: https://github.com/spray/sbt-revolver
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
// Cross-platform compilation support for sbt: https://github.com/portable-scala/sbt-crossproject
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.6.1")
// Compiles Scala to JavaScript: https://www.scala-js.org
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.28")