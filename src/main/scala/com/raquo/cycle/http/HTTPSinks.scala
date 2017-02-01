package com.raquo.cycle.http

import com.raquo.cycle.base.Sinks

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait HTTPSinks[Err <: Exception] extends Sinks {
  val HTTP: HTTPSink[Err]
}
