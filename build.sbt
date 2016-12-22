enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "picobox-scala-js"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1"
)

npmDependencies in Compile ++= Seq(
  "@cycle/dom" -> "14.1.0",
  "@cycle/http" -> "11.2.0",
  "@cycle/isolate" -> "1.4.0",
  "@cycle/xstream-adapter" -> "3.0.4",
  "@cycle/xstream-run" -> "3.1.0",
  "xstream" -> "8.0.0"
)

//webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack-config.js")

scalaJSModuleKind := ModuleKind.CommonJSModule // not needed if using scalajs-bundler

emitSourceMaps in fastOptJS := true
