package cycle

import cycle.base.RawDriverFunction
import _root_.xstream.XStream
import _root_.xstream.RawStream

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPSink = RawStream[RequestOptions]

  type RawHTTPDriver = RawDriverFunction[RawHTTPSink, RawHTTPSource]
}
