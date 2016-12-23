package cycle

import cycle.base.RawDriver
import _root_.xstream.XStream

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPSink = XStream[RequestOptions]

  type RawHTTPDriver = RawDriver[HTTPSink, HTTPSource]

  implicit class RichHTTPSource(val source: HTTPSource) extends AnyVal {

    @inline def select(category: String): XStream[ResponseStream] =
      source.select(category).map(new ResponseStream(_))

    @inline def select(): XStream[ResponseStream] =
      source.select().map(new ResponseStream(_))

    @inline def filter(predicate: RequestOptions => Boolean): HTTPSource =
      source.filter(predicate)
  }
}
