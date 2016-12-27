package com.raquo.cycle

import com.raquo.cycle.base.RawDriver
import com.raquo.snabbdom.{EventProp, VNode}
import com.raquo.snabbdom.VNode
import com.raquo.snabbdom.Util.EventCallback
import com.raquo.xstream.XStream
import org.scalajs.dom.raw.Event

package object dom {

  type DOMSink[Err] = XStream[VNode, Err]

  type RawDOMDriver = RawDriver[DOMSink[Nothing], DOMSource]

  implicit class RichDOMSource(val source: DOMSource) extends AnyVal {

    @inline def events[Ev <: Event](eventProp: EventProp[EventCallback[Ev]]): XStream[Ev, Nothing] =
      source.events[Ev](eventProp.key)

    @inline def events[Ev <: Event](
      eventProp: EventProp[EventCallback[Ev]],
      options: EventOptions
    ): XStream[Ev, Nothing] =
      source.events[Ev](eventProp.key, options)
  }
}
