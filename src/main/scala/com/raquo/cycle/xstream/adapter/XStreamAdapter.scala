package com.raquo.cycle.xstream.adapter

import com.raquo.cycle.base.{Observer, OptionalDisposeFunction, OriginStream, StreamAdapter, StreamSubscribeFunction, Subject}
import com.raquo.xstream.{MemoryStream, EStream}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/xstream-adapter", JSImport.Default)
object XStreamAdapter extends StreamAdapter[EStream, MemoryStream] {

  /** Convert Origin stream to XStream */
  override def adapt[T, E <: Exception](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T, E]]
  ): EStream[T, E] = js.native

  /** Convert Origin stream to XStream – already converted */
  override def adapt[T, E <: Exception](
    originStream: EStream[T, E],
    originStreamSubscribe: StreamSubscribeFunction[EStream[T, E], Observer[T, E]]
  ): EStream[T, E] = js.native

  /** Create memory stream */
  override def remember[T, E <: Exception](stream: EStream[T, E]): MemoryStream[T, E] = js.native

  /** What exactly does this method do... ? @TODO[Docs] */
  override def makeSubject[T, E <: Exception](): Subject[T, E] = js.native

  /** Check if the stream is a valid XStream – case of origin stream */
  override def isValidStream(stream: OriginStream): Boolean = js.native

  /** Check if the stream is a valid XStream */
  override def isValidStream(stream: EStream[_, _ <: Exception]): Boolean = js.native

  /** Subscribe a Cycle observer to a XStream */
  override def streamSubscribe[T, E <: Exception](stream: EStream[T, E], observer: Observer[T, E]): OptionalDisposeFunction = js.native
}
