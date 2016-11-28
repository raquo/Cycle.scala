package cycle.http

import cycle.base.Driver
import cycle.xstream.adapter.XStreamAdapter

class HTTPDriver (
  rawDriver: RawHTTPDriver
) extends Driver[HTTPSink, HTTPSource, HTTPSinks, HTTPSources] {

  val key = "HTTP"

  def driverFunction(sink: HTTPSink, streamAdapter: XStreamAdapter, key: String): HTTPSource = {
    new HTTPSource(rawDriver(sink.rawStream, streamAdapter, key))
  }
}
