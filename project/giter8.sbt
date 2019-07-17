addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8" % "0.11.0")
// workaround for giter8 0.11.0 + sbt 1.2.7
libraryDependencies += { "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value }