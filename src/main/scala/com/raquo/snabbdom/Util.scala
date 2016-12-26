package com.raquo.snabbdom

import org.scalajs.dom.raw.Event
import org.scalajs.dom.MouseEvent

import scala.scalajs.js

object Util {
  type EventCallback[TEvent <: Event] = js.Function1[TEvent, Unit]
  type GenericEventCallback = EventCallback[Event]
  type MouseEventCallback = EventCallback[MouseEvent]

  @inline def attr[Value](key: String): Attr[Value] = new Attr[Value](key)

  @inline def eventProp[Value <: js.Function](key: String): EventProp[Value] = new EventProp[Value](key)

  @inline def prop[Value](key: String): Prop[Value] = new Prop[Value](key)

  // @TODO use this vvvv
//  def style[TValue](jsKey: String, cssKey: String) = new Style(jsKey, cssKey)

  @inline def vnode(tagName: String) = new VNode(tagName)
}
