package com.raquo.cycle.dom

import com.raquo.cycle.base.Driver
import com.raquo.cycle.base.XStreamAdapter

class DOMDriver (
  rawDriver: RawDOMDriver
) extends Driver[DOMSink[Nothing], DOMSource, DOMSinks[Nothing], DOMSources] {

  val key = "DOM"

  def driverFunction(sink: DOMSink[Nothing], streamAdapter: XStreamAdapter, key: String): DOMSource =
    rawDriver(sink, streamAdapter, key)
}
