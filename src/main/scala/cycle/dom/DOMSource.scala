package cycle.dom

import xstream.XStream
import org.scalajs.dom.raw.{Event, HTMLElement}
import snabbdom.EventProp
import snabbdom.Util.EventCallback

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait EventOptions extends js.Object {
  val useCapture: Boolean
}

@js.native
trait RawDOMSource extends js.Object {

  def select(selector: String): RawDOMSource = js.native

  def elements[TElement <: HTMLElement](): XStream[TElement] = js.native

  def events[T <: Event](eventType: String): XStream[T] = js.native

  def events[T <: Event](eventType: String, options: EventOptions): XStream[T] = js.native
}

@ScalaJSDefined
class DOMSource(val rawSource: RawDOMSource) extends js.Object {

  def select(selector: String): DOMSource = rawSource.select(selector)

  def elements[T <: HTMLElement](): XStream[T] = rawSource.elements[T]()

  @inline def events[T <: Event](eventType: String): XStream[T] =
    rawSource.events[T](eventType)

  @inline def events[T <: Event](eventType: String, options: EventOptions): XStream[T] =
    rawSource.events[T](eventType, options)

  @inline def events[T <: Event](eventProp: EventProp[EventCallback[T]]) =
    rawSource.events[T](eventProp.key)

  @inline def events[T <: Event](eventProp: EventProp[EventCallback[T]], options: EventOptions) =
    rawSource.events[T](eventProp.key, options)
}
