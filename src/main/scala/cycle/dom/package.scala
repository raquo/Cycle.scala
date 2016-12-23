package cycle

import cycle.base.RawDriver
import snabbdom.{EventProp, VNode}
import snabbdom.Util.EventCallback
import _root_.xstream.XStream
import org.scalajs.dom.raw.Event

package object dom {

  type DOMSink = XStream[VNode]

  type RawDOMDriver = RawDriver[DOMSink, DOMSource]

  implicit class RichDOMSource(val source: DOMSource) extends AnyVal {

    @inline def events[Ev <: Event](eventProp: EventProp[EventCallback[Ev]]): XStream[Ev] =
      source.events[Ev](eventProp.key)

    @inline def events[Ev <: Event](
      eventProp: EventProp[EventCallback[Ev]],
      options: EventOptions
    ): XStream[Ev] =
      source.events[Ev](eventProp.key, options)
  }
}
