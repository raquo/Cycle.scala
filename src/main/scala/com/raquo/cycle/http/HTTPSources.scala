package com.raquo.cycle.http

import com.raquo.cycle.base.{IsolatableSource, Sources}
import com.raquo.xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait HTTPSource extends IsolatableSource[HTTPSource, HTTPSink] {

  def select(): XStream[ResponseStream, Nothing] = js.native

  @JSName("select")
  def selectByCategory(category: String): XStream[ResponseStream, Nothing] = js.native

  def filter(predicate: js.Function1[RequestOptions, Boolean]): HTTPSource = js.native

  def isolateSource(source: HTTPSource, scope: String): HTTPSource = js.native

  def isolateSink[Err <: js.Error](sink: HTTPSink[Err], scope: String): HTTPSink[Err] = js.native
}

@js.native
trait HTTPSources extends Sources {
  val HTTP: HTTPSource = js.native
}
