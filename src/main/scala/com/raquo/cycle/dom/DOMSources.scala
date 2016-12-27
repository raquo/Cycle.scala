package com.raquo.cycle.dom

import com.raquo.cycle.base.{IsolatableSource, Sources}
import com.raquo.xstream.XStream
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

  def elements[T <: HTMLElement](): XStream[T, Nothing] = js.native // @TODO>>> Signature seems incorrect - should be multiple elements? Or... Check cycle source.

  def events[Ev <: Event](eventType: String): XStream[Ev, Nothing] = js.native

  def events[Ev <: Event](eventType: String, options: EventOptions): XStream[Ev, Nothing] = js.native

  def isolateSource(source: DOMSource, scope: String): DOMSource = js.native

  def isolateSink[Err](sink: DOMSink[Err], scope: String): DOMSink[Err] = js.native
}

@js.native
trait DOMSources extends Sources {
  val DOM: DOMSource = js.native
}
