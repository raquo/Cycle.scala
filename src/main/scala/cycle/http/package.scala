package cycle

import cycle.base.RawDriver
import _root_.xstream.{RawStream, XStream}

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPSink = RawStream[RequestOptions]

  type RawHTTPDriver = RawDriver[RawHTTPSink, RawHTTPSource]
}
