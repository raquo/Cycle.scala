package cycle.http

import cycle.base.{IsolatableSource, RawSource, Sources}
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO use pimp-my-library pattern here

@js.native
trait RawHTTPSource extends RawSource with IsolatableSource[RawHTTPSource, RawHTTPSink] {

  def select(category: js.UndefOr[String] = js.undefined): XStream[RawResponseStream] = js.native

  def filter(predicate: RequestOptions => Boolean): RawHTTPSource = js.native

  def isolateSource(rawSource: RawHTTPSource, scope: String): RawHTTPSource = js.native

  def isolateSink(rawSink: RawHTTPSink, scope: String): RawHTTPSink = js.native
}

@ScalaJSDefined
class HTTPSource(val rawSource: RawHTTPSource) extends IsolatableSource[HTTPSource, HTTPSink] {

  def select(category: String): XStream[ResponseStream] = {
    rawSource.select(category).map(new ResponseStream(_))
  }

  def select(): XStream[ResponseStream] =
    rawSource.select().map(new ResponseStream(_))

  def filter(predicate: RequestOptions => Boolean): HTTPSource =
    new HTTPSource(rawSource.filter(predicate))

  protected def isolateSource(source: HTTPSource, scope: String): HTTPSource =
    new HTTPSource(rawSource.isolateSource(source.rawSource, scope))

  protected def isolateSink(sink: HTTPSink, scope: String): HTTPSink =
    rawSource.isolateSink(sink.stream, scope)
}

@js.native
trait HTTPSources extends Sources {
  val HTTP: HTTPSource = js.native
}
