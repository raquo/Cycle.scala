package snabbdom

import org.scalajs.dom.raw.Event
import org.scalajs.dom.MouseEvent

import scala.scalajs.js

object Util {
  type EventCallback[TEvent <: Event] = js.Function1[TEvent, Unit]
  type GenericEventCallback = EventCallback[Event]
  type MouseEventCallback = EventCallback[MouseEvent]

  @inline def attr[TValue](key: String) = new Attr[TValue](key)

  @inline def eventProp[TValue <: js.Function](key: String) = new EventProp[TValue](key)

  @inline def prop[TValue](key: String) = new Prop[TValue](key)

//  def style[TValue](jsKey: String, cssKey: String) = new Style(jsKey, cssKey)

  @inline def vnode(tagName: String) = new VNode(tagName)
}
