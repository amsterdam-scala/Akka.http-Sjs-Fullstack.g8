import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

organization := "$organization$"
name := "$name$"
version := "$version$"

lazy val server = (project in file("jvm")).settings(
  scalaJSProjects := Seq(client, sw),
  // triggers scalaJSPipeline when using compile or continuous compilation
  pipelineStages in Assets := Seq(scalaJSPipeline),
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http"          % vAkkaHttp,
    "com.typesafe.akka" %% "akka-stream"        % vAkka
  ),
  WebKeys.packagePrefix in Assets := "public/",
  managedClasspath in Runtime += (packageBin in Assets).value
).settings(commonSettings).enablePlugins(SbtWeb, JavaAppPackaging).dependsOn(shared.jvm)

lazy val client = (project in file("js")).settings(
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js"     %%% "scalajs-dom"         %vScalajsDom,
    "io.github.cquiroz"%%% "scala-java-time-tzdb"%"2.0.0-RC3_2019a"
  )
).settings(commonSettings).enablePlugins(ScalaJSPlugin, ScalaJSWeb).dependsOn(shared.js)

lazy val shared = (crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure) in file("shared")).
  settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi"    %%% "autowire"           % vAutowire,
      "com.lihaoyi"    %%% "scalatags"          % vScalatags,
      "io.suzaku"      %%% "boopickle"          % vBoopickle)
  ).settings(commonSettings)
  .jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sw = (project in file("sw"))
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "0.9.7")
  ).settings(commonSettings).enablePlugins(ScalaJSPlugin, ScalaJSWeb)

val vAkka          = "2.5.23"
val vAkkaHttp      = "10.1.9"
val vAutowire      = "0.2.6"
val vBoopickle     = "1.3.1"
val vScala         = "2.12.8"
val vScalajsDom    = "0.9.7"
val vScalatags     = "0.7.0"
val commonSettings = Seq(scalaVersion := vScala, organization := "$organization$")

// loads the server project at sbt startup
onLoad in Global ~= (_ andThen ("project server" :: _))