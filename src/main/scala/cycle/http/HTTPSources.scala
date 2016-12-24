package cycle.http

import cycle.base.{IsolatableSource, Sources}
import xstream.XStream

import scala.scalajs.js

@js.native
trait HTTPSource extends IsolatableSource[HTTPSource, HTTPSink] {

  def select(): XStream[ResponseStream] = js.native

  def select(category: String): XStream[ResponseStream] = js.native

  def filter(predicate: js.Function1[RequestOptions, Boolean]): HTTPSource = js.native

  def isolateSource(source: HTTPSource, scope: String): HTTPSource = js.native

  def isolateSink(sink: HTTPSink, scope: String): HTTPSink = js.native
}

@js.native
trait HTTPSources extends Sources {
  val HTTP: HTTPSource = js.native
}
