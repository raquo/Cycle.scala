package cycle.http

import cycle.base.{Driver, RawDrivers}
import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js.annotation.ScalaJSDefined

class HTTPDriver (
  val rawDriver: RawHTTPDriver
) extends Driver[HTTPSink, RawHTTPSink, HTTPSource, RawHTTPSource, HTTPSinks, HTTPSources] {

  val key = "HTTP"

  def driverFunction(sink: HTTPSink, streamAdapter: XStreamAdapter, key: String): HTTPSource = {
    new HTTPSource(rawDriver(sink.rawStream, streamAdapter, key))
  }
}

// @TODO remove? This is not used anywhere
// @TODO also remove "<: Drivers" signatures?
@ScalaJSDefined
trait HTTPDrivers extends RawDrivers {
  val HTTP: RawHTTPDriver
}
