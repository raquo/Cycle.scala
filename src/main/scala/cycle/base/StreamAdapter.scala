package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait StreamAdapter[CustomStream[_], CustomMemoryStream[_]] extends js.Object {

  /** Convert Origin stream to XStream */
  def adapt[T](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T]]
  ): CustomStream[T]

  /** Convert Origin stream to XStream – already converted */
  def adapt[T](
    originStream: CustomStream[T],
    originStreamSubscribe: StreamSubscribeFunction[CustomStream[T], Observer[T]]
  ): CustomStream[T]

  /** Create memory stream */
  def remember[T](stream: CustomStream[T]): CustomMemoryStream[T]

  /** What exactly does this method do... ? @TODO[Docs] */
  def makeSubject[T](): Subject[T]

  /** Check if the stream is a valid XStream – case of origin stream */
  def isValidStream(stream: OriginStream): Boolean

  /** Check if the stream is a valid XStream */
  def isValidStream(stream: CustomStream[_]): Boolean

  /** Subscribe a Cycle observer to a XStream */
  def streamSubscribe[T](stream: CustomStream[T], observer: Observer[T]): OptionalDisposeFunction
}
