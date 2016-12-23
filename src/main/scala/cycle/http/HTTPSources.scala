package cycle.http

import cycle.base.{IsolatableSource, RawSource, Sources}
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO use pimp-my-library pattern here

@js.native
trait HTTPSource extends RawSource with IsolatableSource[HTTPSource, HTTPSink] {

  def select(): XStream[RawResponseStream] = js.native

  def select(category: String): XStream[RawResponseStream] = js.native

  def filter(predicate: js.Function1[RequestOptions, Boolean]): HTTPSource = js.native

  def isolateSource(source: HTTPSource, scope: String): HTTPSource = js.native

  def isolateSink(sink: HTTPSink, scope: String): HTTPSink = js.native
}

@js.native
trait HTTPSources extends Sources {
  val HTTP: HTTPSource = js.native
}
