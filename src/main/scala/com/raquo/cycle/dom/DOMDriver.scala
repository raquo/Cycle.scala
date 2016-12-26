package com.raquo.cycle.dom

import com.raquo.cycle.base.Driver
import com.raquo.cycle.base.XStreamAdapter

class DOMDriver (
  rawDriver: RawDOMDriver
) extends Driver[DOMSink, DOMSource, DOMSinks, DOMSources] {

  val key = "DOM"

  def driverFunction(sink: DOMSink, streamAdapter: XStreamAdapter, key: String): DOMSource =
    rawDriver(sink, streamAdapter, key)
}
