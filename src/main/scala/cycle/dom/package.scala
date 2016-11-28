package cycle

import _root_.xstream.XStream
import cycle.base.RawDriverFunction
import snabbdom.VNode
import _root_.xstream.RawStream

package object dom {

  type DOMSink = XStream[VNode]
  type RawDOMSink = RawStream[VNode]

  type RawDOMDriver = RawDriverFunction[RawDOMSink, RawDOMSource]
}
