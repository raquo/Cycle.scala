package cycle.dom

import cycle.base.Driver
import cycle.base.XStreamAdapter

class DOMDriver (
  rawDriver: RawDOMDriver
) extends Driver[DOMSink, DOMSource, DOMSinks, DOMSources] {

  val key = "DOM"

  def driverFunction(sink: DOMSink, streamAdapter: XStreamAdapter, key: String): DOMSource =
    rawDriver(sink, streamAdapter, key)
}
