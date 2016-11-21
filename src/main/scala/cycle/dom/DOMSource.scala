package cycle.dom

import cycle.base.IsolatableSource
import xstream.{RawStream, XStream}
import org.scalajs.dom.raw.{Event, HTMLElement}
import snabbdom.{EventProp, VNode}
import snabbdom.Util.EventCallback

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class EventOptions(
  val useCapture: Boolean
) extends js.Object

@js.native
trait RawDOMSource extends IsolatableSource[RawDOMSource, RawStream[VNode]] {

  def select(selector: String): RawDOMSource = js.native

  def elements[TElement <: HTMLElement](): XStream[TElement] = js.native

  def events[T <: Event](eventType: String): XStream[T] = js.native

  def events[T <: Event](eventType: String, options: EventOptions): XStream[T] = js.native

  def isolateSource(rawSource: RawDOMSource, scope: String): RawDOMSource = js.native

  def isolateSink(rawSink: RawStream[VNode], scope: String): RawStream[VNode] = js.native
}

@ScalaJSDefined
class DOMSource(val rawSource: RawDOMSource) extends IsolatableSource[DOMSource, XStream[VNode]] {

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

  @inline def isolateSource(source: DOMSource, scope: String): DOMSource =
    new DOMSource(rawSource.isolateSource(rawSource, scope))

  @inline def isolateSink(sink: XStream[VNode], scope: String): XStream[VNode] =
    XStream.fromRawStream(rawSource.isolateSink(sink.rawStream, scope))
}
