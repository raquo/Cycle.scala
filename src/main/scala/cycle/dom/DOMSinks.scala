package cycle.dom

import cycle.base.{Sink, Sinks}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait DOMSinks extends Sinks {
  val DOM: DOMSink
}
