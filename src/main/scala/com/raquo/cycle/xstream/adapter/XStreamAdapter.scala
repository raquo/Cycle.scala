package com.raquo.cycle.xstream.adapter

import com.raquo.cycle.base.{Observer, OptionalDisposeFunction, OriginStream, StreamAdapter, StreamSubscribeFunction, Subject}
import com.raquo.xstream.{MemoryStream, XStream}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/xstream-adapter", JSImport.Default)
object XStreamAdapter extends StreamAdapter[XStream, MemoryStream] {

  /** Convert Origin stream to XStream */
  override def adapt[T](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T]]
  ): XStream[T] = js.native

  /** Convert Origin stream to XStream – already converted */
  override def adapt[T](
    originStream: XStream[T],
    originStreamSubscribe: StreamSubscribeFunction[XStream[T], Observer[T]]
  ): XStream[T] = js.native

  /** Create memory stream */
  override def remember[T](stream: XStream[T]): MemoryStream[T] = js.native

  /** What exactly does this method do... ? @TODO[Docs] */
  override def makeSubject[T](): Subject[T] = js.native

  /** Check if the stream is a valid XStream – case of origin stream */
  override def isValidStream(stream: OriginStream): Boolean = js.native

  /** Check if the stream is a valid XStream */
  override def isValidStream(stream: XStream[_]): Boolean = js.native

  /** Subscribe a Cycle observer to a XStream */
  override def streamSubscribe[T](stream: XStream[T], observer: Observer[T]): OptionalDisposeFunction = js.native
}
