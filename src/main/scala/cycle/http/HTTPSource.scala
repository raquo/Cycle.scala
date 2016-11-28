package cycle.http

import cycle.base.{IsolatableSource, RawSource, Sources}
import xstream.{RawStream, XStream}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait RawHTTPSource extends IsolatableSource[RawHTTPSource, RawStream[RequestOptions]] with RawSource {

  def select(category: String): RawHTTPSource = js.native

  def select(): RawHTTPSource = js.native

  def filter(predicate: RequestOptions => Boolean): RawHTTPSource = js.native

  def isolateSource(rawSource: RawHTTPSource, scope: String): RawHTTPSource = js.native

  def isolateSink(rawSink: RawStream[RequestOptions], scope: String): RawStream[RequestOptions] = js.native
}

@ScalaJSDefined
class HTTPSource(val rawSource: RawHTTPSource) extends IsolatableSource[HTTPSource, XStream[RequestOptions]] {

  def select(category: String): HTTPSource =
    new HTTPSource(rawSource.select(category))

  def select(): HTTPSource =
    new HTTPSource(rawSource.select())

  def filter(predicate: RequestOptions => Boolean): HTTPSource =
    new HTTPSource(rawSource.filter(predicate))

  def isolateSource(source: HTTPSource, scope: String): HTTPSource =
    new HTTPSource(rawSource.isolateSource(source.rawSource, scope))

  def isolateSink(sink: XStream[RequestOptions], scope: String): XStream[RequestOptions] =
    XStream.fromRawStream(rawSource.isolateSink(sink.rawStream, scope))
}

@ScalaJSDefined
trait HTTPSources extends Sources {
  val HTTP: HTTPSource
}
