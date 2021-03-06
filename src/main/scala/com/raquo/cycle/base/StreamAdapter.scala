package com.raquo.cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait StreamAdapter[CustomStream[_, _ <: Exception], CustomMemoryStream[_, _ <: Exception]] extends js.Object {

  /** Convert Origin stream to XStream */
  def adapt[T, E <: Exception](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T, E]]
  ): CustomStream[T, E]

  /** Convert Origin stream to XStream – already converted */
  def adapt[T, E <: Exception](
    originStream: CustomStream[T, E],
    originStreamSubscribe: StreamSubscribeFunction[CustomStream[T, E], Observer[T, E]]
  ): CustomStream[T, E]

  /** Create memory stream */
  def remember[T, E <: Exception](stream: CustomStream[T, E]): CustomMemoryStream[T, E]

  /** What exactly does this method do... ? @TODO[Docs] */
  def makeSubject[T, E <: Exception](): Subject[T, E]

  /** Check if the stream is a valid XStream – case of origin stream */
  def isValidStream(stream: OriginStream): Boolean

  /** Check if the stream is a valid XStream */
  def isValidStream(stream: CustomStream[_, _ <: Exception]): Boolean

  /** Subscribe a Cycle observer to a XStream */
  def streamSubscribe[T, E <: Exception](stream: CustomStream[T, E], observer: Observer[T, E]): OptionalDisposeFunction
}
