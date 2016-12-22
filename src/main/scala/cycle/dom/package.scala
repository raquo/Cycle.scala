package cycle

import cycle.base.RawDriver
import snabbdom.VNode
import _root_.xstream.XStream

package object dom {
  type DOMSink = XStream[VNode]
  type RawDOMSink = XStream[VNode]
  type RawDOMDriver = RawDriver[RawDOMSink, RawDOMSource]
}
