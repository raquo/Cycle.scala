package com.raquo.cycle.dom

import com.raquo.cycle.base.{IsolatableSource, Sources}
import com.raquo.xstream.XStream
import org.scalajs.dom.raw.{Event, HTMLElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.|

@ScalaJSDefined
class EventOptions(
  val useCapture: Boolean
) extends js.Object

@js.native
trait DOMSource extends IsolatableSource[DOMSource, DOMSink] {

  def select(cssSelector: String): DOMSource = js.native

  private[dom] def elements(): XStream[HTMLElement | js.Array[HTMLElement]] = js.native

  private[dom] def events[Ev <: Event](eventType: String): XStream[Ev] = js.native

  private[dom] def events[Ev <: Event](eventType: String, options: EventOptions): XStream[Ev] = js.native

  def isolateSource(source: DOMSource, scope: String): DOMSource = js.native

  def isolateSink[Err <: Exception](sink: DOMSink[Err], scope: String): DOMSink[Err] = js.native
}

@js.native
trait DOMSources extends Sources {
  val DOM: DOMSource = js.native
}
