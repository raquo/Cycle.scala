package com.raquo.cycle.dom

import com.raquo.cycle.base.Sinks

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait DOMSinks extends Sinks {
  val DOM: DOMSink
}
