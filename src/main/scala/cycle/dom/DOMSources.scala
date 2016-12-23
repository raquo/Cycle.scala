package cycle.dom

import cycle.base.{IsolatableSource, Sources}
import xstream.XStream
import org.scalajs.dom.raw.{Event, HTMLElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class EventOptions(
  val useCapture: Boolean
) extends js.Object

@js.native
trait DOMSource extends IsolatableSource[DOMSource, DOMSink] {

  def select(cssSelector: String): DOMSource = js.native

  def elements[T <: HTMLElement](): XStream[T] = js.native

  def events[E <: Event](eventType: String): XStream[E] = js.native

  def events[E <: Event](eventType: String, options: EventOptions): XStream[E] = js.native

  def isolateSource(source: DOMSource, scope: String): DOMSource = js.native

  def isolateSink(sink: DOMSink, scope: String): DOMSink = js.native
}

@js.native
trait DOMSources extends Sources {
  val DOM: DOMSource = js.native
}
