package cycle.http

import cycle.base.{IsolatableSource, RawSource, Sources}
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait RawHTTPSource extends RawSource with IsolatableSource[RawHTTPSource, RawHTTPSink] {

  def select(category: String): RawHTTPSource = js.native

  def select(): RawHTTPSource = js.native

  def filter(predicate: RequestOptions => Boolean): RawHTTPSource = js.native

  def isolateSource(rawSource: RawHTTPSource, scope: String): RawHTTPSource = js.native

  def isolateSink(rawSink: RawHTTPSink, scope: String): RawHTTPSink = js.native
}

@ScalaJSDefined
class HTTPSource(val rawSource: RawHTTPSource) extends IsolatableSource[HTTPSource, HTTPSink] {

  def select(category: String): HTTPSource =
    new HTTPSource(rawSource.select(category))

  def select(): HTTPSource =
    new HTTPSource(rawSource.select())

  def filter(predicate: RequestOptions => Boolean): HTTPSource =
    new HTTPSource(rawSource.filter(predicate))

  protected def isolateSource(source: HTTPSource, scope: String): HTTPSource =
    new HTTPSource(rawSource.isolateSource(source.rawSource, scope))

  protected def isolateSink(sink: HTTPSink, scope: String): HTTPSink =
    XStream.fromRawStream(rawSource.isolateSink(sink.rawStream, scope))
}

@js.native
trait HTTPSources extends Sources {
  val HTTP: HTTPSource = js.native
}
