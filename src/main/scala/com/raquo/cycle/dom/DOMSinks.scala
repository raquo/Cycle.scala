package com.raquo.cycle.dom

import com.raquo.cycle.base.Sinks

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait DOMSinks[Err <: Exception] extends Sinks {
  val DOM: DOMSink[Err]
}
