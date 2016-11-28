package cycle.dom

import cycle.base.{Driver, RawDrivers}
import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js.annotation.ScalaJSDefined

class DOMDriver (
  val rawDriver: RawDOMDriver
) extends Driver[DOMSink, RawDOMSink, DOMSource, RawDOMSource, DOMSinks, DOMSources] {

  val key = "DOM"

  def driverFunction(sink: DOMSink, streamAdapter: XStreamAdapter, key: String): DOMSource = {
    new DOMSource(rawDriver(sink.rawStream, streamAdapter, key))
  }
}

// @TODO remove? This is not used anywhere
@ScalaJSDefined
trait DOMDrivers extends RawDrivers {
  val DOM: RawDOMDriver
}
