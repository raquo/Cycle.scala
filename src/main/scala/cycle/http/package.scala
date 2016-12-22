package cycle

import cycle.base.RawDriver
import _root_.xstream.XStream

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPSink = XStream[RequestOptions]

  type RawHTTPDriver = RawDriver[RawHTTPSink, RawHTTPSource]
}
