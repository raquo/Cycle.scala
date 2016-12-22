package cycle.dom

import cycle.base.{IsolatableSource, RawSource, Sources}
import xstream.XStream
import org.scalajs.dom.raw.{Event, HTMLElement}
import snabbdom.EventProp
import snabbdom.Util.EventCallback

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class EventOptions(
  val useCapture: Boolean
) extends js.Object

// @TODO[API] Use pimp-my-library pattern here

@js.native
trait RawDOMSource extends RawSource with IsolatableSource[RawDOMSource, RawDOMSink] {

  def select(cssSelector: String): RawDOMSource = js.native

  def elements[T <: HTMLElement](): XStream[T] = js.native

  def events[E <: Event](eventType: String): XStream[E] = js.native

  def events[E <: Event](eventType: String, options: EventOptions): XStream[E] = js.native

  def isolateSource(rawSource: RawDOMSource, scope: String): RawDOMSource = js.native

  def isolateSink(rawSink: RawDOMSink, scope: String): RawDOMSink = js.native
}

@ScalaJSDefined
class DOMSource(val rawSource: RawDOMSource) extends IsolatableSource[DOMSource, DOMSink] {

  @inline def select(cssSelector: String): DOMSource =
    new DOMSource(rawSource.select(cssSelector))

  @inline def elements[T <: HTMLElement](): XStream[T] =
    rawSource.elements[T]()

  @inline def events[E <: Event](eventType: String): XStream[E] =
    rawSource.events[E](eventType)

  @inline def events[E <: Event](eventType: String, options: EventOptions): XStream[E] =
    rawSource.events[E](eventType, options)

  @inline def events[E <: Event](eventProp: EventProp[EventCallback[E]]): XStream[E] =
    rawSource.events[E](eventProp.key)

  @inline def events[E <: Event](eventProp: EventProp[EventCallback[E]], options: EventOptions): XStream[E] =
    rawSource.events[E](eventProp.key, options)

  @inline protected def isolateSource(source: DOMSource, scope: String): DOMSource =
    new DOMSource(rawSource.isolateSource(source.rawSource, scope))

  @inline protected def isolateSink(sink: DOMSink, scope: String): DOMSink =
    rawSource.isolateSink(sink, scope)
}

@js.native
trait DOMSources extends Sources {
  val DOM: DOMSource = js.native
}
