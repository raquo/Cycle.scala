package cycle.http

import cycle.base.{Sink, Sinks}
import xstream.XStream

import scala.scalajs.js.annotation.ScalaJSDefined

//trait HTTPSink extends Sink { this: XStream[RequestOptions] => }

@ScalaJSDefined
trait HTTPSinks extends Sinks {
  val HTTP: HTTPSink
}
