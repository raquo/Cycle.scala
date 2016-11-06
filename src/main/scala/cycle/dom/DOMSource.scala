package cycle.dom

import xstream.XStream

import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait DOMDriverOptions extends js.Object {
  val modules: js.UndefOr[js.Array[js.Object]]
  val transposition: js.UndefOr[Boolean]
}

@js.native
trait DOMSource extends js.Object {

  def select(selector: String): DOMSource = js.native

  def elements[TElement <: HTMLElement](): XStream[TElement] = js.native

  def events[TEvent](eventType: String): XStream[TEvent] = js.native

  def events[TEvent](eventType: String, options: EventsOptions): XStream[TEvent] = js.native
}

@ScalaJSDefined
trait EventsOptions extends js.Object {
  def useCapture: js.UndefOr[Boolean]
}
