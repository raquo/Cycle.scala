package com.raquo.cycle.http

import com.raquo.cycle.base.Sinks

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait HTTPSinks[Err] extends Sinks {
  val HTTP: HTTPSink[Err]
}
