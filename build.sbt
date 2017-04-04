enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "Cycle.scala"

normalizedName := "cycle"

organization := "com.raquo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

// crossScalaVersions := Seq("2.11.8", "2.12.1")

homepage := Some(url("https://github.com/raquo/Cycle.scala"))

licenses += ("MIT", url("https://github.com/raquo/Cycle.scala/blob/master/LICENSE.txt"))

libraryDependencies ++= Seq(
  "com.raquo" %%% "xstream" % "0.1-SNAPSHOT",
  "org.scala-js" %%% "scalajs-dom" % "0.9.1"
)

npmDependencies in Compile ++= Seq(
  "@cycle/dom" -> "14.1.0",
  "@cycle/http" -> "11.2.0",
  "@cycle/isolate" -> "1.4.0",
  "@cycle/xstream-adapter" -> "3.0.4",
  "@cycle/xstream-run" -> "3.1.0"
)

// Webpack bundle is not being generated? Remember that you need to run `sbt fastOptJS::webpack`, not just `sbt fastOptJS`.

//webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack-config.js")

scalaJSModuleKind := ModuleKind.CommonJSModule // not needed if using scalajs-bundler

emitSourceMaps in fastOptJS := false
