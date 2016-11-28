package cycle.dom

import cycle.base.Driver
import cycle.xstream.adapter.XStreamAdapter

class DOMDriver (
  rawDriver: RawDOMDriver
) extends Driver[DOMSink, DOMSource, DOMSinks, DOMSources] {

  val key = "DOM"

  def driverFunction(sink: DOMSink, streamAdapter: XStreamAdapter, key: String): DOMSource = {
    new DOMSource(rawDriver(sink.rawStream, streamAdapter, key))
  }
}
