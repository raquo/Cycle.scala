package com.raquo.cycle.xstream.adapter

import com.raquo.cycle.base.{Observer, OptionalDisposeFunction, OriginStream, StreamAdapter, StreamSubscribeFunction, Subject}
import com.raquo.xstream.{MemoryStream, XStream}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/xstream-adapter", JSImport.Default)
object XStreamAdapter extends StreamAdapter[XStream, MemoryStream] {

  /** Convert Origin stream to XStream */
  override def adapt[T, E](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T, E]]
  ): XStream[T, E] = js.native

  /** Convert Origin stream to XStream – already converted */
  override def adapt[T, E](
    originStream: XStream[T, E],
    originStreamSubscribe: StreamSubscribeFunction[XStream[T, E], Observer[T, E]]
  ): XStream[T, E] = js.native

  /** Create memory stream */
  override def remember[T, E](stream: XStream[T, E]): MemoryStream[T, E] = js.native

  /** What exactly does this method do... ? @TODO[Docs] */
  override def makeSubject[T, E](): Subject[T, E] = js.native

  /** Check if the stream is a valid XStream – case of origin stream */
  override def isValidStream(stream: OriginStream): Boolean = js.native

  /** Check if the stream is a valid XStream */
  override def isValidStream(stream: XStream[_, _]): Boolean = js.native

  /** Subscribe a Cycle observer to a XStream */
  override def streamSubscribe[T, E](stream: XStream[T, E], observer: Observer[T, E]): OptionalDisposeFunction = js.native
}
