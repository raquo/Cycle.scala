package com.raquo.cycle.http

import com.raquo.cycle.base.Driver
import com.raquo.cycle.base.XStreamAdapter

class HTTPDriver (
  rawDriver: RawHTTPDriver
) extends Driver[HTTPSink, HTTPSource, HTTPSinks, HTTPSources] {

  val key = "HTTP"

  def driverFunction(sink: HTTPSink, streamAdapter: XStreamAdapter, key: String): HTTPSource =
    rawDriver(sink, streamAdapter, key)
}
